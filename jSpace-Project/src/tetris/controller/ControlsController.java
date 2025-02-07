package tetris.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tetris.App;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController implements Initializable {

    public ControlsController() {}

    DB DB = new DB();

    @FXML private Button backBtn;
    @FXML private Button defaultBtn;
    @FXML private Button saveChangesBtn;
    @FXML private Label moveRight;
    @FXML private Label moveLeft;
    @FXML private Label rotateRight;
    @FXML private Label rotateLeft;
    @FXML private Label softDrop;
    @FXML private Label hardDrop;
    @FXML private Label hold;
    @FXML private Label saveChanges;
    @FXML private Label saveDefaultChanges;
    @FXML private CheckBox soundCB;
    @FXML private CheckBox musicCB;
    @FXML private Pane ControlsPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
        defaultBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
        saveChangesBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));

        moveRight.setText(DB.getMoveRightControl());
        moveLeft.setText(DB.getMoveLeftControl());
        rotateRight.setText(DB.getRotateRightControl());
        rotateLeft.setText(DB.getRotateLeftControl());
        softDrop.setText(DB.getSoftDropControl());
        hardDrop.setText(DB.getHardDropControl());
        hold.setText(DB.getHoldControl());

        soundCB.setSelected(DB.getDisableSound());
        musicCB.setSelected(DB.getDisableMusic());
    }

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

                //Updating labels
                moveLeft.setText("Left");
                moveRight.setText("Right");
                softDrop.setText("Down");
                rotateRight.setText("Up");
                hardDrop.setText("Space");
                rotateLeft.setText("Z");
                hold.setText("C");

                //Updating DB
                DB.setMoveRightKey("Right");
                DB.setMoveLeftKey("Left");
                DB.setRotateRightKey("Up");
                DB.setRotateLeftKey("Z");
                DB.setSoftDropKey("Down");
                DB.setHardDropKey("Space");
                DB.setHoldKey("C");

                //Updating checkboxes
                soundCB.setSelected(true);
                musicCB.setSelected(false);

                DB.setDisableSound(false);
                DB.setDisableMusic(false);

                saveDefaultChanges.setText("Controls saved as default");
                saveChanges.setText("");
            } else {}
        });
    }

    public void saveB(ActionEvent actionEvent) {
        //Sets the actual gamekeys to be that of the control panel
        App.setKeys(moveLeft.getText(), moveRight.getText(), softDrop.getText(), rotateRight.getText(), hardDrop.getText() );

        //Updating DB with saved values
        DB.setMoveRightKey(moveRight.getText());
        DB.setMoveLeftKey(moveLeft.getText());
        DB.setRotateRightKey(rotateRight.getText());
        DB.setRotateLeftKey(rotateLeft.getText());
        DB.setSoftDropKey(softDrop.getText());
        DB.setHardDropKey(hardDrop.getText());
        DB.setHoldKey(hold.getText());

        DB.setDisableSound(soundCB.isSelected());
        DB.setDisableMusic(musicCB.isSelected());

        saveChanges.setText("Controls saved!");
        saveDefaultChanges.setText("");
    }

    public void moveRightB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for move right"));
        dialogVbox.getChildren().add(new Label("Current key is: " + moveRight.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::moveRightHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(moveRight.getText().contains(moveLeft.getText()) || moveRight.getText().contains(rotateRight.getText())
                            || moveRight.getText().contains(rotateLeft.getText()) || moveRight.getText().contains(softDrop.getText())
                            || moveRight.getText().contains(hardDrop.getText()) || moveRight.getText().contains(hold.getText())) {

                        moveRight.setText("Right");
                        DB.setMoveRightKey("Right");
                        warningAlert("move right");
                    }
                    saveChanges.setText("");
                    saveDefaultChanges.setText("");
                    dialog.close();
                }
        });
    }

    public void moveLeftB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for move left"));
        dialogVbox.getChildren().add(new Label("Current key is: " + moveLeft.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::moveLeftHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(moveLeft.getText().contains(moveRight.getText()) || moveLeft.getText().contains(rotateRight.getText())
                        || moveLeft.getText().contains(rotateLeft.getText()) || moveLeft.getText().contains(softDrop.getText())
                        || moveLeft.getText().contains(hardDrop.getText()) || moveLeft.getText().contains(hold.getText())) {

                    moveLeft.setText("Left");
                    DB.setMoveLeftKey("Left");
                    warningAlert("move left");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    public void RotateRightB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for rotate right"));
        dialogVbox.getChildren().add(new Label("Current key is: " + rotateRight.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::rotateRightHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(rotateRight.getText().contains(moveRight.getText()) || rotateRight.getText().contains(moveLeft.getText())
                        || rotateRight.getText().contains(rotateLeft.getText()) || rotateRight.getText().contains(softDrop.getText())
                        || rotateRight.getText().contains(hardDrop.getText()) || rotateRight.getText().contains(hold.getText())) {

                    rotateRight.setText("Up");
                    DB.setRotateRightKey("Up");
                    warningAlert("move right");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    public void RotateLeftB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for rotate left"));
        dialogVbox.getChildren().add(new Label("Current key is: " + rotateLeft.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::rotateLeftHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(rotateLeft.getText().contains(moveLeft.getText()) || rotateLeft.getText().contains(moveRight.getText())
                        || rotateLeft.getText().contains(rotateRight.getText()) || rotateLeft.getText().contains(softDrop.getText())
                        || rotateLeft.getText().contains(hardDrop.getText()) || rotateLeft.getText().contains(hold.getText())) {

                    rotateLeft.setText("Z");
                    DB.setRotateLeftKey("Z");
                    warningAlert("rotate left");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    public void SoftDropB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for soft drop"));
        dialogVbox.getChildren().add(new Label("Current key is: " + softDrop.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::softDropHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(softDrop.getText().contains(moveLeft.getText()) || softDrop.getText().contains(rotateRight.getText())
                        || softDrop.getText().contains(rotateLeft.getText()) || softDrop.getText().contains(moveRight.getText())
                        || softDrop.getText().contains(hardDrop.getText()) || softDrop.getText().contains(hold.getText())) {

                    softDrop.setText("Down");
                    DB.setSoftDropKey("Down");
                    warningAlert("soft drop");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    public void HardDropB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for hard drop"));
        dialogVbox.getChildren().add(new Label("Current key is: " + hardDrop.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::hardDropHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(hardDrop.getText().contains(moveLeft.getText()) || hardDrop.getText().contains(rotateRight.getText())
                        || hardDrop.getText().contains(rotateLeft.getText()) || hardDrop.getText().contains(softDrop.getText())
                        || hardDrop.getText().contains(moveRight.getText()) || hardDrop.getText().contains(hold.getText())) {

                    hardDrop.setText("Space");
                    DB.setHardDropKey("Space");
                    warningAlert("hard drop");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    public void HoldB(ActionEvent actionEvent) {
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(80);
        dialogVbox.getChildren().add(new Text("Press key for hold"));
        dialogVbox.getChildren().add(new Label("Current key is: " + hold.getText()));

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        dialogScene.setOnKeyPressed(this::holdHandle);
        dialog.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(hold.getText().contains(moveLeft.getText()) || hold.getText().contains(rotateRight.getText())
                        || hold.getText().contains(rotateLeft.getText()) || hold.getText().contains(softDrop.getText())
                        || hold.getText().contains(hardDrop.getText()) || hold.getText().contains(moveRight.getText())) {

                    hold.setText("C");
                    DB.setHoldKey("C");
                    warningAlert("hold");
                }
                saveChanges.setText("");
                saveDefaultChanges.setText("");
                dialog.close();
            }
        });
    }

    //Methods to register keypresses
    private void moveRightHandle(KeyEvent keyEvent) {
        moveRight.setText(keyEvent.getCode().getName());
    }
    private void moveLeftHandle(KeyEvent keyEvent) {
        moveLeft.setText(keyEvent.getCode().getName());
    }
    private void rotateRightHandle(KeyEvent keyEvent) {
        rotateRight.setText(keyEvent.getCode().getName());
    }
    private void rotateLeftHandle(KeyEvent keyEvent) {
        rotateLeft.setText(keyEvent.getCode().getName());
    }
    private void softDropHandle(KeyEvent keyEvent) {
        softDrop.setText(keyEvent.getCode().getName());
    }
    private void hardDropHandle(KeyEvent keyEvent) {
        hardDrop.setText(keyEvent.getCode().getName());
    }
    private void holdHandle(KeyEvent keyEvent) {
        hold.setText(keyEvent.getCode().getName());
    }

    public void warningAlert(String name) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Overlapping controls for " + name + "!");
        alert.setContentText("Cannot pick same controls for different actions");
        alert.showAndWait();

        saveChanges.setText("");
        saveDefaultChanges.setText("");
    }

}

