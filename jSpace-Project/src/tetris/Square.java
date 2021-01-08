package tetris;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Square extends Shape {
    final int TILE_SIZE = 28;
    Rectangle square;

    public Square() {
        square = new Rectangle();
        square.setWidth(TILE_SIZE * 2);
        square.setHeight(TILE_SIZE * 2);
        square.setFill(Color.YELLOW);
    }

    public Shape getSquare(){
        return square;
    }
}
