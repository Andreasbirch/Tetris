package tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tetris.LoginServer;
import java.io.IOException;

public class LoginController {

    public LoginController() {}

    @FXML private Pane LoginPage;
    @FXML private Pane NewUserPage;
    @FXML private TextField usernameTF;
    @FXML private PasswordField passwordPF;
    @FXML private TextField usernameNew;
    @FXML private PasswordField passwordNew;
    @FXML private Button loginBtn;
    @FXML private Button createUserBtn;
    @FXML private Button BackBtn;
    @FXML private Button SignUpBtn;
    @FXML private Label usernameL;
    @FXML private Label passwordL;

    LoginServer server;

    @FXML
    public void initialize() {
        try {
            loginBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));
            createUserBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));
            usernameL.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
            passwordL.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));


        } catch(NullPointerException e){}

        try {
            BackBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
            SignUpBtn.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 14));
            usernameL.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
            passwordL.setFont(Font.loadFont(getClass().getResourceAsStream("/tetris/res/PressStart2P-Regular.ttf"), 10));
        } catch(NullPointerException e) {}
    }

    public void login(ActionEvent event) throws Exception {
        server = new LoginServer(usernameTF.getText(), passwordPF.getText());
        if (server.getCanLogin()) {
            loginSuccess();
        } else {
            wrongLoginAlert();
            usernameTF.setText("");
            passwordPF.setText("");
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
    public void backBtn (ActionEvent event) throws IOException {
        Stage stage = (Stage) NewUserPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void makeUser(ActionEvent event) throws Exception {
        server = new LoginServer(usernameNew.getText(), passwordNew.getText());
        server.writeUser();

        Stage stage = (Stage) NewUserPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void wrongLoginAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Wrong username or password!");
        alert.setContentText("Try again or create a new user.");
        alert.show();
    }
}
