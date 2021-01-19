package tetris.Blocks;

import java.util.ArrayList;
import java.util.List;

public class IBlock {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<>();

    public IBlock() {
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0}
        });
        rotations.add(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 2, 2, 2},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0}
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
