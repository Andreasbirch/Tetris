package tetris;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

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




        Scene scene = new Scene(root, 280, 560);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}