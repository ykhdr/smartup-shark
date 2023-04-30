package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.MenuView;

public class MenuController implements Controller {

    private final @NotNull MenuView view;

    private final @NotNull SceneManager sceneManager;

    public MenuController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new MenuView();

        setupButtons();
    }

    private void setupButtons() {
        view.getNewGameBtn().setOnAction(event ->  sceneManager.setGameScene());
        view.getExitBtn().setOnAction(event -> System.exit(0));
        view.getScoreboardBtn().setOnAction(event -> sceneManager.setScoreboardScene());
        view.getAboutBtn().setOnAction(event -> sceneManager.setAboutScene());
    }

    @Override
    public @NotNull Scene getScene() {
        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
