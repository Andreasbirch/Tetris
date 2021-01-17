package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

public class P2Timer {

    private Timeline timeline;
    private int[][] boardArray;

    public P2Timer(RemoteSpace server) {
        if ( !(timeline == null) ) {
            throw new IllegalArgumentException("Time class may only be instantiated once!");
        }

        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            if (App.getP2ID() != null) {
                Object[]arr = new Object[0];
                try {
                    arr = server.getp(new ActualField(App.getP2ID()), new FormalField(int[][].class));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                if(arr != null) {
                    boardArray = (int[][])arr[1];
                    App.setP2View(boardArray);
                }
            }
        }));

//        timeline.play();
    }

    public void play () {
        timeline.play();
    }


}
