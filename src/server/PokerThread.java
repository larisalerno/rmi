package server;

import utils.PlayersUtils;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class PokerThread extends Thread {
    private volatile HashMap<Integer, String> hosts;

    public PokerThread(HashMap<Integer, String> hosts) {
        super();
        this.hosts = hosts;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (this.hosts) {
                try {
                    for (Map.Entry<Integer, String> host : this.hosts.entrySet()) {
                        PlayersUtils.getPlayer(host.getValue()).cutuca();
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
