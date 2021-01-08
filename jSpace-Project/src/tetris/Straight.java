package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Straight extends Rectangle{
    final int TILE_SIZE = 28;
    final int BLOCK_WIDTH = TILE_SIZE * 4;

    public Straight() {
        setWidth(BLOCK_WIDTH);
        setHeight(TILE_SIZE);
        setFill(Color.CYAN);
    }

}
