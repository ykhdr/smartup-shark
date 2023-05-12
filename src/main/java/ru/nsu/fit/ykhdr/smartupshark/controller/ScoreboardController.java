package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreFileHandler;
import ru.nsu.fit.ykhdr.smartupshark.view.ScoreboardView;

public class ScoreboardController implements Controller {

    private final @NotNull ScoreboardView view;
    private final @NotNull Scene scene;
    private final @NotNull SceneManager sceneManager;

    private boolean isSetup = false;


    public ScoreboardController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new ScoreboardView();
        this.scene = new Scene(view);
    }

    private void setScoreboardItems() {
        view.setItems(ScoreFileHandler.getScoreDataList());
    }

    private void setupViewDependencies() {
        view.setActionOnBtnBackToMenu(event -> sceneManager.setMenuScene());
    }


    @Override
    public @NotNull Scene getScene() {
        if (!isSetup) {
            view.setup();
            setupViewDependencies();
            isSetup = true;
        }

        setScoreboardItems();

        return scene;
    }
}
