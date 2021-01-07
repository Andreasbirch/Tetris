package tetris;

import javafx.application.Application;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.Random;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        Pane root = new Pane();
        root.getChildren().add(btn);
        root.getChildren().add(new BackgroundTile());

        //Programatically create background
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 10; x++){
                BackgroundTile tile = new BackgroundTile();
                tile.setLayoutX(28.*x);
                tile.setLayoutY(28.*y);
                root.getChildren().add(tile);
            }
        }

        Straight straight = new Straight();
        root.getChildren().add(straight);

        Scene scene = new Scene(root, 280, 560);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A) {straight.moveLeft();}
                if(event.getCode() == KeyCode.D) {straight.moveRight();}
                if(event.getCode() == KeyCode.S) {straight.moveDown();}
                if(event.getCode() == KeyCode.W) {straight.rotate();}

                event.consume();
            }
        });



        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}