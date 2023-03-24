package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Enemy;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.EnemyGenerator;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Player;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Sprite;


public class GameModel {

    private final Pane gameField;
    private final AnchorPane endPane;

    private final Player player = new Player(500, 500, 30, 15, Color.BLUE);

    private double spawnTime = 0;
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateGameField();
            gameField.getChildren().forEach(s -> {
                if (!(s instanceof Enemy enemy)) {
                    return;
                }
                enemy.layout();
            });
        }
    };

    private final double SPAWN_OFFSET = 100;
    private final double PLAYER_SIZE_SCALE = 0.75;
    private final double SPAWN_TIME = 0.5;


    public GameModel(@NotNull Pane gameField, @NotNull AnchorPane endPane) {
        this.gameField = gameField;
        this.endPane = endPane;
    }

    public void startGame() {
        resetGame();

        gameField.getChildren().add(player);

        Scene scene = gameField.getScene();
        scene.setOnMouseMoved((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            player.setX(x - player.getWidth() / 2);
            player.setY(y - player.getHeight() / 2);
        });

        player.setVisible(true);
        timer.start();
    }

    private void resetGame() {
        player.reset();
    }

    private void spawnEnemy() {
        Enemy enemy = EnemyGenerator.generateRandomEnemy(gameField.getWidth(), gameField.getHeight());
        gameField.getChildren().add(enemy);
    }


    private void updateGameField() {
        spawnTime += 0.016;

        gameField.getChildren().forEach(n -> {
            Sprite s = (Sprite) n;
            if (s.isDead() || player.isDead()) {
                return;
            }
            if (s != player && s.getBoundsInParent().intersects(player.getBoundsInParent())) {
                if (player.getWidth() * player.getHeight() > s.getWidth() * s.getHeight()) {
                    s.setDead(true);
                    player.setWidth(player.getWidth() + PLAYER_SIZE_SCALE);
                    player.setHeight(player.getHeight() + PLAYER_SIZE_SCALE);
                } else {
                    player.setDead(true);
                }
                System.out.println("bad");
            }
        });

        gameField.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            if (s.isDead()) {
                return true;
            }
            return s.getTranslateX() > gameField.getWidth() + SPAWN_OFFSET || -s.getTranslateX() - SPAWN_OFFSET > gameField.getWidth();
        });

        if (player.isDead()) {
            gameField.getChildren().clear();
            timer.stop();

            endPane.setVisible(true);
            endPane.setManaged(true);
        }

        if (spawnTime > SPAWN_TIME) {
            spawnEnemy();
            spawnTime = 0;
        }
    }
}
