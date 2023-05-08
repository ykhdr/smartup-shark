package ru.nsu.fit.ykhdr.smartupshark.model;


import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.*;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameModel {

    private final @NotNull List<FishModel> enemies = new ArrayList<>();
    private final @NotNull PlayerModel player = new PlayerModel(Direction.LEFT);

    private final @NotNull SceneSize sceneSize = new SceneSize(1024, 720);
    private final @NotNull GameField gameField = new GameField(sceneSize.width(), sceneSize.height());

    private final @NotNull SpawnTimeManager timeManager = new SpawnTimeManager();
    private int score = 0;
    private boolean gameOver = false;

    {
        player.setPosition(new Position(gameField.width() / 2, gameField.height() / 2));
    }

    public GameModel(){
    }

    public GameModel(@NotNull String propertyPath){
        ConfigParser configParser = new ConfigParser(propertyPath);
        addFishFromConfig(configParser.parse());
    }

    private void addFishFromConfig(@NotNull Map<FishType,Position> configModels) {
        for (Map.Entry<FishType, Position> entry : configModels.entrySet()) {
            FishModel fishModel = FishFactory.generate(entry.getKey());
            fishModel.setPosition(entry.getValue());
            setEnemyEatable(fishModel);
            enemies.add(fishModel);
        }
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
            FishPositionGenerator.setRandomCoordinates(newEnemy, sceneSize);

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

        timeManager.resetAll();
        score = 0;
    }

    public @NotNull GameObjects getGameObjects() {
        List<FishObject> fishObjectList = ModelConverter.convertFishListToObjectList(enemies);
        PlayerObject playerObject = ModelConverter.convertPlayerToObject(player);

        return new GameObjects(fishObjectList, playerObject, score);
    }


    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public @NotNull SceneSize getSceneSize() {
        return sceneSize;
    }
}