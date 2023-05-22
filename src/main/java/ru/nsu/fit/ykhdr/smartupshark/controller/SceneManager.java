package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SceneManager {
    private final @NotNull Stage stage;

    private @Nullable Controller menuController;
    private @Nullable Controller gameController;
    private @Nullable Controller aboutController;
    private @Nullable Controller scoreboardController;

    private static @Nullable SceneManager sceneManager;

    public static @NotNull SceneManager getInstance(@NotNull Stage stage) {
        if (sceneManager == null) {
            sceneManager = new SceneManager(stage);
        }

        return sceneManager;
    }

    private SceneManager(@NotNull Stage stage) {
        this.stage = stage;
    }

    void setGameScene() {
        if (gameController == null) {
            gameController = new GameController(this);
        }

        stage.setScene(gameController.getScene());
    }

    void setScoreboardScene() {
        if (scoreboardController == null) {
            scoreboardController = new ScoreboardController(this);
        }

        stage.setScene(scoreboardController.getScene());
    }

    void setAboutScene() {
        if (aboutController == null) {
            aboutController = new AboutController(this);
        }

        stage.setScene(aboutController.getScene());
    }

    void setMenuScene() {
        if (menuController == null) {
            menuController = new MenuController(this);
        }

        stage.setScene(menuController.getScene());
    }

    public void show() {
        setMenuScene();
        stage.setResizable(false);
        stage.show();
    }
}
