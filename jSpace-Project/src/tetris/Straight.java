package tetris;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Straight extends Rectangle{
    final int TILE_SIZE = 28;
    final int BLOCK_WIDTH = TILE_SIZE * 4;

    public Straight() {
        setWidth(BLOCK_WIDTH);
        setHeight(TILE_SIZE);
        setFill(Color.CYAN);


        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A) {moveLeft();}
                if(event.getCode() == KeyCode.D) {moveRight();}
                if(event.getCode() == KeyCode.S) {moveDown();}
                if(event.getCode() == KeyCode.W) {rotate();}

                event.consume();
            }
        });
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
        setRotate(getRotate()+90);

        switch((int) getRotate()){
            case 90:
                setLayoutX(getLayoutX() + TILE_SIZE/2);
                setLayoutY(getLayoutY() + TILE_SIZE/2);
                break;
            case 180:
                setLayoutX(getLayoutX() - TILE_SIZE/2);
                setLayoutY(getLayoutY() + TILE_SIZE/2);
                break;
            case 270:
                setLayoutX(getLayoutX() - TILE_SIZE/2);
                setLayoutY(getLayoutY() - TILE_SIZE/2);
                break;
            case 360:
                setLayoutX(getLayoutX() + TILE_SIZE/2);
                setLayoutY(getLayoutY() - TILE_SIZE/2);
                setRotate(0);
                break;
        }
    }
}
