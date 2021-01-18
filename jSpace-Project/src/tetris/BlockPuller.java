package tetris;

import org.jspace.FormalField;
import org.jspace.Space;
import tetris.Blocks.TBlock;

public class BlockPuller implements Runnable {
    private Space blockSpace;
    private Board board;

    public BlockPuller(Space blockSpace, Board board) {
        this.board = board;
        this.blockSpace = blockSpace;
    }

    @Override
    public void run() {
        try {
            if(!blockSpace.queryp(new FormalField(TBlock.class)).equals(null)){
                Object[] tBlock = blockSpace.get(new FormalField(TBlock.class));
            } else {
                System.out.println("BlockPuller failed on query.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
