package client;

import interfaces.IJogador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Jogador extends UnicastRemoteObject implements IJogador {

    private static final int ARGUMENTS_SIZE = 3;
    private static volatile int playerId;
    private static volatile String hostName;

    public Jogador(Integer id, String host) throws RemoteException {
        this.playerId = id;
        this.hostName = host;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getPlayerHostName() {
        return this.hostName;
    }

    @Override
    public void inicia() throws RemoteException {
        System.out.println("The player with id "+this.getPlayerId()+" has been initiated by the server");
    }

    @Override
    public void finaliza() throws RemoteException {
        System.out.println("The player with id "+this.getPlayerId()+" has been terminated by the server");
    }

    @Override
    public void cutuca() throws RemoteException {
        System.out.println("The player with id "+this.getPlayerId()+" has been poked by the server");
    }
}
