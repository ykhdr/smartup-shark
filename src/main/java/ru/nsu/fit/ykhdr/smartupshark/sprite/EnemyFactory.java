package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class EnemyFactory {
    protected static final Random RANDOM = new Random();



    protected static double getSizeScale() {
        return RANDOM.nextDouble(1, 2);
    }

    public abstract @NotNull Enemy create(double fieldWeight, double fieldHeight);
}
