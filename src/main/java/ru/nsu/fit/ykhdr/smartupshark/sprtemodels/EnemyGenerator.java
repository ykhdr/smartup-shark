package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyGenerator {
    private static final Random random = new Random();
    private static final List<Class<? extends Enemy>> enemyClasses = new ArrayList<>();

    static {
        enemyClasses.add(LongFish.class);
        enemyClasses.add(FatFish.class);
        enemyClasses.add(SmallFish.class);

    }

    // TODO: 25.03.2023 подумать над расширяемостью
    public static Enemy generateRandomEnemy(double fieldWeight, double fieldHeight) {
        try {
            Class<? extends Enemy> enemyClass = enemyClasses.get(random.nextInt(enemyClasses.size()));
            Constructor<? extends Enemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class);

            Enemy enemy = constructor.newInstance(getRandomX(fieldWeight), getRandomY(fieldHeight));

            double sizeScale = getSizeScale();

            enemy.setWidth(enemy.getWidth() * sizeScale);
            enemy.setHeight(enemy.getHeight() * sizeScale);

            enemy.setPhase(random.nextDouble(1));
            enemy.setSpeed(random.nextDouble(1, 3));
            enemy.setAmplitude(random.nextDouble(1));
            if (enemy.getX() > 0) {
                enemy.setRightDirection(false);
            }
            return enemy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double getRandomX(double fieldWeight) {
        return random.nextBoolean() ? -50 : fieldWeight + 50;
    }

    private static double getRandomY(double fieldHeight) {
        return random.nextDouble(fieldHeight - 150) + 80;
    }


    private static double getSizeScale() {
        return random.nextDouble(1, 2);
    }
}
