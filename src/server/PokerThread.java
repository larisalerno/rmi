package server;

import utils.GameUtils;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class PokerThread extends Thread {
    private HashMap<Integer, String> hosts;

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
                        GameUtils.getPlayer(host.getValue()).cutuca();
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
