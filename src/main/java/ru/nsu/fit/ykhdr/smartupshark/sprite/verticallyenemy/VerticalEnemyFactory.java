package ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;
import ru.nsu.fit.ykhdr.smartupshark.sprite.EnemyFactory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class VerticalEnemyFactory extends EnemyFactory {
    private static final @NotNull List<Class<? extends VerticalSinusoidalEnemy>> enemyClasses = new ArrayList<>();

    private static final @NotNull VerticalEnemyFactory instance =  new VerticalEnemyFactory();

    static {
        enemyClasses.add(Jellyfish.class);
    }

    private VerticalEnemyFactory(){}

    public static @NotNull VerticalEnemyFactory getInstance() {
        return instance;
    }

    @Override
    public @NotNull VerticalSinusoidalEnemy create(double fieldWeight, double fieldHeight) {
        try {
            Class<? extends VerticalSinusoidalEnemy> enemyClass = enemyClasses.get(RANDOM.nextInt(enemyClasses.size()));
            Constructor<? extends VerticalSinusoidalEnemy> constructor = enemyClass.getDeclaredConstructor(double.class, double.class, double.class);

            VerticalSinusoidalEnemy enemy = constructor.newInstance(getRandomX(fieldWeight), getRandomY(fieldHeight), getSizeScale());
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
