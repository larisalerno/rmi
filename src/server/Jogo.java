package server;

import client.Jogador;
import interfaces.IJogo;
import utils.RandomIDGenerator;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Stack;

public class Jogo extends UnicastRemoteObject implements IJogo {
    private static final long serialVersionUID = Long.MAX_VALUE;
    private final int PORT = 52369;
    private volatile ArrayList<Jogador> players;
    private Stack<Integer> possibleIdsCollection;
    private int maxPlayers;

    public Jogo(int maxPlayers) throws RemoteException {
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
        this.possibleIdsCollection = RandomIDGenerator.generateNumbers(this.maxPlayers);
        new PokerThread(this.players).start();

    }

    private void start() {
        for (Jogador player : this.players) {
            try {
                player.inicia();
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public boolean canStartMatch() {
        return this.players.size() == maxPlayers;
    }

    @Override
    public int registra() throws RemoteException {
        if (canStartMatch()) {
            return -1;
        }
        try {
            String hostName = String.format("rmi://%d:52369/Hello2", getClientHost());
            Integer playerId = this.possibleIdsCollection.pop();
            this.players.add(new Jogador(playerId, hostName));
            System.out.println("Player "+hostName+" has been registered successfully.");

            if (canStartMatch()) {
                this.start();
            }

            return playerId;
        }
        catch (ServerNotActiveException exception) {
            System.out.println("Server is not active. Error message: "+exception.getMessage());
        }
        return -1;
    }

    @Override
    public int joga(int id) throws RemoteException {
        System.out.println("Player with id "+id+" has played.");

        // Randomizer da morte
        if (false) {
            this.encerra(id);
        }

        return id;
    }

    @Override
    public int encerra(int id) throws RemoteException {
        for (Jogador player : this.players) {
            if (player.getPlayerId() == id) {
               player.finaliza();
               this.players.remove(player);
            }
        }
        return id;
    }
}
