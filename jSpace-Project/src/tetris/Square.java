package tetris;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle{
    final int TILE_SIZE = 28;

    public Square() {
        setWidth(TILE_SIZE * 2);
        setHeight(TILE_SIZE * 2);
        setFill(Color.YELLOW);
    }


    public void moveLeft(){
        setLayoutX(getLayoutX()-TILE_SIZE);
    }

    public void moveRight(){
        setLayoutX(getLayoutX()+TILE_SIZE);
    }

    public void moveDown(){
        setLayoutY(getLayoutY()+TILE_SIZE);
    }

    public void rotate(){

    }
}
