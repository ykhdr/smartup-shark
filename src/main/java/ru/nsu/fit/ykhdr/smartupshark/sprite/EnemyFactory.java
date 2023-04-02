package ru.nsu.fit.ykhdr.smartupshark.sprite;

import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.*;
import ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy.Jellyfish;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {
    private static final Random random = new Random();
    private static final List<Class<? extends Enemy>> enemyClasses = new ArrayList<>();


    static {
        enemyClasses.add(LongFish.class);
        enemyClasses.add(FatFish.class);
        enemyClasses.add(SmallFish.class);
        enemyClasses.add(MidFish.class);
        enemyClasses.add(Jellyfish.class);
    }


    public static Enemy getRandomEnemy(double fieldWeight, double fieldHeight, double playerSize) {
        try {
            Class<? extends Enemy> enemyClass = enemyClasses.get(random.nextInt(enemyClasses.size()));
            Constructor<? extends Enemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class, double.class, double.class);

            return constructor.newInstance(fieldWeight,fieldHeight, getSizeScale(), playerSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double getSizeScale() {
        return random.nextDouble(1, 2);
    }
}
