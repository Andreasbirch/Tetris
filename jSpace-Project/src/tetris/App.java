package tetris;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import org.jspace.*;

import java.io.*;
import java.net.InetAddress;

public class App{
    public final int TILE_SIZE = 28;
    public final int WIDTH = 10;
    public final int HEIGHT = 20;
    private static Board board;
    private static View view, p2View;
    private static HeldView heldView;
    private static QueueView queueView1, queueView2;
    public static KeyCode moveLeftKey, moveRightKey, moveDownKey, rotateKey, dropKey;
    private static Label scoreLabel;
    private static Label linesClearedLabel;
    private static RemoteSpace server;
    private static Stage primaryStage;
    private static Scene scene;
    private static String ID;
    private static boolean multiplayer;
    private static String p2ID;
    private static P2Timer p2Timer;

    public App (Stage primaryStage) throws InterruptedException {
        this.multiplayer = multiplayer;
        this.primaryStage = primaryStage;

        if(multiplayer){
            p2Timer.play();
        }
//        if(multiplayer) {
//            Object[] t = server.get(new ActualField("START"), new FormalField(String.class));
//            if (t != null) {
//                p2ID = (String) t[1];
//                System.out.println(p2ID);
//            }
//        }

        VBox root = javaFXSetup();
        scene = new Scene(root, 1000, 800);
        Time timer = new Time(board);
//        timer.getTimeline().play();

        try {
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

                    if(multiplayer){
                        try {
                            server.put(ID, board.getBoardArray());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    updateView();
                    event.consume();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

        //queueView.updateQueueView(board);
        view.getView().requestFocus();
        updateView();
        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void updateP2View(int[][] ints) {
        if(p2View != null) {
            p2View.updateView(ints);
        }
    }

    public static void getTimerUpdate() throws InterruptedException {
        if(multiplayer) {
            server.put(ID, board.getBoardArray());
        }
    }

    public static void updateTimer() {
//        Time timer = new Time(board);
    }

    public static void updateView() {
        view.updateView(board.getBoardArray());
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

    public static void launchHost() throws IOException {
        //Sets ID to be equiv to ipAddress. Only works locally.
        multiplayer = true;
        ID = InetAddress.getLocalHost().getHostAddress();
        Space board = new SequentialSpace();
        new Thread(new GameServer(board)).start();
        String uri = "tcp://" + ID + ":9001/server?keep";
        server = new RemoteSpace(uri);
        p2Timer = new P2Timer(server);
    }

    private VBox javaFXSetup() throws InterruptedException {
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

        VBox heldViewBox = new VBox();
        Button pauseBtn = new Button("pause");
        Button newGameBtn = new Button("new");
        heldViewBox.setAlignment(Pos.CENTER);
        heldViewBox.setSpacing(30);
        heldViewBox.getChildren().addAll(heldView.getView(), pauseBtn, newGameBtn);

        if(multiplayer){
            p2View = new View(TILE_SIZE, WIDTH, HEIGHT);
            hBox.getChildren().addAll(heldViewBox, view.getView(), vBox, p2View.getView());
        } else {
            hBox.getChildren().addAll(heldViewBox, view.getView(), vBox);
        }

        hBox.setPrefSize(1000,600);
        hBox.setAlignment(Pos.CENTER);

        ImageView image = new ImageView();
        image.setImage(new Image("/tetris/res/TetrisLogo.png"));
        image.setLayoutX(50);
        image.setScaleX(0.5);
        image.setScaleY(0.5);

        Button backBtn = new Button("back");
        backBtn.setOnAction(e -> {
            try {
                backB();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        HBox headerBox = new HBox();
        headerBox.getChildren().addAll(backBtn, image);
        headerBox.setMargin(backBtn, new Insets(50, 50, 0, 50));



        VBox root = new VBox();
        root.setStyle("-fx-background-image: url(/tetris/res/BackgroundImage.jpg); -fx-background-repeat: repeat; -fx-background-size: cover, auto");
        root.setPrefSize(1000,800);
        root.getChildren().addAll(headerBox, hBox);
//        root.setMargin(bb, new Insets(60, 60,60,60));
//        root.setMargin(image, new Insets(0, 0,0,60));


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
            backBtn.setFont(f);
            pauseBtn.setFont(f);
            newGameBtn.setFont(f);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load font.");
            scoreL.setTextFill(Color.WHITE);
            scoreLabel.setTextFill(Color.WHITE);
            linesL.setTextFill(Color.WHITE);
            linesClearedLabel.setTextFill(Color.WHITE);
        }

        return root;
    }

    @FXML
    private void backB() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene mainMenuScene= new Scene(root);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    public static String getID() {
        return ID;
    }

    public static String getP2ID() {
        //Hardcoded, working on fix.
        return "MAC";
//        return p2ID;
    }

    public static void stop(){}
}
