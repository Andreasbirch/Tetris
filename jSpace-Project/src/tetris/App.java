package tetris;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App{
    public final int TILE_SIZE = 28;
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    private static Board board;
    private static View view;
    private static HeldView heldView;
    private static QueueView queueView1, queueView2;
    public static KeyCode moveLeftKey, moveRightKey, moveDownKey, rotateKey, dropKey;
    private static Label scoreLabel;
    private static Label linesClearedLabel;
    public App (Stage primaryStage) throws InterruptedException {

        board = new Board(TILE_SIZE, WIDTH, HEIGHT);
        view = new View(TILE_SIZE, WIDTH, HEIGHT);
        heldView = new HeldView(TILE_SIZE);
        queueView1 = new QueueView(TILE_SIZE, 1);
        queueView2 = new QueueView(TILE_SIZE, 2);
        scoreLabel = new Label("0");
        linesClearedLabel = new Label("0");

        HBox hBox = new HBox();

        hBox.setPrefSize(WIDTH*TILE_SIZE*2, HEIGHT*TILE_SIZE);

        VBox vBox = new VBox();

        vBox.getChildren().addAll(queueView1.getView(), queueView2.getView(), scoreLabel, linesClearedLabel);
        hBox.getChildren().addAll(heldView.getView(), view.getView(), vBox);

        Scene scene = new Scene(hBox, WIDTH*TILE_SIZE*2, HEIGHT*TILE_SIZE);
        Time timer = new Time(board);
//        timer.getTimeline().play();

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
                //queueView.updateQueueView(board);
                updateView();
                event.consume();

            }
        });
        //queueView.updateQueueView(board);
        updateView();
        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void updateTimer() {
//        Time timer = new Time(board);
    }

    public static void updateView() {
        view.updateView(board);
        queueView1.updateQueueView(board);
        queueView2.updateQueueView(board);
        scoreLabel.setText(String.valueOf(board.getScore()));
        linesClearedLabel.setText(String.valueOf(board.getLinesCleared()));
    }

    public static void setKeys(String moveLeftKeyS, String moveRightKeyS, String moveDownKeyS, String rotateKeyS, String dropKeyS) {
        moveLeftKey = KeyCode.getKeyCode(moveLeftKeyS);
        moveRightKey = KeyCode.getKeyCode(moveRightKeyS);
        moveDownKey = KeyCode.getKeyCode(moveDownKeyS);
        rotateKey = KeyCode.getKeyCode(rotateKeyS);
        dropKey = KeyCode.getKeyCode(dropKeyS);
    }
}
