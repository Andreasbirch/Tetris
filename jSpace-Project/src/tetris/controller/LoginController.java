package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tetris.LoginServer;

import java.io.IOException;

public class LoginController {

    LoginServer server;

    public LoginController() {}

    @FXML private Pane LoginPage;
    @FXML private Pane NewUserPage;
    @FXML private TextField usernameTF;
    @FXML private PasswordField passwordPF;
    @FXML private Button makeUserBtn;
    @FXML private Button backBtn;



    @FXML
    public void initialize() {}


    public void login(ActionEvent event) throws Exception {
        server = new LoginServer(usernameTF.getText(), passwordPF.getText());
        if (server.getCanLogin()) {
            loginSuccess();
        } else {
            System.out.println("Incorrect username or password. Try again.");
        }
    }

    @FXML
    public void loginSuccess() throws Exception {
        Stage stage = (Stage) LoginPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/StartPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createUser(ActionEvent event) throws IOException {
        Stage stage = (Stage) LoginPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/NewUserPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) NewUserPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void makeUser(ActionEvent event) {
        //her
    }
}
