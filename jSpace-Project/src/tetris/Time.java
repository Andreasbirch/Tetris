package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
            try {
                board.move("DOWN");
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
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
