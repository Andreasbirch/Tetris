package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Time {

    private Timeline timeline;


    public Time(Board board) {
        if (timeline != null) {
            timeline.stop();
        }

        if ( !(timeline == null) ) {
            throw new IllegalArgumentException("Time class may only be instantiated once!");
        }

        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), e -> {
            if (board.gameOver) {
                timeline.stop();
            }
            board.move("DOWN");
            App.updateView();
            try {
                App.getTimerUpdate();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }));

        timeline.play();
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
