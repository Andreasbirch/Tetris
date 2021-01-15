package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HighscoreController {

    public HighscoreController() {}

    @FXML
    private Button backBtn;

    @FXML
    private Pane HighScoresPage;

    @FXML
    private void backB(ActionEvent event) throws Exception {
        Stage stage = (Stage) HighScoresPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
