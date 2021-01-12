package tetris2.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetris2.data.Board;

public class BoardView {
    private Pane pane;
    private final int TILE_SIZE = 28;

    public BoardView(Board board) {
        pane = new Pane();
        for(int x = 1; x < 11; x++) {
            for(int y = 0; y < 20; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(TILE_SIZE);
                tile.setHeight(TILE_SIZE);
                tile.setLayoutX((x-1) * TILE_SIZE);
                tile.setLayoutY(y * TILE_SIZE);
                tile.setFill(Color.BLACK);
                tile.setStroke(Color.DARKGRAY);

                pane.getChildren().add(tile);
            }
        }
    }

    public void updateView(Board board) {
        for(int x = 1; x < 11; x++) {
            for(int y = 0; y < 20; y++) {
                Rectangle tile = new Rectangle();
                tile.setWidth(TILE_SIZE);
                tile.setHeight(TILE_SIZE);
                tile.setLayoutX(x * TILE_SIZE);
                tile.setLayoutY(y * TILE_SIZE);

                if(board.boardGrid[x][y] == 0) {
                    tile.setFill(Color.BLACK);
                    tile.setStroke(Color.DARKGRAY);
                } else if(board.boardGrid[x][y] == 1) {
                    tile.setFill(Color.CYAN);
                }
                pane.getChildren().add(tile);
            }
        }
    }

    public Pane getView() {
        return pane;
    }
}
