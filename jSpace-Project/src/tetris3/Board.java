package tetris3;

public class Board {
    private int tile_size, width, height;
    private int posX, posY;
    private int deg;
    private int[][] boardArray;
    private Block currentBlock;

    private int boardC, addNewBlockC, insertStructureElementC, isClearC, getCurrentBlockC, createBoardArrayC, getBoardArrayC, rotateC, moveC, eraseStructureElementC, placeBlockC, canDropC, dropC;

    public Board(int tile_size, int width, int height) throws InterruptedException{
        boardC++;
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
        addNewBlockC ++;
        currentBlock = new Block();
        if(isClear((width/2)-2,0, 0)){
            insertStructureElement((width/2)-2, 0, 0);
            posX = (width/2)-2;
            posY = 0;
            deg = 0;
        } else {
            System.out.println("Game over.");
        }
    }


    private void insertStructureElement(int posX, int posY, int deg){
        insertStructureElementC ++;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0){
                    boardArray[y+posY][x+posX] = currentBlock.getRotations(deg)[y][x];
                }
            }
        }
    }

    private boolean isClear(int posX, int posY, int deg) {
        isClearC ++;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0 && boardArray[y+posY][x+posX] != 0){
                    return false;
                }
            }
        }
        return true;
    }


//    public Block getCurrentBlock() {
//        getCurrentBlockC ++;
//        return currentBlock;
//    }


    private void createBoardArray() {
        createBoardArrayC ++;
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
        getBoardArrayC ++;
        return boardArray;
    }

    public void rotate() {
        rotateC ++;
        eraseStructureElement(posX, posY, deg);
        if(isClear(posX, posY, (deg+1) % 4)){
            deg = (deg+1) % 4;
        }
        insertStructureElement(posX, posY, deg);
    }

    public void move(String dir) {
        moveC ++;
        eraseStructureElement(posX, posY, deg);
        switch (dir) {
            case "LEFT":
                if(isClear(posX-1, posY, deg)){
                    posX --;
                }
                insertStructureElement(posX, posY, deg);
                break;
            case "RIGHT":
                if(isClear(posX+1, posY, deg)){
                    posX ++;
                }
                insertStructureElement(posX, posY, deg);
                break;
            case "DOWN":
                if(isClear(posX, posY+1, deg)){
                    posY++;
                    insertStructureElement(posX, posY, deg);
                } else {
                    placeBlock();
                }
                break;
        }
    }

    private void eraseStructureElement(int posX, int posY, int deg){
        eraseStructureElementC ++;
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0) {
                    boardArray[y+posY][x+posX] = 0;
                }
            }
        }
    }

    private void placeBlock(){
        placeBlockC ++;
        insertStructureElement(posX, posY, deg);
        currentBlock = null;
        System.gc();
        addNewBlock();
        App.updateView();
    }

    private boolean canDrop() {
        canDropC ++;
        eraseStructureElement(posX, posY, deg);
        return isClear(posX, posY+1, deg);
    }

    public void drop() {
        dropC ++;
        while (canDrop()) {
            move("DOWN");
        }
        placeBlock();
    }

    public void printCalls() {
        System.out.println("Board: " + boardC);
        System.out.println("addNewBlock: " + addNewBlockC);
        System.out.println("insertStructureElement: " + insertStructureElementC);
        System.out.println("isClear: " + isClearC);
        System.out.println("getCurrentBlock: " + getCurrentBlockC);
        System.out.println("createBoardArray: " + createBoardArrayC);
        System.out.println("getBoardArray: " + getBoardArrayC);
        System.out.println("rotate: " + rotateC);
        System.out.println("move: " + moveC);
        System.out.println("eraseStructureElement: " + eraseStructureElementC);
        System.out.println("placeBlock: " + placeBlockC);
        System.out.println("canDrop: " + canDropC);
        System.out.println("Drop: " + dropC);
        System.out.println("CurrentBlock" + currentBlock);
    }
}
