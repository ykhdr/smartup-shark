package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEnemyFactory {
    private static final @NotNull List<EnemyFactory> factoriesList = new ArrayList<>();


    static {
        factoriesList.add(VerticalEnemyFactory.getInstance());
        factoriesList.add(HorizontalEnemyFactory.getInstance());
    }

    public static @NotNull EnemyFactory getRandomFactory(){
        return factoriesList.get(new Random().nextInt(factoriesList.size()));
    }
}
