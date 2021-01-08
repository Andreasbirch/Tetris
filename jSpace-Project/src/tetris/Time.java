package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Time {

    private Timeline timeline;


    private void initTimer() {

        if ( !(timeline == null) ) {
            throw new IllegalArgumentException("Time class may only be instantiated once!");
        }

        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add( new KeyFrame(Duration.seconds(1), e -> {

        }));
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
