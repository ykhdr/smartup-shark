package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.MenuView;

public class MenuController implements Runnable{
    private final MenuView view;
    private final Stage stage;

    private final ScoreboardController scoreboardController;
    private final GameController gameController;

    public MenuController(@NotNull Stage stage)  {
        this.stage = stage;
        this.scoreboardController = new ScoreboardController(this::setMenuScene);
        this.gameController = new GameController(this::setMenuScene);
        this.view = new MenuView();

        setupButtons();
    }

    private void setupButtons(){
        view.getNewGameBtn().setOnAction(event -> setGameScene());
        view.getExitBtn().setOnAction(event -> System.exit(0));
        view.getScoreboardBtn().setOnAction(event -> setScoreboardScene());
    }

    private void setGameScene() {
        stage.setScene(gameController.getScene());
    }

    private void setScoreboardScene() {
        stage.setScene(scoreboardController.getScene());
    }

    private void setMenuScene() {
        stage.setScene(view.getScene());
    }

    @Override
    public void run() {
        stage.setScene(new Scene(view));
        stage.setResizable(false);

        stage.show();
    }
}
