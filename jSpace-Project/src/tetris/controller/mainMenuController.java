package tetris.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jspace.SequentialSpace;
import org.jspace.Space;
import tetris.App;
import tetris.GameServer;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class mainMenuController {
    @FXML private Button startBtn;
    @FXML private Pane TetrisPage;
    @FXML private Pane StartPage;
    @FXML private Pane ControlsPage;
    @FXML private ImageView startBtnView;

    public mainMenuController() {}

    @FXML
    private void startB() throws Exception {
        Stage stage = new Stage();
        App app = new App(stage);
    }

    @FXML
    private void controllerB(ActionEvent event) throws Exception {
        Stage stage = (Stage) StartPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/ControlsPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void hostB(ActionEvent event) throws Exception {
        System.out.println("Launching server");
        App.launchHost();
    }
}
