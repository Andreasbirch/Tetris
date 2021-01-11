package tetris3;

import tetris3.Blocks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
    List<int[][]> rotations = new ArrayList<int[][]>();
    private int[][] structureElement;

    public Block() {
        Random random = new Random();
        int randomBlock = random.nextInt(7)+1;

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



    public void rotate(int deg) {
        switch (deg) {
            case 0:
                structureElement = rotations.get(0);
                break;
            case 90:
                structureElement = rotations.get(1);
                break;
            case 180:
                structureElement = rotations.get(2);
                break;
            case 270:
                structureElement = rotations.get(3);
                break;
        }
    }

}
