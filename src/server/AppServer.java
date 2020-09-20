package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AppServer {

    private static final int ARGUMENTS_SIZE = 2;
    private static final int PORT = 52369;

    public static void main(String[] args) {
        checkArguments(args);
        String serverAddress = args[0];
        int maxPlayers = Integer.parseInt(args[1]);

        try {
            System.setProperty("java.rmi.server.hostname", serverAddress);
            LocateRegistry.createRegistry(PORT);
            String server = String.format("rmi://%s:%d/remote_play", serverAddress, PORT);
            Naming.rebind(server, new Jogo(maxPlayers));
            System.out.println("Server "+server+" initiated.");
        }
        catch (RemoteException exception) {
            System.out.println("JAVA RMI already exists");
        }
        catch (MalformedURLException exception) {
            System.out.println("Malformed URL during server creation: "+exception);
        }
    }

    private static void checkArguments(String[] args) {
        if (args.length != ARGUMENTS_SIZE) {
            System.out.println("" +
                    "Incorrect initialization. The correct pattern is: java AppServer " +
                    "<server_address> <max_players>"
            );
            System.exit(0);
        }
    }
}
