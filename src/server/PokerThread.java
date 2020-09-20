package server;

import client.Jogador;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PokerThread extends Thread {
    private volatile ArrayList<Jogador> players;

    public PokerThread(ArrayList<Jogador> players) {
        super();
        this.players = players;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (this.players) {
                try {
                    for (Jogador player : this.players) {
                        player.cutuca();
                    }
                    Thread.sleep(3000);
                }
                catch(RemoteException | InterruptedException exception) {
                    System.out.println("");
                }
            }
        }
    }
}
