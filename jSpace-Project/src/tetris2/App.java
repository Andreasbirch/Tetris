package tetris2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tetris2.data.Board;
import tetris2.data.IBlock;
import tetris2.view.BoardView;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Board board;
    private BoardView view;

    @Override
    public void start(Stage primaryStage) {
        board = new Board();
        view = new BoardView(board);

        Scene scene = new Scene(view.getView(), 280, 560);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT)  {board.moveBlock("LEFT");}
                if(event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {board.moveBlock("RIGHT");}
                if(event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN)  {board.moveBlock("DOWN");}
                if(event.getCode() == KeyCode.SPACE)  {board.printBoard();}
                view.updateView(board);
                event.consume();
            }
        });

        board.addBlock();

        primaryStage.setTitle("Tetris!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
