package tetris3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {
    List<int[][]> rotations = new ArrayList<int[][]>();
    private int[][] structureElement;

    public Block() {
        Random random = new Random();
        int randomBlock = random.nextInt(2);

        switch (randomBlock){
            case 0:
                TBlock tBlock = new TBlock();
                structureElement = tBlock.getStructureElement();
                rotations = tBlock.getRotations();
                break;
            case 1:
                IBlock iBlock = new IBlock();
                structureElement = iBlock.getStructureElement();
                rotations = iBlock.getRotations();
                break;

        }
    }


    public int[][] getStructureElement() {
        return structureElement;
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
            case 360:
                structureElement = rotations.get(0);
                break;
        }
    }

}
