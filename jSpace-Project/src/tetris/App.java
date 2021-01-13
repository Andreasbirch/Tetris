package tetris;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

        VBox scoreBox = new VBox();
        scoreBox.setAlignment(Pos.CENTER);
        Label scoreL = new Label("SCORE");
        scoreLabel = new Label("0");
        Label linesL = new Label("LINES");
        linesClearedLabel = new Label("0");
        scoreBox.getChildren().addAll(scoreL, scoreLabel, linesL, linesClearedLabel);


        HBox hBox = new HBox();

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        hBox.setSpacing(30);
        vBox.getChildren().addAll(queueView1.getView(), queueView2.getView(), scoreBox);
        hBox.getChildren().addAll(heldView.getView(), view.getView(), vBox);


        hBox.setPrefSize(600,600);
        hBox.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setStyle("-fx-background-image: url(/tetris/res/BackgroundImage.jpg); -fx-background-repeat: repeat; -fx-background-size: cover, auto");
        ImageView image = new ImageView();
        image.setImage(new Image("/tetris/res/TetrisLogo.png"));
        image.setLayoutX(50);
        image.setScaleX(0.5);
        image.setScaleY(0.5);

        root.setPrefSize(600,800);
        root.getChildren().addAll(image, hBox);
        root.setMargin(image, new Insets(0, 0,0,60));
        Scene scene = new Scene(root, 600, 800);


        final Font f;
        try {
            f = Font.loadFont(new FileInputStream(new File("src\\tetris\\res\\PressStart2P-Regular.ttf")),18);
            scoreL.setFont(f);
            scoreL.setTextFill(Color.WHITE);
            scoreLabel.setFont(f);
            scoreLabel.setTextFill(Color.WHITE);
            linesL.setFont(f);
            linesL.setTextFill(Color.WHITE);
            linesClearedLabel.setFont(f);
            linesClearedLabel.setTextFill(Color.WHITE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
