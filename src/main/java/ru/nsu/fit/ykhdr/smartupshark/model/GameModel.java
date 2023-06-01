package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.config.GameConfig;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.Player;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes.*;
import ru.nsu.fit.ykhdr.smartupshark.model.gameutils.SpawnTimeManager;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GameModel {

    private final @NotNull GameField gameField;
    private final @NotNull FishFactory fishFactory;
    private final @NotNull Player player;
    private final @NotNull List<Fish> enemies;
    private final @NotNull Size defaultPlayerSize;

    private final @NotNull SpawnTimeManager timeManager;
    private int score;
    private boolean gameOver = false;

    public GameModel(@NotNull GameConfig config) {
        PlayerObject playerObject = config.gameObjects().player();

        this.gameField = new GameField(config.fieldSize().width(), config.fieldSize().height(), config.spawnOffset());
        this.fishFactory = new FishFactory(config.factory());
        this.player = new Player(playerObject.size(), playerObject.position(), playerObject.direction());
        this.defaultPlayerSize = playerObject.size();
        this.enemies = config.gameObjects().enemies().stream()
                .map(fishFactory::createFishFromObject)
                .collect(Collectors.toList());
        this.score = config.gameObjects().score();
        this.timeManager = new SpawnTimeManager(config.spawnTime());
    }

    public void movePlayer(double mouseX, double mouseY) {
        if (mouseX < 0 || mouseX > gameField.width() || mouseY < 0 || mouseY > gameField.height()) {
            return;
        }

        player.move(mouseX, mouseY);
    }

    public void update() {
        enemies.forEach(Fish::move);
        timeManager.increaseTime();

        checkEnemiesCollisionPlayer();

        if (gameOver) {
            return;
        }

        enemies.removeIf(this::checkEnemyOutOfBounds);
        enemies.forEach(this::setEnemiesEatable);

        if (timeManager.isTimeToSpawn()) {
            Fish newEnemy = fishFactory.generate(gameField);
            enemies.add(newEnemy);
            timeManager.resetSpawnTime();
        }

        if (timeManager.needToDecreaseSpawnTime()) {
            timeManager.reduceDelay();
        }
    }

    private void checkEnemiesCollisionPlayer() {
        Iterator<Fish> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Fish enemy = iterator.next();
            if (player.checkInBoundsOf(enemy)) {
                if (enemy.isEatable()) {
                    score += 1;
                    player.increaseSize();
                    iterator.remove();
                } else {
                    gameOver = true;
                    break;
                }
            }
        }
    }

    private boolean checkEnemyOutOfBounds(@NotNull Fish enemy) {
        return gameField.checkOutOfBounds(enemy);
    }

    private void setEnemiesEatable(@NotNull Fish enemy) {
        double enemyArea = enemy.getSize().width() * enemy.getSize().height();
        double playerArea = player.getSize().width() * player.getSize().height();

        enemy.setEatable(enemyArea < playerArea);
    }

    public void reset() {
        enemies.clear();
        gameOver = false;
        player.setSize(defaultPlayerSize);

        timeManager.resetAll();
        score = 0;
    }

    public @NotNull GameObjects getGameObjects() {
        List<FishObject> fishObjectList = enemies.stream()
                .map((model) -> new FishObject(matchFishType(model),
                        model.getSize(),
                        model.isEatable(),
                        model.getPosition(),
                        model.getDirection()))
                .toList();

        PlayerObject playerObject = new PlayerObject(player.getSize(), player.getPosition(), player.getDirection());

        return new GameObjects(fishObjectList, playerObject, score);
    }

    private @NotNull FishType matchFishType(@NotNull Fish fish) {
        return switch (fish) {
            case FatFish ignored -> FishType.FAT;
            case Jellyfish ignored -> FishType.JELLY;
            case LongFish ignored -> FishType.LONG;
            case MidFish ignored -> FishType.MID;
            case SmallFish ignored -> FishType.SMALL;
        };
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}