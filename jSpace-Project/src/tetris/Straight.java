package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Straight extends Shape {
    final int TILE_SIZE = 28;
    final int BLOCK_WIDTH = TILE_SIZE * 4;
    Rectangle straigth;

    public Straight() {
        straigth = new Rectangle();
        straigth.setWidth(BLOCK_WIDTH);
        straigth.setHeight(TILE_SIZE);
        straigth.setFill(Color.CYAN);
    }

    public Shape getStraight(){
        return straigth;
    }
}
