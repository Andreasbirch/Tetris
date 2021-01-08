package tetris;


import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block extends Rectangle{
    Shape shape = null;

    final int TILE_SIZE = 28;
    public Time timer;

    public Block() {

        Random random = new Random();
        int randomBlock = random.nextInt(7);

        switch (randomBlock){
            case 0:
                shape = new Straight().getStraight();
                break;
            case 1:
                shape = new Square().getSquare();
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
                if(!premoveCollision(shape, "LEFT")) {
                    shape.setLayoutX(shape.getLayoutX()-TILE_SIZE);
                    break;
                }
            case "RIGHT":
                if(!premoveCollision(shape, "RIGHT")) {
                    shape.setLayoutX(shape.getLayoutX()+TILE_SIZE);
                    break;
                }
            case "DOWN":
                if(!premoveCollision(shape, "DOWN")) {
                    shape.setLayoutY(shape.getLayoutY()+TILE_SIZE);
                    break;
                }
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

<<<<<<< HEAD
    public void drop() {
        shape.setLayoutY(19*TILE_SIZE);
=======
    public boolean checkCollision (Shape shape) {
        for(Shape target: App.shapeList) {
            if(!Shape.intersect(shape, target).getLayoutBounds().isEmpty()) {
                System.out.println("Collision");
                return true;
            }
        }
        return false;
    }


    private boolean premoveCollision(Shape shape, String dir) {
        boolean collision = false;
        switch (dir) {
            case "LEFT":
                shape.setLayoutX(shape.getLayoutX()-TILE_SIZE);
                collision = checkCollision(shape);
                shape.setLayoutX(shape.getLayoutX()+TILE_SIZE);
                break;
            case "RIGHT":
                shape.setLayoutX(shape.getLayoutX()+TILE_SIZE);
                collision = checkCollision(shape);
                shape.setLayoutX(shape.getLayoutX()-TILE_SIZE);
                break;
            case "DOWN":
                shape.setLayoutY(shape.getLayoutY()+TILE_SIZE);
                collision = checkCollision(shape);
                shape.setLayoutY(shape.getLayoutY()-TILE_SIZE);
                break;
        }
        if(collision) {System.out.println("Collided");}
        return collision;
    }

    public void drop() {
        while(!premoveCollision(shape, "DOWN"))
            move("DOWN");
        App.shapeList.add(shape);
>>>>>>> 957ee064fdd6a457af5fdcd1d9dd48a45ab42556
    }

}
