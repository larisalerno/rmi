package client;

import interfaces.IJogador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Jogador extends UnicastRemoteObject implements IJogador {

    public Jogador() throws RemoteException {}

    @Override
    public void inicia() throws RemoteException {
        System.out.println("The player has been initiated by the server");
    }

    @Override
    public void finaliza() throws RemoteException {
        System.out.println("The player has been terminated by the server");
    }

    @Override
    public void cutuca() throws RemoteException {
        System.out.println("The player has been poked by the server");
    }
}
