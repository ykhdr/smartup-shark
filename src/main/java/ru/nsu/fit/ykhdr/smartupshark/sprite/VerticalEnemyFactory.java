package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy.Jellyfish;
import ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy.MovingAlongSineWaveVerticallyEnemy;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class VerticalEnemyFactory extends EnemyFactory {
    private static final @NotNull List<Class<? extends MovingAlongSineWaveVerticallyEnemy>> enemyClasses = new ArrayList<>();

    private static final @NotNull VerticalEnemyFactory instance =  new VerticalEnemyFactory();

    static {
        enemyClasses.add(Jellyfish.class);
    }

    private VerticalEnemyFactory(){}

    public static @NotNull VerticalEnemyFactory getInstance() {
        return instance;
    }

    @Override
    public @NotNull MovingAlongSineWaveVerticallyEnemy create(double fieldWeight, double fieldHeight) {
        try {
            Class<? extends MovingAlongSineWaveVerticallyEnemy> enemyClass = enemyClasses.get(RANDOM.nextInt(enemyClasses.size()));
            Constructor<? extends MovingAlongSineWaveVerticallyEnemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class, double.class);

            MovingAlongSineWaveVerticallyEnemy enemy = constructor.newInstance(getRandomX(fieldWeight), getRandomY(fieldHeight), getSizeScale());
            enemy.setDirection(Direction.UP);

            return enemy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double getRandomX(double fieldWeight) {
        return RANDOM.nextDouble(fieldWeight - 80) + 80;
    }

    private static double getRandomY(double fieldHeight) {
        return fieldHeight;
    }
}
