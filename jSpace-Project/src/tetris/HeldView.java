package tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HeldView {
    private Pane pane;
    private Rectangle[][] tileArray = new Rectangle[4][4];

    public HeldView(int tile_size) {
        pane = new Pane();

        for(int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(tile_size);
                tile.setHeight(tile_size);
                tile.setLayoutX(x * tile_size);
                tile.setLayoutY(y * tile_size);

                tile.setFill(Color.BLACK);
                tile.setStroke(Color.DARKGRAY);
                tileArray[y][x] = tile;

                pane.getChildren().add(tileArray[y][x]);
            }
        }
    }

    public void updateHeldView(Board board) {
        int[][] boardArray = board.getHeld().getStructureElement();

        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                switch (boardArray[y][x]) {
                    case 0:
                        tileArray[y][x].setFill(Color.BLACK);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 1:
                        tileArray[y][x].setFill(Color.PURPLE);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 2:
                        tileArray[y][x].setFill(Color.CYAN);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 3:
                        tileArray[y][x].setFill(Color.LIME);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 4:
                        tileArray[y][x].setFill(Color.RED);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 5:
                        tileArray[y][x].setFill(Color.DARKBLUE);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 6:
                        tileArray[y][x].setFill(Color.ORANGE);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                    case 7:
                        tileArray[y][x].setFill(Color.YELLOW);
                        tileArray[y][x].setStroke(Color.DARKGRAY);
                        break;
                }
            }
        }
    }

    private void clearView() {
        for(int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                tileArray[y][x].setFill(Color.BLACK);
                tileArray[y][x].setStroke(Color.DARKGRAY);
            }
        }
    }

    public Pane getView() {
        return pane;
    }
}
