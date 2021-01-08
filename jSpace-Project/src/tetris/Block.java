package tetris;

import tetris.GameData;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import tetris.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block extends Rectangle{
    Shape shape = null;

    final int TILE_SIZE = 28;

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
//        if(!shape.getClass().toString().contains("Rectangle")) {
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
//        }
    }

    public boolean checkCollision (Shape shape) {
        for(Shape target: GameData.getShapeList()) {
            if(!Shape.intersect(shape, target).getLayoutBounds().isEmpty()) {
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
        return collision;
    }

    public boolean checkLine() {
        Rectangle tile = new Rectangle();
        tile.setWidth(TILE_SIZE);
        tile.setHeight(TILE_SIZE);

        int correctCollisions = 0;

        for(int y = 19; y >= 0; y--){
            for(int x = 0; x < 10; x++) {
                tile.setLayoutY(y*TILE_SIZE);
                tile.setLayoutX(x*TILE_SIZE);
                if(checkCollision(tile)){
                    correctCollisions++;
                }
            }
            if(correctCollisions == 10){
                System.out.println("CLEAR");
                clearLine(y);
                return true;
            } else if(correctCollisions == 0) {
                return false;
            }
            correctCollisions = 0;
        }
        return false;
    }

    public void clearLine(int lineNo) {
        Rectangle tile = new Rectangle();
        tile.setWidth(TILE_SIZE);
        tile.setHeight(TILE_SIZE);


        tile.setLayoutY(lineNo*TILE_SIZE);
        App.tileLine(lineNo);
        for(int x = 0; x < 10; x++) {
            tile.setLayoutX(x*TILE_SIZE);
            GameData.removeShape(tile);
            mergeUpperShape(lineNo);
        }
    }

    private void mergeUpperShape(int yThreshold) {
        for(Shape listShape: GameData.getShapeList()) {
            if(listShape.getLayoutY() < yThreshold){
                listShape.setFill(Color.WHITE);
//                listShape.setLayoutY(listShape.getLayoutY()+TILE_SIZE);
            }
        }
    }

    public void drop() {
        while(!premoveCollision(shape, "DOWN"))
            move("DOWN");
        if(GameData.getShapeList().size() > 1) {
            GameData.setTotalMass(Shape.union(GameData.getTotalMass(), shape));
        } else {
            GameData.setTotalMass(shape);
        }
        GameData.getShapeList().add(shape);

        checkLine();
    }

}
