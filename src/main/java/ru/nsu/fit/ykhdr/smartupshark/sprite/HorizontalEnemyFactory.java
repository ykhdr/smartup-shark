package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class HorizontalEnemyFactory extends EnemyFactory {

    private static final @NotNull List<Class<? extends MovingAlongSineWaveHorizontallyEnemy>> enemyClasses = new ArrayList<>();

    private static final @NotNull HorizontalEnemyFactory instance =  new HorizontalEnemyFactory();

    static {
        enemyClasses.add(LongFish.class);
        enemyClasses.add(FatFish.class);
        enemyClasses.add(SmallFish.class);
        enemyClasses.add(MidFish.class);
    }

    private HorizontalEnemyFactory(){}

    public static @NotNull HorizontalEnemyFactory getInstance() {
        return instance;
    }

    @Override
    public @NotNull MovingAlongSineWaveHorizontallyEnemy create(double fieldWeight, double fieldHeight) {
        try {
            Class<? extends MovingAlongSineWaveHorizontallyEnemy> enemyClass = enemyClasses.get(RANDOM.nextInt(enemyClasses.size()));
            Constructor<? extends MovingAlongSineWaveHorizontallyEnemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class, double.class);

            MovingAlongSineWaveHorizontallyEnemy enemy = constructor.newInstance(getRandomX(fieldWeight), getRandomY(fieldHeight), getSizeScale());
            // TODO: 11.04.2023 Where I have to place this method?
            enemy.setDirection(enemy.getX() < 0 ? Direction.RIGHT : Direction.LEFT);

            return enemy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static double getRandomX(double fieldWeight) {
        return RANDOM.nextBoolean() ? -50 : fieldWeight + 50;
    }

    private static double getRandomY(double fieldHeight) {
        return RANDOM.nextDouble(fieldHeight - 80) + 80;
    }
}
