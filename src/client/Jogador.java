package client;

import interfaces.IJogador;
import interfaces.IJogo;
import utils.GameUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Jogador extends UnicastRemoteObject implements IJogador {

    private int id;
    private int numberOfPlays;
    private int interval;
    private String serverHostName;
    private static final long serialVersionUID = 1L;

    public Jogador(int numberOfPlays, int interval, String serverHostName) throws RemoteException {
        this.numberOfPlays = numberOfPlays;
        this.interval = interval;
        this.serverHostName = serverHostName;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public void inicia() throws RemoteException {
        System.out.println("The player with id "+id+" has been initiated by the server and is now playing");

        IJogo server = GameUtils.getServer(this.serverHostName);
        for(int i = 0; i < numberOfPlays; i++) {
            try {
                server.joga(id);
                Thread.sleep(interval);
            } catch (RemoteException | InterruptedException exception) {
                System.out.println("Player with id "+id+" could not execute a play. Message: "+exception.getMessage());
            }
        }
        try {
            server.encerra(id);
        } catch (RemoteException exception) {
            System.out.println("Player could not leave the game. Message: "+exception.getMessage());
        }
    }

    @Override
    public void finaliza() throws RemoteException {
        System.out.println("The player with id "+id+" has been terminated by the server");
        System.exit(0);
    }

    @Override
    public void cutuca() throws RemoteException {
        System.out.println("The player with id "+id+" has been poked by the server");
    }
}
