package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SceneManager {
    private final @NotNull Stage stage;

    private final @NotNull Controller menuController;
    private final @NotNull Controller gameController;
    private final @NotNull Controller aboutController;
    private final @NotNull Controller scoreboardController;

    private static @Nullable SceneManager sceneManager;
    public static @NotNull SceneManager getInstance(@NotNull Stage stage) {
        if(sceneManager == null){
            sceneManager = new SceneManager(stage);
        }

        return sceneManager;
    }

    private SceneManager(@NotNull Stage stage) {
        this.stage = stage;
        
        menuController = new MenuController(this);
        gameController = new GameController(this);
        aboutController = new AboutController(this);
        scoreboardController = new ScoreboardController(this);
    }

    void setGameScene() {
        stage.setScene(gameController.getScene());
    }
    
    void setScoreboardScene() {
        stage.setScene(scoreboardController.getScene());
    }
    
    void setAboutScene(){
        stage.setScene(aboutController.getScene());
    }
    
    void setMenuScene() {
        stage.setScene(menuController.getScene());
    }

    public void show(){
        setMenuScene();
        stage.setResizable(false);
        stage.show();
    }
}
