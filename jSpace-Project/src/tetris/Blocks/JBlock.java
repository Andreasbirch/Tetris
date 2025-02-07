package tetris.Blocks;

import java.util.ArrayList;
import java.util.List;

public class JBlock {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<>();

    public JBlock() {
        rotations.add(new int[][]{
                {5, 0, 0, 0},
                {5, 5, 5, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 5, 5, 0},
                {0, 5, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {5, 5, 5, 0},
                {0, 0, 5, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 5, 0, 0},
                {0, 5, 0, 0},
                {5, 5, 0, 0},
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
