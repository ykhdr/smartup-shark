package ru.nsu.fit.ykhdr.smartupshark.sprte;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HorizontalEnemyFactory {
    private static final Random random = new Random();
    private static final List<Class<? extends HorizontalEnemy>> enemyClasses = new ArrayList<>();

    static {
        enemyClasses.add(LongFish.class);
        enemyClasses.add(FatFish.class);
        enemyClasses.add(SmallFish.class);
        enemyClasses.add(MidFish.class);
    }

    // TODO: 25.03.2023 подумать над расширяемостью
    public static HorizontalEnemy getRandomEnemy(double fieldWeight, double fieldHeight) {
        try {
            Class<? extends HorizontalEnemy> enemyClass = enemyClasses.get(random.nextInt(enemyClasses.size()));
            Constructor<? extends HorizontalEnemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class, double.class);

            HorizontalEnemy enemy = constructor.newInstance(getRandomX(fieldWeight), getRandomY(fieldHeight), getSizeScale());

            enemy.setPhase(random.nextDouble(1));
            enemy.setSpeed(random.nextDouble(1, 2));
            enemy.setAmplitude(random.nextDouble(1));
            enemy.setFrequency(random.nextDouble(0.4, 0.5));

            return enemy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double getRandomX(double fieldWeight) {
        return random.nextBoolean() ? -50 : fieldWeight + 50;
    }

    private static double getRandomY(double fieldHeight) {
        return random.nextDouble(fieldHeight - 80) + 80;
    }

    private static double getSizeScale() {
        return random.nextDouble(1, 2);
    }
}
