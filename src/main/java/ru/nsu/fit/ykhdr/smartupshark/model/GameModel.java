package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Enemy;
import ru.nsu.fit.ykhdr.smartupshark.sprite.EnemyFactory;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameModel {
    private final @NotNull IntegerProperty score = new SimpleIntegerProperty(0);
    private final @NotNull ObservableList<Enemy> enemies = FXCollections.observableArrayList();
    private final @NotNull BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final @NotNull Player player;

    // TODO: 07.04.2023 create or use some structure for keep game field size
    private final double gameFieldMaxX;
    private final double gameFieldMaxY;

    private double spawnTime = 0;
    private double spawnIncreaseTime = 0;

    private double SPAWN_DELAY = 1;

    public GameModel(double gameFieldMaxX, double gameFieldMaxY) {
        this.gameFieldMaxX = gameFieldMaxX;
        this.gameFieldMaxY = gameFieldMaxY;

        this.player = new Player();
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    public void resetGame() {
        player.reset();
        gameOver.set(false);
        score.set(0);

        SPAWN_DELAY = 1;
        spawnTime = 0;
        spawnIncreaseTime = 0;
    }

    public void updateGame() {
        enemies.forEach(Enemy::layout);

        spawnTime += 0.016;
        spawnIncreaseTime += 0.016;

        enemies.removeIf(this::checkEnemyCollisionPlayer);
        enemies.removeIf(this::checkEnemyOutOfBounds);

        if (player.isDead()) {
            gameOver.set(true);
        }

        if (spawnTime > SPAWN_DELAY) {
            enemies.add(spawnEnemy());
            spawnTime = 0;
        }

        if (spawnIncreaseTime > 10 && SPAWN_DELAY > 0.2) {
            SPAWN_DELAY -= 0.05;
            spawnIncreaseTime = 0;
        }
    }

    public void endGame() {
        enemies.clear();
        writeScore();
    }

    private void writeScore(){
        Path scoreboardPath = Path.of("src/main/resources/data/scores.csv");
        if (!Files.exists(scoreboardPath)) {
            createFile(scoreboardPath);
        }

        try (FileWriter writer = new FileWriter(scoreboardPath.toString(), true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + "," + score.get() + "\n");
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


    private boolean checkEnemyCollisionPlayer(@NotNull Enemy enemy) {
        final double PLAYER_SIZE_SCALE = 1;
        final double MAX_PLAYER_SIZE = 18050;

        if (enemy.getBoundsInParent().intersects(player.getBoundsInParent())) {
            if (enemy.isEatable()) {
                enemy.setDead(true);
                score.set(score.get() + 1);
                if (player.size() < MAX_PLAYER_SIZE) {
                    player.setWidth(player.getWidth() + PLAYER_SIZE_SCALE);
                    player.setHeight(player.getHeight() + PLAYER_SIZE_SCALE);
                }
            } else {
                player.setDead(true);
            }
        }
        return enemy.isDead();
    }

    private boolean checkEnemyOutOfBounds(@NotNull Enemy enemy) {
        final double SPAWN_OFFSET = 100;

        double minX = 0 - SPAWN_OFFSET;
        double minY = 0 - SPAWN_OFFSET;
        double maxX = gameFieldMaxX + SPAWN_OFFSET;
        double maxY = gameFieldMaxY + SPAWN_OFFSET;

        return enemy.getBoundsInParent().getMinX() > maxX ||
                enemy.getBoundsInParent().getMaxX() < minX ||
                enemy.getBoundsInParent().getMinY() > maxY ||
                enemy.getBoundsInParent().getMaxY() < minY;
    }

    public @NotNull Enemy spawnEnemy() {
        return EnemyFactory.getRandomEnemy(1024, 720, player.size());
    }

    public void changePlayerCoordinates(double mouseX, double mouseY) {
        player.setX(mouseX - player.getWidth() / 2);
        player.setY(mouseY - player.getHeight() / 2);
    }

    public boolean isPlayerDirectionLeft(double mouseX){
        return  mouseX < player.getX() + player.getWidth() / 2;
    }

    public @NotNull ObservableIntegerValue getScoreProperty() {
        return score;
    }

    public @NotNull ObservableList<Enemy> getEnemies() {
        return enemies;
    }

    public @NotNull ObservableBooleanValue getGameOverProperty() {
        return gameOver;
    }
}