package tetris;


import javafx.scene.paint.Color;

public class Square extends Block{
    final int TILE_SIZE = 28;

    public Square() {
        setWidth(TILE_SIZE * 2);
        setHeight(TILE_SIZE * 2);
        setFill(Color.YELLOW);
    }

}
