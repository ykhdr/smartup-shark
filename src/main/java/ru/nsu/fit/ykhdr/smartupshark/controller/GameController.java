package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.view.GameView;


public class GameController implements Controller {

    private final @NotNull GameView view;
    private final @NotNull Scene scene;
    private final @NotNull GameModel model;
    private final @NotNull SceneManager sceneManager;

    private final @NotNull AnimationTimer timer;

    private boolean isSetup = false;

    public GameController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new GameView();
        this.scene = new Scene(view);
        this.model = new GameModel();
        this.timer = createTimer();

    }

    private void setupViewDependencies() {
        view.setActionOnBtnStart(event -> startGame());

        view.setActionOnBtnNewGame(event -> newGame());
        view.setActionOnBtnBackToMenu(event -> sceneManager.setMenuScene());
    }

    private @NotNull AnimationTimer createTimer() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.update();

                if (model.isGameOver()) {
                    endGame();
                    return;
                }

                view.update(model.getGameObjects(), model.getScore());
                view.repaint();

                model.removeDeadObjects();
            }
        };
    }

    private void newGame() {
        view.reset();
        model.reset();
        startGame();
    }

    private void endGame() {
        timer.stop();
        view.endGame(model.getScore());
        model.writeScore();
        model.reset();
    }

    private void resetView() {
        view.reset();
    }

    @Override
    public @NotNull Scene getScene() {
        if (!isSetup) {
            view.setup(model.getSceneSize());

            setupViewDependencies();
            isSetup = true;
        } else {
            resetView();
        }
        return scene;
    }

    private void startGame() {
        scene.setOnMouseMoved((MouseEvent event) -> model.movePlayer(event.getX(), event.getY()));

        view.startGame();
        timer.start();
    }
}
