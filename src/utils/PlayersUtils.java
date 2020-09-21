package utils;

import interfaces.IJogador;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PlayersUtils {
    public static IJogador getPlayer(String hostName) {
        try {
            return (IJogador) Naming.lookup(hostName);
        }
        catch (RemoteException exception) {
            System.out.println("JAVA RMI related error: Message: "+exception.getMessage());
        }
        catch (NotBoundException exception) {
            System.out.println("Hostname not found/binded. Message: "+exception.getMessage());
        }
        catch (MalformedURLException exception) {
            System.out.println("Malformed hostname URL. Message: "+exception.getMessage());
        }
        return null;
    }
}
