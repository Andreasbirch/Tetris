package tetris3;

import org.jspace.*;

import java.util.Random;

public class Board {
    private int tile_size, width, height;
    private int posX, posY;
    private int deg;
    private int[][] boardArray;
    private Block currentBlock;

    public Board(int tile_size, int width, int height) throws InterruptedException{
        this.tile_size = tile_size;
        this.width = width;
        this.height = height;

        createBoardArray();

        addNewBlock();
//        Space blockSpace = new RandomSpace();
//        new Thread(new BlockPusher(blockSpace)).start();
//        new Thread(new BlockPuller(blockSpace, this)).start();
    }


    public void addNewBlock() {
        currentBlock = new Block();
        if(isClear((width/2)-2,0, 0)){
            insertStructureElement((width/2)-2, 0, 0);
            posX = (width/2)-2;
            posY = 0;
            deg = 0;
            printBoard();
        } else {
            System.out.println("Game over.");
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
                if(currentBlock.getRotations(deg)[x][y] != 0 && boardArray[x+posX][y+posY] != 0){
                    System.out.println("Not clear.");
                    return false;
                }
            }
        }
        return true;
    }


    public Block getCurrentBlock() {
        return currentBlock;
    }


    private void createBoardArray() {
        boardArray = new int[height][width];
        for(int x = 0; x < height; x++) {
            for(int y = 0; y < width; y++) {
                boardArray[x][y] = 0;
            }
        }
    }

    public int[][] getBoardArray () {
        return boardArray;
    }

    private void printBoard() {
//        for(int x = 0; x < height; x++) {
//            for(int y = 0; y < width; y++) {
//                System.out.print(boardArray[x][y] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
    }

    public void rotate() {
        if(isClear(posX, posY, (deg+1) % 4)){
            eraseStructureElement(posX, posY, deg);
            deg = (deg+1) % 4;
            insertStructureElement(posX, posY, deg);
        }
    }

    public void move(String dir) {
        switch (dir) {
            case "LEFT":
                if(isClear(posX-1, posY, deg)){
                    eraseStructureElement(posX, posY, deg);
                    insertStructureElement(posX-1, posY, deg);
                    posX --;
                    printBoard();
                }
                break;
            case "RIGHT":
                if(isClear(posX+1, posY, deg)){
                    eraseStructureElement(posX, posY, deg);
                    insertStructureElement(posX+1, posY, deg);
                    posX ++;
                    printBoard();
                }
                break;
            case "DOWN":
                if(isClear(posX, posY+1, deg)){
                    eraseStructureElement(posX, posY, deg);
                    insertStructureElement(posX, posY+1, deg);
                    posY ++;
                    printBoard();
                }
                break;
        }
    }

    private void eraseStructureElement(int posX, int posY, int deg){
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(getCurrentBlock().getRotations(deg)[y][x] != 0) {
                    boardArray[y+posY][x+posX] = 0;
                }
            }
        }
    }
}
