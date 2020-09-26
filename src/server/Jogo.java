package server;

import client.Jogador;
import interfaces.IJogador;
import interfaces.IJogo;
import utils.GameUtils;
import utils.RandomIDGenerator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Jogo extends UnicastRemoteObject implements IJogo {
    private static final long serialVersionUID = Long.MAX_VALUE;
    private final int MIN_RANDOM = 0;
    private final int MAX_RANDOM = 99;
    private final int PORT = 52370;
    private volatile HashMap<Integer, String> hosts;
    private volatile Stack<IJogador> players;
    private Stack<Integer> possibleIdsCollection;
    private int maxPlayers;

    public Jogo(int maxPlayers) throws RemoteException {
        this.maxPlayers = maxPlayers;
        this.hosts = new HashMap<>();
        this.players = new Stack<>();
        this.possibleIdsCollection = RandomIDGenerator.generateNumbers(this.maxPlayers);
//        new PokerThread(this.hosts).start();

    }

    private synchronized void start() {
        for (Map.Entry<Integer, String> host : this.hosts.entrySet()) {
            try {
                IJogador player = GameUtils.getPlayer(host.getValue());
                player.inicia();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }

//        for (Map.Entry<Integer, String> host : this.hosts.entrySet()) {
//            IJogador player = GameUtils.getPlayer(host.getValue());
//            this.players.push(player);
//        }
//
//        this.players.forEach(player ->{
//            try {
//                player.inicia();
//            } catch (RemoteException exception) {
//                exception.printStackTrace();
//            }
//        });
    }

    public boolean canStartMatch() {
        return this.hosts.size() == maxPlayers;
    }

    @Override
    public int registra() throws RemoteException {
        if (canStartMatch()) {
            return -1;
        }
        try {
            String hostName = String.format("rmi://%s:%d/Hello2", getClientHost(), PORT);
            Integer playerId = this.possibleIdsCollection.pop();
            this.hosts.put(playerId, hostName);
            IJogador player = GameUtils.getPlayer(hostName);
            player.setId(playerId);
            System.out.println("Player " + hostName + " has been registered successfully.");

            if (canStartMatch()) {
                this.start();
            }

            return playerId;
        } catch (ServerNotActiveException exception) {
            System.out.println("Server is not active. Error message: " + exception.getMessage());
        }
        return -1;
    }

    @Override
    public int joga(int id) throws RemoteException {
        System.out.println("Player with id " + id + " has played.");
        int randomNumber = MIN_RANDOM + (int) (Math.random() * (MAX_RANDOM - MIN_RANDOM + 1));
        if (randomNumber == 21) {
            System.out.println("Player with id "+id+" was randomly chosen and is now being terminated.");
            this.encerra(id);
        }
        return id;
    }

    @Override
    public int encerra(int id) throws RemoteException {
        String playerHostName = this.hosts.get(id);
        IJogador player = GameUtils.getPlayer(playerHostName);

        synchronized (this.hosts) {
            this.hosts.remove(id);
        }

        player.finaliza();
//
//        try {
//            Naming.unbind(playerHostName);
//
//            System.out.println("Player with id "+id+" has been terminated by the server.");
//        } catch (NotBoundException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        return id;
    }
}
