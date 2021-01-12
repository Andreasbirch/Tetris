package tetris;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App{
    public final int TILE_SIZE = 28;
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    private static Board board;
    private static View view;

    public App (Stage primaryStage) throws InterruptedException {

            board = new Board(TILE_SIZE, WIDTH, HEIGHT);
            view = new View(TILE_SIZE, WIDTH, HEIGHT);
            Scene scene = new Scene(view.getView(), WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {board.move("LEFT");}
                    if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {board.move("RIGHT");}
                    if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {board.move( "DOWN");}
                    if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {board.rotate();}
                    if(event.getCode() == KeyCode.SPACE) {board.drop();}
                    if(event.getCode() == KeyCode.O) {board.printCalls();}
                    updateView();
                    event.consume();

                }
            });
            updateView();
            primaryStage.setTitle("Tetris!");
            primaryStage.setScene(scene);
            primaryStage.show();
    }


    public static void updateView() {
        view.updateView(board);
    }
}
