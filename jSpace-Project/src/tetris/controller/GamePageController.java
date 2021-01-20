package tetris.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;
import tetris.App;
import java.io.IOException;

public class GamePageController {
    @FXML Pane heldView;
    @FXML Pane gameView;
    @FXML Pane queueView1;
    @FXML Pane queueView2;
    @FXML VBox GamePage;
    @FXML VBox leftGameContainer;
    @FXML VBox rightGameContainer;
    @FXML HBox gameContainer;
    @FXML Button backBtn;
    @FXML Button pauseBtn;
    @FXML Button newGameBtn;
    @FXML Label scoreLabel;
    @FXML Label linesLabel;
    @FXML TextField chatTF;

    public GamePageController() throws InterruptedException, IOException {
        App app = new App();
    }

    @FXML
    public void initialize() {

        backBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));
        pauseBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));
        newGameBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));


        heldView = App.getHeldView();
        gameView = App.getGameView();

        queueView1 = App.getQueueView1();
        queueView2 = App.getQueueView2();

        scoreLabel.setText(App.getScore());
        linesLabel.setText(App.getLinesCleared());

        leftGameContainer.getChildren().remove(0);
        leftGameContainer.getChildren().add(0, heldView);

        rightGameContainer.getChildren().remove(0);
        rightGameContainer.getChildren().remove(0);
        rightGameContainer.getChildren().add(0, queueView1);
        rightGameContainer.getChildren().add(1, queueView2);

        gameContainer.getChildren().remove(0);
        gameContainer.getChildren().remove(0);
        gameContainer.getChildren().remove(0);
        if(App.getMultiplayer()){
            gameContainer.getChildren().addAll(leftGameContainer, gameView, rightGameContainer, App.getP2ViewPane());
            leftGameContainer.getChildren().remove(1);
        } else {
            gameContainer.getChildren().addAll(leftGameContainer, gameView, rightGameContainer);
        }

        Platform.runLater(() -> gameView.requestFocus());
    }

    public void backBtnClick(ActionEvent event) throws IOException {
        App.pauseGameNoAlert();
        App.setScore(0);
        App.setLinesCleared(0);
        Stage stage = (Stage) GamePage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pauseBtnClick(ActionEvent event) {
        App.pauseGame();
    }

    public void newGameBtnClick(ActionEvent event) throws IOException {
        App.pauseGameNoAlert();
        App.setScore(0);
        App.setLinesCleared(0);
        Stage stage = (Stage) GamePage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/GamePage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateLabels() {
        scoreLabel.setText(App.getScore());
        linesLabel.setText(App.getLinesCleared());
    }

    public void gameViewInputs(KeyEvent event) {
        App.parseInput(event);
        updateLabels();
    }
}
