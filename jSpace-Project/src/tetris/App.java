package tetris;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    static final int TILE_SIZE = 28;
//    public static List <Shape> shapeList = new ArrayList<Shape>();
//    public static Shape totalMass;
    private static Pane root;

    public App (Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, 280, 560);

        //Programatically create background
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 10; x++){
                BackgroundTile tile = new BackgroundTile();
                tile.setLayoutX(TILE_SIZE * x);
                tile.setLayoutY(TILE_SIZE * y);
                root.getChildren().add(tile);
            }
        }
        //Vi skal lige overveje om det her kan være en permanent løsning til bunden.
        Shape bottomBorder = new BottomBorder().getBottomBorder();
        root.getChildren().add(bottomBorder);

        GameData.getShapeList().add(bottomBorder);
        //Alternativ collision checking
        GameData.setTotalMass(bottomBorder);


        generateBlock(root, scene);

        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void generateBlock(Pane root, Scene scene) {
        Block block = new Block();

        root.getChildren().add(block.getShape());
        Time timer = new Time(block);

        timer.getTimeline().play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {block.move("LEFT");}
                if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {block.move("RIGHT");}
                if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {block.move("DOWN");}
                if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {block.rotate();}
                if(event.getCode() == KeyCode.SPACE) {
                    block.drop();
                    generateBlock(root, scene);
                }

                event.consume();
            }
        });
    }

    public static void boundBox(int posY) {
        Rectangle bounds = new Rectangle();
        bounds.setWidth(10*TILE_SIZE);
        bounds.setHeight(posY*TILE_SIZE);
        bounds.setFill(Color.WHITE);
        bounds.setLayoutX(0);
        root.getChildren().add(bounds);
    }

    public static void tileLine(int posY) {
        for(int x = 0; x < 10; x++) {
            BackgroundTile tile = new BackgroundTile();
            tile.setLayoutX(TILE_SIZE * x);
            tile.setLayoutY(TILE_SIZE * posY);
            root.getChildren().add(tile);
        }
    }

}