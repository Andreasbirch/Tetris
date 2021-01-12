package tetris;

public class Board {
    private int tile_size, width, height;
    private int posX, posY;
    private int deg;
    private int lineToClear;
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
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                if(currentBlock.getRotations(deg)[y][x] != 0) {
                    boardArray[y+posY][x+posX] = 0;
                }
            }
        }
    }

    private void placeBlock(){
        insertStructureElement(posX, posY, deg);

        while(checkLine()){
            clearLine(lineToClear);
        }

        currentBlock = null;
        addNewBlock();
        App.updateView();
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
}
