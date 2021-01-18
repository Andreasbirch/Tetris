package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import java.io.IOException;
import java.rmi.Remote;

public class Player2 implements Runnable {
    private RemoteSpace server;
    private String p1ID, p2ID;

    public Player2(String p1ID, String p2ID) {
        this.p1ID = p1ID;
        this.p2ID = p2ID;
    }

    @Override
    public void run() {
        String uri = "tcp://" + p1ID + ":9001/server?keep";
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

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
