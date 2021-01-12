package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View {
    private int width, height, tile_size;
    private Rectangle[][] tileArray;
    private Pane pane;

    public View(int tile_size, int width, int height){
        pane = new Pane();
        this.tile_size = tile_size;
        this.width = width;
        this.height = height;
        tileArray = new Rectangle[height][width];

        System.out.println("Width: " + width);
        System.out.println("Height: " + height);

        for(int x = 1; x < width+1; x++) {
            for(int y = 0; y < height; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(tile_size);
                tile.setHeight(tile_size);
                tile.setLayoutX((x-1) * tile_size);
                tile.setLayoutY(y * tile_size);
                tile.setFill(Color.BLACK);
                tile.setStroke(Color.DARKGRAY);

                tileArray[y][x-1] = tile;
                pane.getChildren().add(tileArray[y][x-1]);
            }
        }
    }

    public void updateView(Board board) {
        int[][] boardArray = board.getBoardArray();

        for(int x = 1; x < width+1; x++) {
            for(int y = 0; y < height; y++) {
                switch (boardArray[y][x]) {
                    case 0:
                        tileArray[y][x-1].setFill(Color.BLACK);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 1:
                        tileArray[y][x-1].setFill(Color.PURPLE);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 2:
                        tileArray[y][x-1].setFill(Color.CYAN);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 3:
                        tileArray[y][x-1].setFill(Color.LIME);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 4:
                        tileArray[y][x-1].setFill(Color.RED);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 5:
                        tileArray[y][x-1].setFill(Color.DARKBLUE);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 6:
                        tileArray[y][x-1].setFill(Color.ORANGE);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;
                    case 7:
                        tileArray[y][x-1].setFill(Color.YELLOW);
                        tileArray[y][x-1].setStroke(Color.DARKGRAY);
                        break;

                }
//                pane.getChildren().add(tileArray[y][x]);
            }
        }
    }

    public Pane getView() {
        return pane;
    }
}
