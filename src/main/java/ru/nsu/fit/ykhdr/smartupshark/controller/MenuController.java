package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label label;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void onActionBtnGo(ActionEvent event) {
        System.out.println("ha");
    }

    @FXML
    public void onActionBtnScoreboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/fit/ykhdr/smartupshark/view/scoreboard.fxml"));
        root = loader.load();

        //ScoreboardController scoreboardController = loader.getController();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void onActionBtnNewGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/fit/ykhdr/smartupshark/view/game.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
