package ru.nsu.fit.ykhdr.smartupshark.model;

public class GameTimeManager {
    private double spawnTime = 0;
    private double spawnDelayIncreaseTime = 0;
    private double spawnDelay = 1;

    public void increaseTime() {
        spawnTime += 0.016;
        spawnDelayIncreaseTime += 0.016;
    }

    public void reduceDelay() {
        spawnDelay -= 0.05;
        spawnDelayIncreaseTime = 0;
    }

    public boolean isSpawnEnemyNecessary() {
        return spawnTime > spawnDelay;
    }

    public boolean isIncreaseSpawnDelayNecessary() {
        return spawnDelayIncreaseTime > 10 && spawnDelay > 0.2;
    }

    public void resetSpawnTime() {
        spawnTime = 0;
    }


    public void resetAll(){
        spawnTime = 0;
        spawnDelay = 1;
        spawnDelayIncreaseTime = 0;
    }

}
