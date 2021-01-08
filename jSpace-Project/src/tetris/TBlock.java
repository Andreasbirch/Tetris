package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class TBlock extends Shape {
    final int TILE_SIZE = 28;
    Shape uni;
    public TBlock() {
        //Skal have fikset sit omdrejningspunkt, men ved ikke hvordan

        Rectangle top = new Rectangle();
        top.setWidth(TILE_SIZE*3);
        top.setHeight(TILE_SIZE);
        top.setFill(Color.MEDIUMPURPLE);

        Rectangle bottom = new Rectangle();
        bottom.setWidth(TILE_SIZE);
        bottom.setHeight(TILE_SIZE);
        bottom.setFill(Color.PURPLE);

        bottom.setLayoutX(bottom.getLayoutX() + TILE_SIZE);
        bottom.setLayoutY(bottom.getLayoutY() + TILE_SIZE);

        uni = Shape.union(bottom,top);
        uni.setFill(Color.PURPLE);

    }

    public Shape getTBLock(){
        return uni;
    }

}
