package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.*;
import ru.nsu.fit.ykhdr.smartupshark.sprite.EnemyFactory;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GameModel {

    private final Pane gameField;
    private final VBox endBox;
    private final Label finalScoreLabel;
    private final Label curScoreLabel;


    private final Player player = new Player();

    private int score = 0;

    private double spawnTime = 0;
    private double spawnIncreaseTime = 0;
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

    private double SPAWN_DELAY = 1;

    public GameModel(@NotNull Pane gameField, @NotNull VBox endBox, @NotNull Label finalScoreLabel, @NotNull Label curScoreLabel) {
        this.gameField = gameField;
        this.endBox = endBox;
        this.finalScoreLabel = finalScoreLabel;
        this.curScoreLabel = curScoreLabel;
    }

    public void startGame() {
        resetGame();

        Scene scene = gameField.getScene();
        scene.setOnMouseMoved((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();

            player.setId(x < player.getX() + player.getWidth() / 2 ? "player-left" : "player-right");
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
        gameField.setVisible(true);
    }

    private void spawnEnemy() {
        Enemy enemy = EnemyFactory.getRandomEnemy(gameField.getWidth(), gameField.getHeight(), player.size());
        gameField.getChildren().add(enemy);
    }


    private void updateGameField() {
        spawnTime += 0.016;
        spawnIncreaseTime += 0.016;

        gameField.getChildren().forEach(this::checkPlayerCollision);
        gameField.getChildren().removeIf(this::checkFieldCollision);

        if (player.isDead()) {
            endGame();
        }

        if (spawnTime > SPAWN_DELAY) {
            spawnEnemy();
            spawnTime = 0;
        }

        if (spawnIncreaseTime > 10 && SPAWN_DELAY > 0.2) {
            SPAWN_DELAY -= 0.05;
            spawnIncreaseTime = 0;
        }
    }

    private void endGame() {
        gameField.getChildren().removeIf(s -> s instanceof Sprite);
        gameField.setVisible(false);
        timer.stop();
        endBox.setVisible(true);
        endBox.setManaged(true);
        finalScoreLabel.setText(getScore());
        writeScore();
    }

    private void checkPlayerCollision(@NotNull Node node) {
        if (!(node instanceof Enemy enemy)) {
            return;
        }

        if (enemy.isDead() || player.isDead()) {
            return;
        }

        final double PLAYER_SIZE_SCALE = 1;
        final double MAX_PLAYER_SIZE = 18050;

        if (enemy.getBoundsInParent().intersects(player.getBoundsInParent())) {
            if (enemy.isEatable()) {
                enemy.setDead(true);
                score++;
                curScoreLabel.setText(getScore());
                if (player.size() < MAX_PLAYER_SIZE) {
                    player.setWidth(player.getWidth() + PLAYER_SIZE_SCALE);
                    player.setHeight(player.getHeight() + PLAYER_SIZE_SCALE);
                }
            } else {
                player.setDead(true);
            }
        }
    }

    private boolean checkFieldCollision(@NotNull Node node) {
        if (!(node instanceof Sprite sprite)) {
            return false;
        }

        if (sprite.isDead()) {
            return true;
        }

        final double SPAWN_OFFSET = 100;

        double minX = gameField.getBoundsInParent().getMinX() - SPAWN_OFFSET;
        double minY = gameField.getBoundsInParent().getMinY() - SPAWN_OFFSET;
        double maxX = gameField.getBoundsInParent().getMaxX() + SPAWN_OFFSET;
        double maxY = gameField.getBoundsInParent().getMaxY() + SPAWN_OFFSET;

        return  sprite.getBoundsInParent().getMinX() > maxX ||
                sprite.getBoundsInParent().getMaxX() < minX ||
                sprite.getBoundsInParent().getMinY() > maxY ||
                sprite.getBoundsInParent().getMaxY() < minY;
    }

    private @NotNull String getScore() {
        return "Score : " + score;
    }

    private void writeScore() {
        Path scoreboardPath = Path.of("src/main/resources/ru/nsu/fit/ykhdr/smartupshark/data/scores.csv");
        if (!Files.exists(scoreboardPath)) {
            createFile(scoreboardPath);
        }

        try (FileWriter writer = new FileWriter(scoreboardPath.toString(), true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + "," + score + "\n");

            score = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile(@NotNull Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


