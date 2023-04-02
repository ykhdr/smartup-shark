package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private Button startBtn;

    @FXML
    private VBox endBox;
    @FXML
    private Label finalScoreLabel;

    @FXML
    private Label curScoreLabel;

    @FXML
    private Pane gameField;

    private GameModel model;


    @FXML
    public void onActionBtnStart() {
        startBtn.setVisible(false);
        startBtn.setManaged(false);

        model.startGame();
    }

    @FXML
    public void onActionBtnMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/fit/ykhdr/smartupshark/view/menu.fxml"));
        root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void onActionBtnNewGame() {
        endBox.setVisible(false);
        endBox.setManaged(false);

        model.startGame();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new GameModel(gameField, endBox, finalScoreLabel, curScoreLabel);

        endBox.setVisible(false);
        endBox.setManaged(false);
    }
}
