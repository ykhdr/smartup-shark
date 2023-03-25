package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Enemy;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.EnemyGenerator;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Player;
import ru.nsu.fit.ykhdr.smartupshark.sprtemodels.Sprite;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GameModel {

    private final Pane gameField;
    private final AnchorPane endPane;
    private final Label scoreLabel;

    private final Player player; // = new Player(500, 500, 30, 15, Color.BLUE);

    private int score = 0;

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

    private final double MAX_HEIGHT = 75.0;
    private final double MAX_WEIGHT = 150.0;


    public GameModel(@NotNull Pane gameField, @NotNull Player player, @NotNull AnchorPane endPane, @NotNull Label scoreLabel) {
        this.gameField = gameField;
        this.endPane = endPane;
        this.scoreLabel = scoreLabel;
        this.player = player;
    }

    public void startGame() {
        resetGame();

        if (!gameField.getChildren().contains(player)) {
            gameField.getChildren().add(player);
        }

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
                    score++;
                    if (MAX_HEIGHT > player.getHeight() && MAX_WEIGHT > player.getWidth()) {
                        player.setWidth(player.getWidth() + PLAYER_SIZE_SCALE);
                        player.setHeight(player.getHeight() + PLAYER_SIZE_SCALE);
                    }
                } else {
                    player.setDead(true);
                }
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
            scoreLabel.setText("Score: " + score);
            writeScore();
        }

        double SPAWN_TIME = 0.5;
        if (spawnTime > SPAWN_TIME) {
            spawnEnemy();
            spawnTime = 0;
        }
    }

    private void writeScore() {
        try (FileWriter writer = new FileWriter("src/main/resources/ru/nsu/fit/ykhdr/smartupshark/data/scores.csv", true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + "," + score + "\n");

            score = 0;
        } catch (FileNotFoundException e) {
            // TODO: 25.03.2023 create file
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


