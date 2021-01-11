package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class controlsController {


    public controlsController() {}

    @FXML
    private Button backBtn;

    @FXML
    private Label moveRight;

    @FXML
    private Label moveLeft;

    @FXML
    private Label rotateRight;

    @FXML
    private Label rotateLeft;

    @FXML
    private Label softDrop;

    @FXML
    private Label hardDrop;

    @FXML
    private Label hold;

    @FXML
    private Pane ControlsPage;

    @FXML
    private void backB(ActionEvent event) throws Exception {
        Stage stage = (Stage) ControlsPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void defaultB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Default controls");
        alert.setHeaderText("Are you sure you want to reset controls to default?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {
                moveRight.setText("Right arrow");
                moveLeft.setText("Left arrow");
                rotateRight.setText("Up arrow");
                rotateLeft.setText("Z");
                softDrop.setText("Down arrow");
                hardDrop.setText("Space");
                hold.setText("C");

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void moveRightB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Move right");
        alert.setHeaderText("Press new key for move right");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void moveLeftB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Move left");
        alert.setHeaderText("Press new key for move left");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void RotateRightB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Rotate right");
        alert.setHeaderText("Press new key for rotate right");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void RotateLeftB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Rotate left");
        alert.setHeaderText("Press new key for rotate left");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void SoftDropB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Soft drop");
        alert.setHeaderText("Press new key for soft drop");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void HardDropB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Hard drop");
        alert.setHeaderText("Press new key for hard drop");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }

    public void HoldB(ActionEvent actionEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Hold");
        alert.setHeaderText("Press new key for hold");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if(type == okButton) {

                //Her skal der ændres i controls ift. hvilke vi bruger i spillet.

            } else {}
        });
    }
}

