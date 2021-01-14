package tetris.controller;

import com.sun.javafx.scene.control.TreeTableViewBackingList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jspace.SequentialSpace;
import org.jspace.Space;
import tetris.App;
import tetris.GameServer;
import tetris.HighScore;
import tetris.HighScoreData;

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
    @FXML private TableView table = new TableView<HighScoreData>();
    @FXML private TableColumn col1 = new TableColumn();
    @FXML private TableColumn col2 = new TableColumn();

    ObservableList<HighScoreData> data;
    HighScore highScore = new HighScore();

    public mainMenuController() {
        setTable();
    }

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

    private void setTable() {

        col1.setCellValueFactory(new PropertyValueFactory<HighScoreData, String>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<HighScoreData, String>("score"));

        table.setItems(highScore.getData());
        table.getColumns().addAll(col1, col2);
    }
}
