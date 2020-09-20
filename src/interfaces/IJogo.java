package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJogo extends Remote {
    public int registra() throws RemoteException;
    public int joga(int id) throws RemoteException;
    public int encerra(int id) throws RemoteException;
}
