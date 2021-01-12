package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View {
    private int width, height, tile_size;
    private Pane pane;

    public View(int tile_size, int width, int height){
        pane = new Pane();
        this.tile_size = tile_size;
        this.width = width;
        this.height = height;


        for(int x = 1; x < width+1; x++) {
            for(int y = 0; y < height; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(tile_size);
                tile.setHeight(tile_size);
                tile.setLayoutX((x-1) * tile_size);
                tile.setLayoutY(y * tile_size);
                tile.setFill(Color.BLACK);
                tile.setStroke(Color.DARKGRAY);

                pane.getChildren().add(tile);
            }
        }
    }

    public void updateView(Board board) {
        int[][] boardArray = board.getBoardArray();

        for(int x = 1; x < width+1; x++) {
            for(int y = 0; y < height; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(tile_size);
                tile.setHeight(tile_size);
                tile.setLayoutX((x-1) * tile_size);
                tile.setLayoutY(y * tile_size);

                switch (boardArray[y][x]) {
                    case 0:
                        tile.setFill(Color.BLACK);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 1:
                        tile.setFill(Color.PURPLE);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 2:
                        tile.setFill(Color.CYAN);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 3:
                        tile.setFill(Color.LIME);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 4:
                        tile.setFill(Color.RED);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 5:
                        tile.setFill(Color.DARKBLUE);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 6:
                        tile.setFill(Color.ORANGE);
                        tile.setStroke(Color.DARKGRAY);
                        break;
                    case 7:
                        tile.setFill(Color.YELLOW);
                        tile.setStroke(Color.DARKGRAY);
                        break;

                }


                pane.getChildren().add(tile);
            }
        }
    }

    public Pane getView() {
        return pane;
    }
}
