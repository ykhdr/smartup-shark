package ru.nsu.fit.ykhdr.smartupshark;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.fit.ykhdr.smartupshark.controller.MenuController;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage){
        MenuController menuController = new MenuController(stage);
        menuController.run();
    }
}