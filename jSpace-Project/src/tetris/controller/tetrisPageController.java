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

public class tetrisPageController {
    public tetrisPageController() {}

    @FXML
    private Button backBtn;

    @FXML
    private Pane TetrisPage;

    @FXML
    private void backB(ActionEvent event) throws Exception {
        Stage stage = (Stage) TetrisPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void startB(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        App app = new App(stage);
    }

    @FXML
    private void restartB(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        App app = new App(stage);
    }
}
