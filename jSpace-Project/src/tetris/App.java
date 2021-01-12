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
    public static KeyCode moveLeftKey, moveRightKey, moveDownKey, rotateKey, dropKey;

    public App (Stage primaryStage) throws InterruptedException {

            board = new Board(TILE_SIZE, WIDTH, HEIGHT);
            view = new View(TILE_SIZE, WIDTH, HEIGHT);
            Scene scene = new Scene(view.getView(), WIDTH*TILE_SIZE, HEIGHT*TILE_SIZE);
            Time timer = new Time(board);
            timer.getTimeline().play();
            
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    //ORs are temporary fix, remove when possible
                    if(event.getCode() == moveLeftKey || event.getCode() == KeyCode.LEFT) {board.move("LEFT");}
                    if(event.getCode() == moveRightKey || event.getCode() == KeyCode.RIGHT) {board.move("RIGHT");}
                    if(event.getCode() == moveDownKey || event.getCode() == KeyCode.DOWN) {board.move( "DOWN");}
                    if(event.getCode() == rotateKey || event.getCode() == KeyCode.UP) {board.rotate();}
                    if(event.getCode() == dropKey || event.getCode() == KeyCode.SPACE) {board.drop();}
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

    public static void setKeys(String moveLeftKeyS, String moveRightKeyS, String moveDownKeyS, String rotateKeyS, String dropKeyS) {
        moveLeftKey = KeyCode.getKeyCode(moveLeftKeyS);
        moveRightKey = KeyCode.getKeyCode(moveRightKeyS);
        moveDownKey = KeyCode.getKeyCode(moveDownKeyS);
        rotateKey = KeyCode.getKeyCode(rotateKeyS);
        dropKey = KeyCode.getKeyCode(dropKeyS);
    }
}
