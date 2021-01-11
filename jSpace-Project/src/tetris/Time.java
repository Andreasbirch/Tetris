package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Time {

    private Timeline timeline;

    private int y;
    private int timeSeconds;

    public Time(Block block) {
        timeSeconds = 0;
        y = 28;
        initTimer(block);
    }

    public void initTimer(Block block) {

        if ( !(timeline == null) ) {
            throw new IllegalArgumentException("Time class may only be instantiated once!");
        }

        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add( new KeyFrame(Duration.seconds(1), e -> {
                timeSeconds++;
                block.move(block.getShape(), "DOWN");
        }));
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getY() {
        return y;
    }
}
