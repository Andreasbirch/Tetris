package tetris3;

public class TBlock extends Block{
    private int[][] structureElement;
    private int[] center = {1,1};

    public TBlock() {
        structureElement = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
    }

    private void rotations(int deg){
        switch (deg){
            case 0:
                structureElement = new int[][]{
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 90:
                structureElement = new int[][]{
                        {0, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 180:
                structureElement = new int[][]{
                        {0, 0, 0, 0},
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 270:
                structureElement = new int[][]{
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                };
                break;

        }
    }

    public int[] getCenter(){
        return center;
    }

    public int[][] getStructureElement() {
        return structureElement;
    }
}
