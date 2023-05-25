package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.config.GameConfig;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.PlayerModel;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes.*;
import ru.nsu.fit.ykhdr.smartupshark.model.gameutils.FishPositionGenerator;
import ru.nsu.fit.ykhdr.smartupshark.model.gameutils.SpawnTimeManager;

import java.util.List;
import java.util.stream.Collectors;

public class GameModel {
    private final @NotNull List<FishModel> enemies;
    private final @NotNull PlayerModel player;
    private final @NotNull GameField gameField;

    private final @NotNull SpawnTimeManager timeManager;
    private int score;
    private boolean gameOver = false;

    public GameModel(@NotNull GameConfig config) {
        this.gameField = new GameField(config.fieldSize().width(), config.fieldSize().height());

        PlayerObject playerObject = config.gameObjects().player();

        this.player = new PlayerModel(playerObject.size(), playerObject.direction());
        player.setPosition(playerObject.position());

        this.enemies = config.gameObjects().enemies().stream()
                .map(fishObject -> {
                    FishModel fishModel = FishFactory.FISH_FACTORIES.get(fishObject.type()).getFish(fishObject.size(), fishObject.direction());
                    fishModel.setEatable(fishObject.isEatable());
                    fishModel.setPosition(fishObject.position());
                    return fishModel;
                })
                .collect(Collectors.toList());

        this.score = config.gameObjects().score();
        this.timeManager = new SpawnTimeManager(config.spawn());
    }

    public void movePlayer(double mouseX, double mouseY) {
        player.move(mouseX, mouseY);
    }

    public void update() {
        enemies.forEach(FishModel::move);
        timeManager.increaseTime();

        enemies.removeIf(this::checkEnemyCollisionPlayer);
        enemies.removeIf(this::checkEnemyOutOfBounds);
        enemies.forEach(this::setEnemyEatable);

        if (timeManager.isSpawnEnemyNecessary()) {
            FishModel newEnemy = FishFactory.generate();
            FishPositionGenerator.setRandomCoordinates(newEnemy, gameField);

            enemies.add(newEnemy);
            timeManager.resetSpawnTime();
        }

        if (timeManager.needToDecreaseSpawnTime()) {
            timeManager.reduceDelay();
        }
    }

    private boolean checkEnemyCollisionPlayer(@NotNull FishModel enemy) {
        if (player.checkInBoundsOf(enemy)) {
            if (enemy.isEatable()) {
                score += 1;
                player.increaseSize();
                return true;
            }
            gameOver = true;
        }

        return false;
    }

    private boolean checkEnemyOutOfBounds(@NotNull FishModel enemy) {
        return gameField.checkOutOfBounds(enemy);
    }

    private void setEnemyEatable(@NotNull FishModel enemy) {
        double enemyArea = enemy.getSize().width() * enemy.getSize().height();
        double playerArea = player.getSize().width() * player.getSize().height();


        enemy.setEatable(enemyArea < playerArea);
    }

    public void reset() {
        enemies.clear();
        gameOver = false;
        player.setSize(new Size(30, 20));

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

    private @NotNull FishType matchFishType(@NotNull FishModel fish) {
        return switch (fish) {
            case FatFishModel ignored -> FishType.FAT;
            case JellyfishModel ignored -> FishType.JELLY;
            case LongFishModel ignored -> FishType.LONG;
            case MidFishModel ignored -> FishType.MID;
            case SmallFishModel ignored -> FishType.SMALL;
        };
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}