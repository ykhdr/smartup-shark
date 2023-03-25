package ru.nsu.fit.ykhdr.smartupshark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/nsu/fit/ykhdr/smartupshark/view/menu.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}