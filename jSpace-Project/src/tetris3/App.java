package tetris3;

import tetris3.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public final int TILE_SIZE = 28;
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
//    private Board board;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Board board = new Board(TILE_SIZE, WIDTH, HEIGHT);
        View view = new View(TILE_SIZE, WIDTH, HEIGHT);
        Scene scene = new Scene(view.getView(), WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {board.move("LEFT");}
                if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {board.move("RIGHT");}
                if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {board.move( "DOWN");}
                if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {board.rotate();}
//                if(event.getCode() == KeyCode.SPACE) {
//                    block.drop(block.getShape());
//                    generateBlock(root, scene);
//                }
                view.updateView(board);
                event.consume();
            }
        });
        view.updateView(board);
        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
