package tetris3;

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
                if(getCurrentBlock().getRotations(deg)[y][x] != 0) {
                    boardArray[y+posY][x+posX] = 0;
                }
            }
        }
    }

    private void placeBlock(){
        insertStructureElement(posX, posY, deg);
        addNewBlock();
        App.updateView();
    }

    private boolean canDrop() {
        eraseStructureElement(posX, posY, deg);
        if(isClear(posX, posY + 1, deg)) {
            return true;
        } else {
            return false;
        }
    }

    public void drop() {
        while (canDrop()) {
            move("DOWN");
        }
        placeBlock();
    }
}
