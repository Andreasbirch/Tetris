package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tetris.App;


public class mainMenuController {

    public mainMenuController() {}

    @FXML
    private Button startBtn;

    @FXML
    private Pane StartPage;

    @FXML
    private Pane HighScoresPage;

    @FXML
    private Pane ControlsPage;

    @FXML
    private void startB(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        App app = new App(stage);
    }

    @FXML
    private void highscoreB(ActionEvent event) throws Exception {
        Stage stage = (Stage) StartPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/HighScoresPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void controllerB(ActionEvent event) throws Exception {
        Stage stage = (Stage) StartPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/ControlsPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
