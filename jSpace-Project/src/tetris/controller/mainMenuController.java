package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private void startB(ActionEvent event) throws Exception {

        Stage stage = new Stage();
        App app = new App(stage);

    }
}
