package server;

import interfaces.IJogo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class Server extends UnicastRemoteObject implements IJogo {
    private final int PORT = 52369;
    private volatile Map<Integer, String> players;
    private int maxPlayers;

    public Server(int maxPlayers) throws RemoteException {}

    @Override
    public int registra() throws RemoteException {
        return 0;
    }

    @Override
    public int joga(int id) throws RemoteException {
        return 0;
    }

    @Override
    public int encerra(int id) throws RemoteException {
        return 0;
    }
}
