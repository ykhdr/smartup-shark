package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprte.HorizontalEnemy;
import ru.nsu.fit.ykhdr.smartupshark.sprte.HorizontalEnemyFactory;
import ru.nsu.fit.ykhdr.smartupshark.sprte.Player;
import ru.nsu.fit.ykhdr.smartupshark.sprte.Sprite;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GameModel {

    private final Pane gameField;
    private final AnchorPane endPane;
    private final Label scoreLabel;

    private final Player player; // = new Player(500, 500, 30, 15, Color.BLUE);

    private int score = 0;

    private double time = 0;
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateGameField();
            gameField.getChildren().forEach(s -> {
                if (!(s instanceof HorizontalEnemy enemy)) {
                    return;
                }
                enemy.layout();
            });
        }
    };

    private final double SPAWN_OFFSET = 100;
    private final double PLAYER_SIZE_SCALE = 0.75;

    private final double MAX_HEIGHT = 95.0;
    private final double MAX_WEIGHT = 190.0;


    public GameModel(@NotNull Pane gameField, @NotNull Player player, @NotNull AnchorPane endPane, @NotNull Label scoreLabel) {
        this.gameField = gameField;
        this.endPane = endPane;
        this.scoreLabel = scoreLabel;
        this.player = player;
    }

    public void startGame() {
        resetGame();

        Scene scene = gameField.getScene();
        scene.setOnMouseMoved((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            player.setX(x - player.getWidth() / 2);
            player.setY(y - player.getHeight() / 2);
        });


        timer.start();
    }

    private void resetGame() {
        player.reset();

        if (!gameField.getChildren().contains(player)) {
            gameField.getChildren().add(player);
        }

        player.setVisible(true);
    }

    private void spawnEnemy() {
        HorizontalEnemy enemy = HorizontalEnemyFactory.getRandomEnemy(gameField.getWidth(), gameField.getHeight());
        gameField.getChildren().add(enemy);
    }


    private void updateGameField() {
        time += 0.016;

        gameField.getChildren().forEach(this::checkCollision);

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
        if (time > SPAWN_TIME) {
            spawnEnemy();
            time = 0;
        }
    }

    private void checkCollision(@NotNull Node node) {
        Sprite sprite = (Sprite) node;

        if (sprite.isDead() || player.isDead()) {
            return;
        }

        if (sprite != player && sprite.getBoundsInParent().intersects(player.getBoundsInParent())) {
            if (player.getWidth() * player.getHeight() > sprite.getWidth() * sprite.getHeight()) {
                sprite.setDead(true);
                score++;
                if (MAX_HEIGHT > player.getHeight() && MAX_WEIGHT > player.getWidth()) {
                    player.setWidth(player.getWidth() + PLAYER_SIZE_SCALE);
                    player.setHeight(player.getHeight() + PLAYER_SIZE_SCALE);
                }
            } else {
                player.setDead(true);
            }
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


