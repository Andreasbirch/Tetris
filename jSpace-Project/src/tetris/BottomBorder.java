package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BottomBorder extends Shape {
    Rectangle rectangle;

    public BottomBorder() {
        rectangle = new Rectangle();
        rectangle.setWidth(10*28);
        rectangle.setHeight(28);
        rectangle.setLayoutY(20*28);
        rectangle.setFill(Color.WHITE);
    }

    public Shape getBottomBorder() {
        return rectangle;
    }

}
