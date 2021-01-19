package tetris.Blocks;

import java.util.ArrayList;
import java.util.List;

public class Square {
    private int[][] structureElement;
    List<int[][]> rotations = new ArrayList<>();

    public Square() {
        rotations.add(new int[][]{
                {0, 7, 7, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 7, 7, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 7, 7, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        rotations.add(new int[][]{
                {0, 7, 7, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0},
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
