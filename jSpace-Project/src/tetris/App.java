package tetris;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.jspace.*;

import java.io.*;
import java.net.InetAddress;
import java.util.Random;

public class App{
    public final int TILE_SIZE = 28, WIDTH = 10, HEIGHT = 20;
    private static int score = 0, linesCleared = 0;
    private static boolean multiplayer, isClient;
    private static Board board;
    private static View view, p2View;
    private static HeldView heldView;
    private static QueueView queueView1, queueView2;
    public static KeyCode moveLeftKey, moveRightKey, moveDownKey, rotateKey, dropKey;
    private static RemoteSpace server;
    private static String ID, p2ID, serverID;
    private static Time timer;
    private static long blockSeed = new Random().nextLong();

    public App () throws InterruptedException {
        initializations();
        multiplayer();
        updateView();
    }

    private void initializations() {
        board = new Board(TILE_SIZE, WIDTH, HEIGHT, blockSeed);
        view = new View(TILE_SIZE, WIDTH, HEIGHT);
        heldView = new HeldView(TILE_SIZE);
        queueView1 = new QueueView(TILE_SIZE, 1);
        queueView2 = new QueueView(TILE_SIZE, 2);


        timer = new Time(board);
        board.pause = false;
        timer.getTimeline().play();
        view.getView().requestFocus();
    }
    private void multiplayer() throws InterruptedException {
        if(multiplayer){
            Object[] seedT = server.query(new ActualField("SEED"), new FormalField(Long.class));
            this.blockSeed = (long) seedT[1];
            //Following code only applicable for p2p services
            p2View = new View(TILE_SIZE, WIDTH, HEIGHT);
            if(isClient) {
                server.put("CLIENTSTART", ID);
                p2ID = serverID;
                Object[] t = server.query(new ActualField("HOSTSTART"), new FormalField(String.class));
                p2ID = (String) t[1];
            } else {
                server.put("HOSTSTART", ID);
                serverID = ID;
                Object[] t = server.query(new ActualField("CLIENTSTART"), new FormalField(String.class));
                p2ID = (String) t[1];
            }
            new Thread(new Player2(ID,p2ID,serverID)).start();
        }
    }

    public static void hostGame() throws IOException {
        //Sets ID to be equiv to ipAddress. Only works locally.
        multiplayer = true;
        ID = InetAddress.getLocalHost().getHostAddress();
        new Thread(new GameServer()).start();
        String uri = "tcp://" + ID + ":9001/server?keep";
        server = new RemoteSpace(uri);
    }
    public static void joinGame(String serverIP) throws IOException, InterruptedException {
        multiplayer = true;
        isClient = true;
        serverID = serverIP;
        String uri = "tcp://" + serverIP + ":9001/server?keep";
        ID = InetAddress.getLocalHost().getHostAddress();
        server = new RemoteSpace(uri);
        System.out.println("Joined server on IP " + serverIP);
        server.put("CONNECTED", ID);
    }
    public static void parseInput(KeyEvent event) {
        if(event.getCode() == moveLeftKey || event.getCode() == KeyCode.LEFT) {board.move("LEFT");}
        if(event.getCode() == moveRightKey || event.getCode() == KeyCode.RIGHT) {board.move("RIGHT");}
        if(event.getCode() == moveDownKey || event.getCode() == KeyCode.DOWN) {board.move( "DOWN");}
        if(event.getCode() == rotateKey || event.getCode() == KeyCode.UP) {board.rotate();}
        if(event.getCode() == dropKey || event.getCode() == KeyCode.SPACE) {board.drop();}
        if(event.getCode() == KeyCode.C) {
            board.hold();
            heldView.updateHeldView(board);
        }

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
    public static void pauseGame() {
        board.pause = true;
        timer.getTimeline().pause();
        pauseAlert();
    }
    public static void pauseGameNoAlert() {
        board.pause = true;
        timer.getTimeline().pause();
    }

    //Getters
    public static String getScore() { return String.valueOf(score); }
    public static String getLinesCleared() { return String.valueOf(linesCleared); }
    public static Pane getHeldView() { return heldView.getView(); }
    public static Pane getGameView() { return view.getView(); }
    public static Pane getQueueView1() { return queueView1.getView(); }
    public static Pane getQueueView2() { return queueView2.getView(); }
    public static void getTimerUpdate() throws InterruptedException {
        if(multiplayer) {
            server.put(ID, board.getBoardArray());
        }
    }
    public static boolean getMultiplayer() { return multiplayer; }
    public static Pane getP2ViewPane() {
        return p2View.getView();
    }

    //Setters
    public static void setScore(int sc) { score = sc; }
    public static void setLinesCleared(int LC) { linesCleared = LC; }
    public static void updateView() {
        view.updateView(board.getBoardArray());
        queueView1.updateQueueView(board);
        queueView2.updateQueueView(board);
    }
    public static void updateP2View(int[][] boardArray) {
        p2View.updateView(boardArray);
    }
    public static void setKeys(String moveLeftKeyS, String moveRightKeyS, String moveDownKeyS, String rotateKeyS, String dropKeyS) {
        moveLeftKey = KeyCode.getKeyCode(moveLeftKeyS);
        moveRightKey = KeyCode.getKeyCode(moveRightKeyS);
        moveDownKey = KeyCode.getKeyCode(moveDownKeyS);
        rotateKey = KeyCode.getKeyCode(rotateKeyS);
        dropKey = KeyCode.getKeyCode(dropKeyS);
    }

    //Alerts
    public static void pauseAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Game paused");
        alert.setContentText("Press button to unpause");

        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {
                board.pause = false;
                timer.getTimeline().play();
                view.getView().requestFocus();
            } else {}
        });
    }
}
