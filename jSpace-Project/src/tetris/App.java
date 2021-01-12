package tetris;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App{
    public final int TILE_SIZE = 28;
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    private static Board board;
    private static View view;
    private static HeldView heldView;
    public static KeyCode moveLeftKey, moveRightKey, moveDownKey, rotateKey, dropKey;

    public App (Stage primaryStage) throws InterruptedException {

        board = new Board(TILE_SIZE, WIDTH, HEIGHT);
        view = new View(TILE_SIZE, WIDTH, HEIGHT);
        heldView = new HeldView(TILE_SIZE);

        HBox hBox = new HBox();

        hBox.setPrefSize(WIDTH*TILE_SIZE*2, HEIGHT*TILE_SIZE);
        hBox.getChildren().addAll(heldView.getView(), view.getView());

        Scene scene = new Scene(hBox, WIDTH*TILE_SIZE*2, HEIGHT*TILE_SIZE);
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
                if(event.getCode() == KeyCode.C) {
                    board.hold();
                    heldView.updateHeldView(board);
                }
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
