package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.MenuView;

public class MenuController implements Runnable{
    private final @NotNull MenuView view;
    private final @NotNull  Stage stage;

    private final @NotNull ScoreboardController scoreboardController;
    private final @NotNull GameController gameController;

    private final @NotNull AboutController aboutController;

    public MenuController(@NotNull Stage stage)  {
        this.stage = stage;
        this.scoreboardController = new ScoreboardController(this::setMenuScene);
        this.gameController = new GameController(this::setMenuScene);
        this.aboutController = new AboutController(this::setMenuScene);

        this.view = new MenuView();

        setupButtons();
    }

    private void setupButtons(){
        view.getNewGameBtn().setOnAction(event -> setGameScene());
        view.getExitBtn().setOnAction(event -> System.exit(0));
        view.getScoreboardBtn().setOnAction(event -> setScoreboardScene());
        view.getAboutBtn().setOnAction(event -> setAboutScene());
    }

    private void setGameScene() {
        stage.setScene(gameController.getScene());
    }

    private void setScoreboardScene() {
        stage.setScene(scoreboardController.getScene());
    }

    private void setAboutScene(){
        stage.setScene(aboutController.getScene());
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
