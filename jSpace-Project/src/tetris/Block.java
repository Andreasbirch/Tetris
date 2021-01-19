package tetris;

import tetris.Blocks.*;
import java.util.ArrayList;
import java.util.List;

public class Block {
    List<int[][]> rotations = new ArrayList<>();
    private int[][] structureElement;

    public Block(int randomBlock) {
        switch (randomBlock){
            case 1:
                TBlock tBlock = new TBlock();
                structureElement = tBlock.getStructureElement();
                rotations = tBlock.getRotations();
                break;
            case 2:
                IBlock iBlock = new IBlock();
                structureElement = iBlock.getStructureElement();
                rotations = iBlock.getRotations();
                break;
            case 3:
                SBlock sBlock = new SBlock();
                structureElement = sBlock.getStructureElement();
                rotations = sBlock.getRotations();
                break;
            case 4:
                ZBlock zBlock = new ZBlock();
                structureElement = zBlock.getStructureElement();
                rotations = zBlock.getRotations();
                break;
            case 5:
                JBlock jBlock = new JBlock();
                structureElement = jBlock.getStructureElement();
                rotations = jBlock.getRotations();
                break;
            case 6:
                LBlock lBlock = new LBlock();
                structureElement = lBlock.getStructureElement();
                rotations = lBlock.getRotations();
                break;
            case 7:
                Square square = new Square();
                structureElement = square.getStructureElement();
                rotations = square.getRotations();
                break;

        }
    }

    public int[][] getStructureElement() {
        return structureElement;
    }

    public int[][] getRotations(int i) {
        return rotations.get(i);
    }
}
