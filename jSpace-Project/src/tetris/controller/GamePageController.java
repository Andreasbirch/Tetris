package tetris.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tetris.App;
import tetris.View;

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

    public GamePageController() throws InterruptedException {
        App app = new App();
    }

    @FXML
    public void initialize() {
        heldView = App.getHeldView();
        gameView = App.getGameView();

        queueView1 = App.getQueueView1();
        queueView1 = App.getQueueView2();

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
            gameContainer.getChildren().addAll(leftGameContainer, gameView, rightGameContainer, new View(28, 10, 20).getView());
        } else {
            gameContainer.getChildren().addAll(leftGameContainer, gameView, rightGameContainer);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                gameView.requestFocus();
            }
        });
    }

    public void backBtnClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) GamePage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void pauseBtnClick(ActionEvent event) throws IOException {
        App.pauseGame();
    }

    public void newGameBtnClick(ActionEvent event) {

    }

    public void updateLabels() {
        scoreLabel.setText(String.valueOf(App.getScore()));
        linesLabel.setText(String.valueOf(App.getLinesCleared()));
    }

    public void gameViewInputs(KeyEvent event) {
        App.parseInput(event);
        updateLabels();
    }
}
