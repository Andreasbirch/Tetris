package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.jspace.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class GameServer implements Runnable{
    private Space space;
    private Timeline timeline;
    private int[][] boardArray;

    public GameServer(Space space) {
        this.space = space;


//        timeline = new Timeline();
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), e -> {
//            if(boardArray != null) {
//                App.updateP2View(boardArray);
//            }
//        }));
//
//        timeline.play();
    }

    @Override
    public void run() {
        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            // Create a repository
            SpaceRepository repository = new SpaceRepository();

            // Create a local space for the chat messages
            SequentialSpace chat = new SequentialSpace();

            // Add the space to the repository
            repository.add("chat", chat);

            // Set the URI of the chat space

            String uri = "tcp://127.0.0.1:9001/chat?keep";

            // Open a gate
            URI myUri = new URI(uri);
            String gateUri = "tcp://" + myUri.getHost() + ":" + myUri.getPort() +  "?keep" ;
            System.out.println("Opening repository gate at " + gateUri + "...");
            repository.addGate(gateUri);

            // Keep reading chat messages and printing them
            while (true) {
                Object[] t = chat.get(new FormalField(String.class), new FormalField(int[][].class));
                boardArray = (int[][])t[1];
                App.updateP2View(boardArray);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
