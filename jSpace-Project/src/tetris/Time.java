package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Time {

    private Timeline timeline;

    private int y;
    private int timeSeconds;

    public Time(Board board) {
        timeSeconds = 0;
        y = 28;
        initTimer(board);
    }

    public void initTimer(Board board) {

        if ( !(timeline == null) ) {
            throw new IllegalArgumentException("Time class may only be instantiated once!");
        }

        timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add( new KeyFrame(Duration.seconds(1), e -> {
                timeSeconds++;
                board.move("DOWN");
                App.updateView();
        }));
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getY() {
        return y;
    }
}
