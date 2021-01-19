package tetris.Blocks;

import java.util.ArrayList;
import java.util.List;

public class SBlock {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<>();

    public SBlock() {
        rotations.add(new int[][]{
                {0, 3, 3, 0},
                {3, 3, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 3, 0, 0},
                {0, 3, 3, 0},
                {0, 0, 3, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {0, 3, 3, 0},
                {3, 3, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {3, 0, 0, 0},
                {3, 3, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 0, 0}
        });
        structureElement = rotations.get(0);
    }

    public int[][] getStructureElement() {
        return structureElement;
    }

    public List<int[][]> getRotations() {
        return rotations;
    }
}
