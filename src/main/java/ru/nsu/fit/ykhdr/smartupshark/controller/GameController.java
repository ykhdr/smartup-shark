package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private AnchorPane startPane;

    @FXML
    private Pane gameField;

    @FXML
    private AnchorPane endPane;
    @FXML
    private Label scoreLabel;
    @FXML
    private Player player;
    private GameModel model;


    @FXML
    public void onActionBtnStart() {
        startPane.setVisible(false);
        startPane.setManaged(false);

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
    public void onActionNewGame() {
        endPane.setVisible(false);
        endPane.setManaged(false);

        model.startGame();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new GameModel(gameField, player, endPane, scoreLabel);


        endPane.setVisible(false);
        endPane.setManaged(false);
    }
}
