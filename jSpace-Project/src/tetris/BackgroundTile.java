package tetris;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class BackgroundTile extends Rectangle {
    final int SIZE = 28;

    public BackgroundTile(){
        setWidth(SIZE);
        setHeight(SIZE);
        setStroke(Color.DARKGRAY);


    }


}
