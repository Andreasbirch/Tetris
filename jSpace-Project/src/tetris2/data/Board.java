package tetris2.data;

public class Board {
    private final int WIDTH = 10;
    private final int HEIGHT = 20;
    private final int TILE_SIZE = 28;
    public int[][] boardGrid = new int[WIDTH][HEIGHT];
    public IBlock currBlock = new IBlock(this);

    public Board() {
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                boardGrid[x][y] = 0;
            }
        }
    }

    public void addBlock(){
        if(placeable(currBlock.getPosX(), currBlock.getPosY())) {
            for(int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    boardGrid[((WIDTH/2)-2)+x][y] = currBlock.getStructureElement()[x][y];
                }
            }
        }
    }

    public void moveBlock(String dir){
        int posX = currBlock.getPosX();
        int posY = currBlock.getPosY();
        int[][] structureElement = currBlock.getStructureElement();
        switch(dir) {
            case "LEFT":
                if(placeable(posX-1, currBlock.getPosY())) {
                    for(int y = 0; y < 4; y++) {
                        for (int x = 0; x < 4; x++) {
                            if(structureElement[x][y] != 0) {
                                boardGrid[posX+x][posY+y] = structureElement[x][y];
                            }
                            if(x == 3 && structureElement[x][y] != 0){
                                boardGrid[posX+x][posY+y] = 0;
                            }
                        }
                    }
                }
                currBlock.move("LEFT");
                break;
            case "RIGHT":
                if(placeable(currBlock.getPosX()+1, currBlock.getPosY())) {
                    for(int y = 0; y < 4; y++) {
                        for (int x = 0; x < 4; x++) {
                            if(currBlock.getStructureElement()[x][y] != 0) {
                                boardGrid[(((WIDTH/2)-2)+x)+1][y] = currBlock.getStructureElement()[x][y];
                            }
                            if(y == 0 && currBlock.getStructureElement()[x][y] != 0){
                                boardGrid[(((WIDTH/2)-2)+x)][y] = 0;
                            }
                        }
                    }
                }
                currBlock.move("RIGHT");
                break;
            case "DOWN":
                if(placeable(currBlock.getPosX(), currBlock.getPosY()+1)) {
                    for(int y = 0; y < 4; y++) {
                        for (int x = 0; x < 4; x++) {
                            if(currBlock.getStructureElement()[x][y] != 0) {
                                boardGrid[(((WIDTH/2)-2)+x)][y+1] = currBlock.getStructureElement()[x][y];
                            }
                            if(x == 3 && currBlock.getStructureElement()[x][y] != 0){
                                boardGrid[(((WIDTH/2)-2)+x)][y] = 0;
                            }
                        }
                    }
                }
                currBlock.move("DOWN");
                break;
        }
    }

    private boolean placeable(int posX, int posY) {
        for(int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(posX >= 0 && posX < WIDTH && posY >= 0 && posY < HEIGHT){
                    if(currBlock.getStructureElement()[x][y] != 0 && boardGrid[posX+x][posY+y] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                System.out.print(boardGrid[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
