package tetris3;

import java.util.ArrayList;
import java.util.List;

public class ZBlock {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<int[][]>();
    private int[] center = {1,1};

    public ZBlock() {
        rotations.add(new int[][]{
                {4, 4, 0, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 4, 0},
                {0, 4, 4, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {4, 4, 0, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 4, 0, 0},
                {4, 4, 0, 0},
                {4, 0, 0, 0},
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
