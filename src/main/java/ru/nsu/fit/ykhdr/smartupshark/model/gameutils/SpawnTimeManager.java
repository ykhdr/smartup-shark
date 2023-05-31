package ru.nsu.fit.ykhdr.smartupshark.model.gameutils;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.config.SpawnTimeConfig;

public class SpawnTimeManager {
    private final double timeStep;
    private final double delayReduction;
    private final double defaultSpawnDelay;
    private double spawnTime = 0;
    private double spawnDelayIncreaseTime = 0;
    private double spawnDelay;

    public SpawnTimeManager(@NotNull SpawnTimeConfig config) {
        this.timeStep = config.timeStep();
        this.delayReduction = config.delayReduction();
        this.spawnDelay = config.spawnDelay();
        this.defaultSpawnDelay = config.spawnDelay();
    }

    public void increaseTime() {
        spawnTime += timeStep;
        spawnDelayIncreaseTime += timeStep;
    }

    public void reduceDelay() {
        spawnDelay -= delayReduction;
        spawnDelayIncreaseTime = 0;
    }

    public boolean isTimeToSpawn() {
        return spawnTime > spawnDelay;
    }

    public boolean needToDecreaseSpawnTime() {
        return spawnDelayIncreaseTime > 10 && spawnDelay > 0.2;
    }

    public void resetSpawnTime() {
        spawnTime = 0;
    }

    public void resetAll() {
        spawnTime = 0;
        spawnDelay = defaultSpawnDelay;
        spawnDelayIncreaseTime = 0;
    }

}
