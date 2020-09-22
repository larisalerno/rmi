package client;

import interfaces.IJogador;
import interfaces.IJogo;
import server.Jogo;
import utils.GameUtils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Client {

    private static final int ARGUMENTS_SIZE = 3;
    private static final int MAX_SLEEP_TIME = 1500;
    private static final int MIN_SLEEP_TIME = 500;
    private static final int CLIENT_PORT = 52370;
    private static final int SERVER_PORT = 52369;

    public static void main (String[] args) throws RemoteException {
        checkArguments(args);
        String serverAddress = args[0];
        String clientAddress = args[1];
        int numberOfPlays = Integer.parseInt(args[2]);
        int interval = MIN_SLEEP_TIME + (int) (Math.random() * (MAX_SLEEP_TIME - MIN_SLEEP_TIME + 1));

        System.setProperty("java.rmi.server.hostname", clientAddress);

        // Creating the Java RMI registry
        try {
            LocateRegistry.createRegistry(CLIENT_PORT);
            System.out.println("Java RMI registry created successfully");
        } catch (RemoteException exception) {
            System.out.println("Java RMI registry already exists. Message: "+exception.getMessage());
        }

        // Creating the client
        String clientHostName = String.format("rmi://%s:%d/Hello2",clientAddress, CLIENT_PORT);
        String serverHostName = String.format("rmi://%s:%d/server", serverAddress, SERVER_PORT);
        try {
            Naming.rebind(clientHostName, new Jogador(numberOfPlays, interval, serverHostName));
            System.out.println("Java RMI Client created successfully");
        } catch (RemoteException exception) {
            System.out.println("Java RMI Client creating failed. Message: "+exception.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Java RMI Client creation failed due to malformed URL. Message: "+e.getMessage());
        }

        // Recovering game server instance
        IJogo server = GameUtils.getServer(serverHostName);
        IJogador player = GameUtils.getPlayer(clientHostName);

        // Registering player into the game
        try {
            int response = server.registra();

            if (response == -1) {
                System.out.println("The game room is already full. Try again later.");
            }
        } catch (RemoteException exception) {
            System.out.println("Player registration failed. Message: "+exception.getMessage());
        }
    }

    private static void checkArguments(String[] args) {
        if (args.length != ARGUMENTS_SIZE) {
            System.out.println("" +
                    "Incorrect initialization. The correct pattern is: java Client " +
                    "<server_address> <client_address> <number_of_plays>"
            );
            System.exit(0);
        }
    }
}
