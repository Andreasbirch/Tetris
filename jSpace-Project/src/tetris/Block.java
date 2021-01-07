package tetris;


import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Block extends Rectangle{
    Shape shape = null;
    final int TILE_SIZE = 28;

    public Block() {

        Random random = new Random();
        int randomBlock = random.nextInt(7);

        switch (randomBlock){
            case 0:
                shape = new Straight();
                break;
            case 1:
                shape = new Square();
                break;
            case 2:
                shape = new TBlock().getTBLock();
                break;
            case 3:
                shape = new ZBlock().getZBLock();
                break;
            case 4:
                shape = new SBlock().getSBlock();
                break;
            case 5:
                shape = new LBlock().getLBlock();
                break;
            case 6:
                shape = new JBlock().getJBlock();
                break;
        }

    }

    public Node getShape () {
        return shape;
    }

    public void move(String dir){
        switch (dir) {
            case "LEFT":
                shape.setLayoutX(shape.getLayoutX()-TILE_SIZE);
                break;
            case "RIGHT":
                shape.setLayoutX(shape.getLayoutX()+TILE_SIZE);
                break;
            case "DOWN":
                shape.setLayoutY(shape.getLayoutY()+TILE_SIZE);
                break;
        }
    }

    public void rotate(){
        shape.setRotate(shape.getRotate()+90);

        switch((int) shape.getRotate()){
            case 90:
                shape.setLayoutX(shape.getLayoutX() + TILE_SIZE/2);
                shape.setLayoutY(shape.getLayoutY() + TILE_SIZE/2);
                break;
            case 180:
                shape.setLayoutX(shape.getLayoutX() - TILE_SIZE/2);
                shape.setLayoutY(shape.getLayoutY() + TILE_SIZE/2);
                break;
            case 270:
                shape.setLayoutX(shape.getLayoutX() - TILE_SIZE/2);
                shape.setLayoutY(shape.getLayoutY() - TILE_SIZE/2);
                break;
            case 360:
                shape.setLayoutX(shape.getLayoutX() + TILE_SIZE/2);
                shape.setLayoutY(shape.getLayoutY() - TILE_SIZE/2);
                shape.setRotate(0);
                break;
        }
    }

    public void drop() {
        shape.setLayoutY(19*TILE_SIZE);
    }
}
