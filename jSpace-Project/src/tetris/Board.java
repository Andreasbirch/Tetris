package tetris;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int tile_size, width, height;
    private int posX, posY;
    private int deg;
    private int lineToClear;
    private int[][] boardArray;
    private List<Block> blockQueue = new ArrayList<Block>();
    private boolean canSwap;
    private Block currentBlock, heldBlock;
    private Block[] queue = new Block[6];
    private int score = 0;
    private int linesCleared = 0;
    public boolean gameOver = false;
    public boolean pause = false;


    public Board(int tile_size, int width, int height) throws InterruptedException{
        this.tile_size = tile_size;
        this.width = width;
        this.height = height;

        createBoardArray();

        generateQueue();
        generateNewBlock();

//        Space blockSpace = new RandomSpace();
//        new Thread(new BlockPusher(blockSpace)).start();
//        new Thread(new BlockPuller(blockSpace, this)).start();
    }


    public void generateNewBlock() {
        currentBlock = queue[0];
        canSwap = true;
        addBlock();
    }

    private void addBlock() {
        if(isClear((width/2)-2,0, 0) && !pause){
            insertStructureElement((width/2)-2, 0, 0);
            posX = (width/2)-2;
            posY = 0;
            deg = 0;
        } else {
            gameOver = true;
            gameOver = true;
            alertGameOver(linesCleared);
        }
    }

    public void generateQueue() {
        for (int i = 0; i < 6; i++) {
            queue[i] = new Block();
        }
    }

    public void addToQueue() {
        for (int i = 1; i < 6; i++) {
            queue[i - 1] = queue[i];
        }
        queue[5] = new Block();
    }

    public void hold() {
        if(canSwap) {
            canSwap = false;
            eraseStructureElement(posX, posY, deg);
            if(heldBlock != null) {
                Block tempBlock = currentBlock;
                currentBlock = heldBlock;
                heldBlock = tempBlock;
                addBlock();
            } else {
                heldBlock = currentBlock;
//                currentBlock = new Block();
                currentBlock = null;
                addToQueue();
                generateNewBlock();
            }
        }
    }


    private void insertStructureElement(int posX, int posY, int deg){
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0){
                    boardArray[y+posY][x+posX] = currentBlock.getRotations(deg)[y][x];
                }
            }
        }
    }

    private boolean isClear(int posX, int posY, int deg) {
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0 && boardArray[y+posY][x+posX] != 0){
                    return false;
                }
            }
        }
        return true;
    }


    private void createBoardArray() {
        boardArray = new int[height+1][width+2];
        for(int x = 0; x < height+1; x++) {
            for(int y = 0; y < width+2; y++) {
                if(x == height || y == 0 || y == width + 1) {
                    boardArray[x][y] = -1;
                } else {
                    boardArray[x][y] = 0;
                }
            }
        }
    }

    public int[][] getBoardArray () {
        return boardArray;
    }

    public void rotate() {
        eraseStructureElement(posX, posY, deg);
        if(isClear(posX, posY, (deg+1) % 4)){
            deg = (deg+1) % 4;
        }
        insertStructureElement(posX, posY, deg);
    }

    public void move(String dir) {
        if (!pause) {
            eraseStructureElement(posX, posY, deg);
            switch (dir) {
                case "LEFT":
                    if (isClear(posX - 1, posY, deg)) {
                        posX--;
                    }
                    insertStructureElement(posX, posY, deg);
                    break;
                case "RIGHT":
                    if (isClear(posX + 1, posY, deg)) {
                        posX++;
                    }
                    insertStructureElement(posX, posY, deg);
                    break;
                case "DOWN":
                    if (isClear(posX, posY + 1, deg)) {
                        posY++;
                        insertStructureElement(posX, posY, deg);
                        score++;
                    } else {
                        placeBlock();
                    }
                    break;
            }
        }
    }

    private void eraseStructureElement(int posX, int posY, int deg){
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0) {
                    boardArray[y+posY][x+posX] = 0;
                }
            }
        }
    }

    private void placeBlock(){
        if (!gameOver && !pause) {
            insertStructureElement(posX, posY, deg);

            while (checkLine()) {
                clearLine(lineToClear);
            }

            currentBlock = null;
            addToQueue();
            generateNewBlock();
            App.updateView();
        }
    }

    private boolean canDrop() {
        eraseStructureElement(posX, posY, deg);
        return isClear(posX, posY+1, deg);
    }

    public void drop() {
        while (canDrop()) {
            move("DOWN");
        }
        placeBlock();
    }

    private void clearLine(int lineNo) {
        for(int x = 1; x < width+1; x++) {
            boardArray[lineNo][x] = 0;
        }
        score += 200;
        linesCleared++;
        gravity(lineNo);
    }

    private void gravity(int lineNo) {
        for(int y = lineNo; y > 0; y--) {
            for (int x = 1; x < width + 1; x++) {
                boardArray[y][x] = boardArray[y-1][x];
            }
        }
    }

    private boolean checkLine() {
        for(int y = height-1; y >= 0; y--) {
            int fillCounter = 0;
            int emptyCounter = 0;
            for(int x = 1; x < width+1; x++) {
                if(boardArray[y][x] != 0){
                    fillCounter++;
                } else {
                    emptyCounter++;
                }
                if(fillCounter == 10) {
                    lineToClear = y;
                    return true;
                } else if(emptyCounter == 10) {
                    return false;
                }
            }
        }
        return false;
    }

    public Block getHeld() {
        return heldBlock;
    }

    public int getScore() {
        return score;
    }

    public int getLinesCleared() {
        return linesCleared;
    }

    public Block[] getQueue() { return queue; }

    public void alertGameOver(int clearedLines) {
        TextField name;

        //Skal nok over i view som FXML fil?
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Game over");
        dialog.setHeaderText("You cleared " + clearedLines + " lines!");

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ok);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        name = new TextField();
        name.setPromptText("Your name");

        grid.add(new Label("Please enter your name"), 0, 0);
        grid.add(name, 1, 0);

        Node okButton = dialog.getDialogPane().lookupButton(ok);
        okButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> name.requestFocus());

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == ok) {
                gameOver(name.getText());
                dialog.close();
            }
            return null;
        });

        dialog.show();
    }

    public void gameOver(String name) {

        Space channelUserScoreboard = new SequentialSpace();
        Space channelScoreboardUser = new SequentialSpace();

        try {
            channelUserScoreboard.put(name, score);

            if (checkScore(score)) {
                channelScoreboardUser.put("new high score");
                addToScoreBoard(name, score);
            } else {
                channelScoreboardUser.put("not a high score");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkScore(int newScore) {
        HighScore highScore = new HighScore();
        return highScore.isHighScore(newScore);
    }

    public void addToScoreBoard(String name, int score) {
        HighScore highScore = new HighScore();
        String newScore = score + "";
        highScore.addHighScore(name, newScore);
    }
}
