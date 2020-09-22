package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJogador extends Remote {
    public void inicia() throws RemoteException;
    public void finaliza() throws RemoteException;
    public void cutuca() throws RemoteException;
    public int getId() throws RemoteException;
    public void setId(int newId) throws RemoteException;
}
