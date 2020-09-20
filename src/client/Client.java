package client;

import interfaces.IJogador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IJogador {

    private static final int ARGUMENTS_SIZE = 3;
    private static volatile int playerId;

    public Client() throws RemoteException {}

    public static void main(String[] args){
        checkArguments(args);


    }

    private static void checkArguments(String[] args) {
         if (args.length != ARGUMENTS_SIZE) {
             System.out.println("" +
                     "Incorrect initialization. The correct pattern is java Client " +
                     "<server_address> <client_address> <amount_of_plays>"
             );
             System.exit(0);
         }
    }

    @Override
    public void inicia() throws RemoteException {

    }

    @Override
    public void finaliza() throws RemoteException {

    }

    @Override
    public void cutuca() throws RemoteException {

    }
}
