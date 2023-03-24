package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreData;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreboardModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {

    @FXML
    private TableView<ScoreData> scoreboard;

    @FXML
    private TableColumn<ScoreData, String> dateColumn;

    @FXML
    private TableColumn<ScoreData, Integer> scoreColumn;


    @FXML
    public void onActionBtnBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/fit/ykhdr/smartupshark/view/menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ScoreboardModel model = new ScoreboardModel();
        scoreboard.getItems().addAll(model.getScoreDataObservableList());
    }

}
