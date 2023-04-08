package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Enemy;
import ru.nsu.fit.ykhdr.smartupshark.view.GameView;


public class GameController {

    private final @NotNull GameView view;
    private final @NotNull GameModel model;

    private final AnimationTimer timer;

    public GameController(@NotNull Runnable backToMenuScene) {
        this.view = new GameView();
        this.model = new GameModel(view.getGameField().getPrefWidth(), view.getGameField().getPrefHeight());
        this.timer = createTimer();

        setupStartBox();
        setupGameField();
        setupEndBox(backToMenuScene);
        setupGameLogic();
    }

    private void setupGameLogic() {
        model.getGameOverProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == newValue) {
                return;
            }
            if (newValue) {
                timer.stop();
                view.getGameField().removeSprite(model.getPlayer());
                view.endGame();
                model.endGame();
            } else {
                view.startGame();
                model.resetGame();
                timer.start();
            }
        });
    }

    private void setupStartBox() {
        view.getStartBox().getStartBtn().setOnAction(event -> startGame());
    }

    private void setupEndBox(@NotNull Runnable backToMenuScene) {
        view.getEndBox().getBtnBackToMenu().setOnAction(event -> backToMenuScene.run());
        view.getEndBox().getBtnNewGame().setOnAction(event -> startGame());
        view.getEndBox().getScoreLabel().textProperty().bind(
                Bindings.createStringBinding(() -> "Score: " + model.getScoreProperty().get(), model.getScoreProperty()));
    }

    private void setupGameField() {
        view.getGameField().getCurScoreLabel().textProperty().bind(
                Bindings.createStringBinding(() -> "Score: " + model.getScoreProperty().get(), model.getScoreProperty()));

        model.getEnemies().addListener((ListChangeListener<Enemy>) change -> {
            while (change.next()) {
                for (Enemy enemy : change.getAddedSubList()) {
                    view.getGameField().addSprite(enemy);
                    boolean leftDirection = enemy.getDirection() == Direction.LEFT;
                    if (enemy.isEatable()) {
                        view.getGameField().updateEatableEnemyView(enemy, leftDirection);
                    } else {
                        view.getGameField().updateNonEatableEnemyView(enemy, leftDirection);
                    }
                }
                for (Enemy enemy : change.getRemoved()) {
                    view.getGameField().removeSprite(enemy);
                }
            }
        });
    }

    private @NotNull AnimationTimer createTimer() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.updateGame();
            }
        };
    }

    private void resetView() {
        view.reset();
    }

    public @NotNull Scene getScene() {
        resetView();
        return view.getScene() == null ? new Scene(view) : view.getScene();
    }

    private void startGame() {
        view.getScene().setOnMouseMoved((MouseEvent event) -> {
            view.getGameField().updatePlayerStyle(model.getPlayer(), model.isPlayerDirectionLeft(event.getX()));
            model.changePlayerCoordinates(event.getX(), event.getY());
        });

        view.getGameField().addSprite(model.getPlayer());

        view.startGame();
        model.resetGame();
        timer.start();
    }
}
