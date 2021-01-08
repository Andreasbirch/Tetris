package tetris;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class App {
    final int TILE_SIZE = 28;
    public static int[][] data;

    public App (Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 280, 560);

        data = new int[10][20];

        //Programatically create background
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 10; x++){
                BackgroundTile tile = new BackgroundTile();
                tile.setLayoutX(28.*x);
                tile.setLayoutY(28.*y);
                root.getChildren().add(tile);
            }
        }

        generateBlock(root, scene);

        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void generateBlock(Pane root, Scene scene) {
        Block block = new Block();
        root.getChildren().add(block.getShape());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A) {block.move("LEFT");}
                if(event.getCode() == KeyCode.D) {block.move("RIGHT");}
                if(event.getCode() == KeyCode.S) {block.move("DOWN");}
                if(event.getCode() == KeyCode.W) {block.rotate();}
                if(event.getCode() == KeyCode.SPACE) {
                    block.drop();
                    generateBlock(root, scene);
                }

                event.consume();
            }
        });
    }
}