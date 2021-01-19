package tetris;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import java.io.IOException;

public class Player2 implements Runnable {
    private RemoteSpace server;
    private String p1ID, p2ID, serverID;

    public Player2(String p1ID, String p2ID, String serverID) {
        this.p1ID = p1ID;
        this.p2ID = p2ID;
        this.serverID = serverID;
    }

    @Override
    public void run() {
        String uri = "tcp://" + serverID + ":9001/server?keep";
        try {
            server = new RemoteSpace(uri);
            timer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timer() {
        while (true) {
            try {

                Object[] arr = server.get(new ActualField(p2ID), new FormalField(int[][].class));
                if( arr != null ) {
                    App.updateP2View((int[][]) arr[1]);
                }

                Object[] gameLost = server.getp(new ActualField("LOST"), new FormalField(String.class));
                if( gameLost != null ) {
                    App.winAlert();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
