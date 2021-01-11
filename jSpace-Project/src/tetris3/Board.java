package tetris3;

import org.jspace.*;

import java.util.Random;

public class Board {
    private int tile_size;
    private int width;
    private int height;
    private int[][] boardArray;
    private int[] currentBlockCoords = new int[2];
    Random random = new Random();
    private TBlock currentBlock;


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
        currentBlock = (TBlock) generateBlock();
        if(isClear((width/2)-2,0)){
            insertStructureElement((width/2)-2, 0);
            printBoard();
        }
    }


    private Object generateBlock(){
        int randomBlock = random.nextInt(1);
        switch (randomBlock){
            case 0:
                return new TBlock();
        }
        return null;
    }

    private void insertStructureElement(int posX, int posY){
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getStructureElement()[y][x] != 0){
                    boardArray[y+posY][x+posX] = currentBlock.getStructureElement()[y][x];
                }
            }
        }
    }

    private boolean isClear(int posX, int posY) {
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getStructureElement()[x][y] != 0 && boardArray[x+posX][y+posY] != 0){
                    return false;
                }
            }
        }
        return true;
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
        for(int x = 0; x < height; x++) {
            for(int y = 0; y < width; y++) {
                System.out.print(boardArray[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

}
