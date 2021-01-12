package tetris.Blocks;

import java.util.ArrayList;
import java.util.List;

public class LBlock {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<int[][]>();
    private int[] center = {1,1};

    public LBlock() {
        rotations.add(new int[][]{
                {0, 0, 6, 0},
                {6, 6, 6, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 6, 0, 0},
                {0, 6, 0, 0},
                {0, 6, 6, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {6, 6, 6, 0},
                {6, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {6, 6, 0, 0},
                {0, 6, 0, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
        structureElement = rotations.get(0);
    }

    public int[] getCenter(){
        return center;
    }

    public int[][] getStructureElement() {
        return structureElement;
    }

    public List<int[][]> getRotations() {
        return rotations;
    }
}
