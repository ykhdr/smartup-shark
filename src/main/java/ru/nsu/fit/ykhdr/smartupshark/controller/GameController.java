package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.config.ConfigParser;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.model.utils.ScoreFileHandler;
import ru.nsu.fit.ykhdr.smartupshark.view.GameView;


public class GameController implements Controller {
    private static final @NotNull String DEFAULT_CONFIG_PATH = "src/main/resources/configs/default-config.json";

    private final @NotNull GameView view;
    private final @NotNull Scene scene;
    private final @NotNull GameModel model;
    private final @NotNull ScoreFileHandler scoreFileHandler = ScoreFileHandler.getInstance();
    private final @NotNull SceneManager sceneManager;
    private final @NotNull AnimationTimer timer;
    private boolean isSetup = false;

    public GameController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new GameView();
        this.scene = new Scene(view);
        this.timer = createTimer();

        this.model = new GameModel(ConfigParser.getInstance().parse(DEFAULT_CONFIG_PATH));
    }

    private void setupViewDependencies() {
        view.setActionOnBtnStart(event -> startGame());
        view.setActionOnBtnNewGame(event -> newGame());
        view.setActionOnBtnBackToMenu(event -> sceneManager.setMenuScene());
        scene.setOnMouseMoved((MouseEvent event) -> model.movePlayer(event.getX(), event.getY()));
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

                view.repaint(model.getGameObjects());
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
        scoreFileHandler.cacheNewScore(model.getScore());
        model.reset();
    }

    private void resetView() {
        view.reset();
    }

    @Override
    public @NotNull Scene getScene() {
        if (!isSetup) {
            view.setup();

            setupViewDependencies();
            isSetup = true;
        } else {
            resetView();
        }
        return scene;
    }

    private void startGame() {
        view.startGame();
        timer.start();
    }
}
