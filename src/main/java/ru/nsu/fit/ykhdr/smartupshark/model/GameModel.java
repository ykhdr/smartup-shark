package ru.nsu.fit.ykhdr.smartupshark.model;


import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.*;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish.RandomFishObjectFactory;

import java.util.ArrayList;
import java.util.List;

/*

CR: tests

- player eats fish
- player eats non-eatable fish
- player eats too big fish
- player moves
 */
public class GameModel {

    private static final int SPAWN_OFFSET = 100;

    private final @NotNull List<FishObject> enemies = new ArrayList<>();
    private final @NotNull PlayerObject player = new PlayerObject(GameObjectIdManager.getPlayerId());

    private final @NotNull SceneSize fieldSize = new SceneSize(1024, 720);

    private final @NotNull SpawnTimeManager timeManager = new SpawnTimeManager();
    private int score = 0;

    {
        player.getCoordinates().setX(fieldSize.width() / 2);
        player.getCoordinates().setY(fieldSize.height() / 2);
    }

    public void movePlayer(double mouseX, double mouseY) {
        double curX = player.getCoordinates().getX();

        player.getCoordinates().setX(mouseX - player.getSize().getWidth() / 2);
        player.getCoordinates().setY(mouseY - player.getSize().getHeight() / 2);

        player.setLeftDirection(player.getCoordinates().getX() < curX);
    }

    public void update() {
        enemies.forEach(FishObject::move);

        timeManager.increaseTime();

        enemies.forEach(this::checkEnemyCollisionPlayer);
        enemies.forEach(this::checkEnemyOutOfBounds);
        enemies.forEach(this::setEnemyEatable);

        if (timeManager.isSpawnEnemyNecessary()) {
            FishObject newEnemy = RandomFishObjectFactory.getFish(fieldSize);
            enemies.add(newEnemy);
            timeManager.resetSpawnTime();
        }

        if (timeManager.needToDecreaseSpawnTime()) {
            timeManager.reduceDelay();
        }
    }

    private void checkEnemyCollisionPlayer(@NotNull FishObject enemy) {
        // CR: move to player
        final double PLAYER_SIZE_SCALE = 1;
        final double MAX_PLAYER_SIZE = 18050;

        // CR: move to base class for enemy and player
        if (checkEnemyInBoundsOfPlayer(enemy)) {
            if (enemy.isEatable()) {
                enemy.setDead(true);
                score += 1;
                if (player.getSize().getArea() < MAX_PLAYER_SIZE) {
                    // CR: move to player
                    double weight = player.getSize().getWidth();
                    double height = player.getSize().getHeight();

                    player.getSize().setWidth(weight + PLAYER_SIZE_SCALE);
                    player.getSize().setHeight(height + PLAYER_SIZE_SCALE);
                }
            } else {
                // CR: not needed
                player.setDead(true);
            }
        }
    }

    private boolean checkEnemyInBoundsOfPlayer(@NotNull FishObject enemy) {
        Bounds playerBounds = player.getBounds();
        Bounds enemyBounds = enemy.getBounds();

        return playerBounds.maxX() > enemyBounds.minX() && playerBounds.minX() < enemyBounds.maxX() &&
                playerBounds.maxY() > enemyBounds.minY() && playerBounds.minY() < enemyBounds.maxY();
    }

    private void checkEnemyOutOfBounds(@NotNull FishObject enemy) {
        Coordinates enemyCoordinates = enemy.getCoordinates();
        if (enemyCoordinates.getX() < -SPAWN_OFFSET ||
                enemyCoordinates.getX() > fieldSize.width() + SPAWN_OFFSET ||
                enemyCoordinates.getY() < -SPAWN_OFFSET ||
                enemyCoordinates.getY() > fieldSize.height() + SPAWN_OFFSET) {

            enemy.setDead(true);
        }
    }

    private void setEnemyEatable(@NotNull FishObject enemy) {
        enemy.setEatable(enemy.getSize().getArea() < player.getSize().getArea());
    }

    public void removeDeadObjects() {
        enemies.removeIf(FishObject::isDead);
    }

    public void reset() {
        enemies.clear();
        player.setDead(false);

        timeManager.resetAll();
        score = 0;
    }

    public @NotNull GameObjects getGameObjects() {
        GameObjects gameObjects = new GameObjects();

        gameObjects.add(player);
        gameObjects.addAll(enemies);

        return gameObjects;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return player.isDead();
    }

    public @NotNull SceneSize getSceneSize() {
        return fieldSize;
    }
}