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
import tetris.App;

import javax.sound.sampled.*;
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

    //Sound
    Boolean musicOff;
    Clip clip;

    public GamePageController() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        App app = new App();
        musicOff = DB.getDisableMusic();

    }

    @FXML
    public void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

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

        if(!musicOff) {
            AudioInputStream music = AudioSystem.getAudioInputStream(getClass().getResource("/tetris/res/Tetris_Battle_Music.wav"));
            clip = AudioSystem.getClip();
            clip.open(music);
            clip.start();
        }

        Platform.runLater(() -> gameView.requestFocus());
    }

    public void backBtnClick(ActionEvent event) throws IOException {
        App.pauseGameNoAlert();
        App.setScore(0);
        App.setLinesCleared(0);

        if(!musicOff) {
            clip.stop();
        }

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
        App.generateNewSeed();
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

    public void gameViewInputs(KeyEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        App.parseInput(event);
        updateLabels();
    }

    public void musicPlayer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

    }
}
