package ru.nsu.fit.ykhdr.smartupshark;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.fit.ykhdr.smartupshark.controller.SceneManager;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage){
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.show();
    }
}