package tetris2.data;

import javafx.scene.Node;
import tetris2.data.Board;

public class IBlock {
    private int[][] structureElement;
    private int rotation = 90;
    private static Board board;
    private static int posX;
    private static int posY;

    public IBlock(Board board) {
        posX = 4;
        posY = 0;
        this.board = board;
        rotations(rotation);
    }

    public static void move(String dir){
        switch (dir) {
            case "LEFT":
                posX--;
                break;
            case "RIGHT":
                posX++;
                break;
            case "DOWN":
                posY++;
                break;
        }

    }


    public void rotations(int rotation) {
        switch(rotation) {
            case 0:
                structureElement = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0}
                };
                break;
            case 90:
                structureElement = new int[][]{
                        {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 180:
                structureElement = new int[][]{
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0}
                };
                break;
            case 270:
                structureElement = new int[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 0, 0, 0}
                };
                break;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int[][] getStructureElement() {
        return structureElement;
    }
}
