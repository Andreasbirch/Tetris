package tetris.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tetris.App;
import tetris.HighScore;
import tetris.HighScoreData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {
    @FXML private Pane StartPage;
    @FXML private TableView<HighScoreData> table;
    @FXML private TableColumn<HighScoreData, String> col1;
    @FXML private TableColumn<HighScoreData, String> col2;

    ObservableList<HighScoreData> data;
    HighScore highScore = new HighScore();

    public MainMenuController() {}

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        col1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        col2.setCellValueFactory(
                new PropertyValueFactory<>("score")
        );

        table.setItems(highScore.getData());
    }

    @FXML
    private void startB() throws Exception {
        Stage stage = (Stage) StartPage.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/tetris/view/GamePage.fxml"));
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

    @FXML
    private void hostB(ActionEvent event) throws Exception {
        System.out.println("Launching server");
        App.hostGame();
    }

    @FXML
    private void joinB(ActionEvent event) throws Exception {
        System.out.println("Attempting to join game");

        TextField serverIP;

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Join Game");
        dialog.setHeaderText("Pleaser enter the server IP address: ");

        ButtonType ok = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ok);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        serverIP = new TextField();
        serverIP.setPromptText("IP Address");

        grid.add(new Label("Pleaser enter the server IP address: "), 0, 0);
        grid.add(serverIP, 1, 0);

        Node okButton = dialog.getDialogPane().lookupButton(ok);
        okButton.setDisable(true);

        serverIP.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> serverIP.requestFocus());

        dialog.setResultConverter(dialogButton -> {

            if (dialogButton == ok) {
                try {
                    App.joinGame(serverIP.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.close();
            }
            return null;
        });

        dialog.show();
    }
}
