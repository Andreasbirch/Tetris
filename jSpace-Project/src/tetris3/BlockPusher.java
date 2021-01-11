package tetris3;

import org.jspace.Space;
public class BlockPusher implements Runnable {
    private Space blockSpace;

    public BlockPusher(Space blockSpace) {
        this.blockSpace = blockSpace;
    }

    @Override
    public void run() {
        try {
            blockSpace.put(new TBlock());
            blockSpace.put(new TBlock());
            blockSpace.put(new TBlock());
            blockSpace.put(new TBlock());
            blockSpace.put(new TBlock());
            blockSpace.put(new TBlock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
