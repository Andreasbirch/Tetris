package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class LBlock extends Shape {
    final int TILE_SIZE = 28;
    Shape union;
    public LBlock() {
        //Skal have fikset sit omdrejningspunkt, men ved ikke hvordan

        Rectangle top = new Rectangle();
        top.setWidth(TILE_SIZE*2);
        top.setHeight(TILE_SIZE);

        Rectangle bottom = new Rectangle();
        bottom.setWidth(TILE_SIZE);
        bottom.setHeight(TILE_SIZE*2);

        bottom.setLayoutY(bottom.getLayoutY() - TILE_SIZE*2);

        union = Shape.union(bottom,top);
        union.setFill(Color.ORANGE);
    }

    public Shape getLBlock(){
        return union;
    }

}
