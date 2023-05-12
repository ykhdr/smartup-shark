package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.MenuView;

public class MenuController implements Controller {

    private final @NotNull MenuView view;
    private final @NotNull Scene scene;
    private final @NotNull SceneManager sceneManager;

    private boolean isSetup = false;

    public MenuController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new MenuView();
        this.scene = new Scene(view);

        setupViewDependencies();
    }

    private void setupViewDependencies() {
        view.setActionOnBtnNewGame(event -> sceneManager.setGameScene());
        view.setActionOnBtnScoreboard(event -> sceneManager.setScoreboardScene());
        view.setActionOnBtnAbout(event -> sceneManager.setAboutScene());
        view.setActionOnBtnExit(event -> System.exit(0));
    }

    @Override
    public @NotNull Scene getScene() {
        if(!isSetup){
            view.setup();
            setupViewDependencies();

            isSetup = true;
        }

        return scene;
    }
}
