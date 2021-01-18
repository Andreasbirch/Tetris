package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.jspace.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class GameServer implements Runnable{

    public GameServer(Space space) {

    }

    @Override
    public void run() {
        try {
            SpaceRepository repository = new SpaceRepository();
            SequentialSpace server = new SequentialSpace();
            repository.add("server", server);

            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String uri = "tcp://" + ipAddress + ":9001/server?keep";
            System.out.println("Hosting game on address: " + uri);

            URI myUri = new URI(uri);
            String gateUri = "tcp://" + myUri.getHost() + ":" + myUri.getPort() +  "?keep" ;
            repository.addGate(gateUri);


            //Print IP of client when a connection is made.
            Object[] connectedMsg = server.query(new FormalField(String.class), new FormalField(String.class));
            System.out.println(connectedMsg[0] + "  " + connectedMsg[1]);

            while (true) {
                Object[] t = server.get(new FormalField(String.class), new FormalField(int[][].class));
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
