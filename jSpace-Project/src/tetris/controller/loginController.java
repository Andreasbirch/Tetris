package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

    public loginController() {}

    @FXML private TextField usernameTF;
    @FXML private PasswordField passwordPF;

    @FXML
    public void initialize() {
        usernameTF.setPromptText("Username");
        passwordPF.setPromptText("Password");
    }

    public void login(ActionEvent event) {
    }
}
