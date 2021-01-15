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
    private Space space;
    private Timeline timeline;
    private int[][] boardArray;

    public GameServer(Space space) {
        this.space = space;
    }

    @Override
    public void run() {
        try {

            // Create a repository
            SpaceRepository repository = new SpaceRepository();

            // Create a local space for the chat messages
            SequentialSpace server = new SequentialSpace();

            // Add the space to the repository
            repository.add("server", server);

            // Set the URI of the chat space
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String uri = "tcp://" + ipAddress + ":9001/server?keep";
            System.out.println("Hosting game on address: " + uri);
//            String uri = "tcp://127.0.0.1:9001/chat?keep";

            // Open a gate
            URI myUri = new URI(uri);
            String gateUri = "tcp://" + myUri.getHost() + ":" + myUri.getPort() +  "?keep" ;
//            System.out.println("Opening repository gate at " + gateUri + "...");
            repository.addGate(gateUri);

            // Keep reading chat messages and printing them
            System.out.println(App.getP2ID());
//            while (true) {
////                Object[] t = chat.get(new ActualField("MAC"), new FormalField(String.class));
////                Object[] t = server.queryp(new FormalField(String.class), new FormalField(int[][].class));
////                if(t != null && t[0] != App.getID()) {
//                    if (App.getP2ID() != null) {
//                        Object[] arr = server.getp(new ActualField(App.getP2ID()), new FormalField(int[][].class));
//                        if(arr != null) {
//                            boardArray = (int[][])arr[1];
//                            App.updateP2View(boardArray);
//                        }
//                    }
//
////                }
////                while (true) {
////                    Object[] t = server.get(new FormalField(String.class), new FormalField(int[][].class));
////                    System.out.println(t[0] + " " + t[1]);
////                }
//            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
