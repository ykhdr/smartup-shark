package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.SceneSize;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.GameObjectIdManager;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RandomFishObjectFactory {
    private static final @NotNull List<Class<? extends FishObject>> fishClasses = new ArrayList<>();

    private static final @NotNull Random RANDOM = new Random();

    static {
        fishClasses.add(FatFishObject.class);
        fishClasses.add(JellyfishObject.class);
        fishClasses.add(LongFishObject.class);
        fishClasses.add(MidFishObject.class);
        fishClasses.add(SmallFishObject.class);
    }

    /*
    каждая рыба имеет поле говорящее о том в каком направлении она может двигаться
    здесь идет проверка на это

     */
    public static @NotNull FishObject getFish(@NotNull SceneSize fieldSize) {
        try {
            Class<? extends FishObject> clazz = fishClasses.get(RANDOM.nextInt(fishClasses.size()));
            Constructor<? extends FishObject> constructor = clazz.getDeclaredConstructor(double.class, int.class);

            FishObject fish = constructor.newInstance(getSizeScale(), GameObjectIdManager.getNextId());
            setRandomAvailableDirection(fish);
            setRandomCoordinates(fish, fieldSize);

            return fish;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private static void setRandomAvailableDirection(@NotNull FishObject fish) {
        List<Direction> availableDirections =
                switch (fish) {
                    case FatFishObject fatFish -> fatFish.getAvailableDirections();
                    case JellyfishObject jellyfish -> jellyfish.getAvailableDirections();
                    case LongFishObject longFish -> longFish.getAvailableDirections();
                    case MidFishObject midFish -> midFish.getAvailableDirections();
                    case SmallFishObject smallFish -> smallFish.getAvailableDirections();
                };

        fish.setDirection(availableDirections.get(RANDOM.nextInt(availableDirections.size())));
    }

    private static void setRandomCoordinates(@NotNull FishObject fish, @NotNull SceneSize fieldSize) {
        switch (fish.getDirection()) {
            case RIGHT, LEFT -> setRandomHorizontalCoordinates(fish, fieldSize);
            case UP, DOWN -> setRandomVerticalCoordinates(fish, fieldSize);
        }
    }

    private static void setRandomHorizontalCoordinates(@NotNull FishObject fish, @NotNull SceneSize fieldSize) {
        fish.getCoordinates().setX(fish.getDirection() == Direction.RIGHT ? -50 : fieldSize.width() + 50);
        fish.getCoordinates().setY(RANDOM.nextDouble(fieldSize.height() - 80) + 80);
    }

    private static void setRandomVerticalCoordinates(@NotNull FishObject fish, @NotNull SceneSize fieldSize) {
        fish.getCoordinates().setX(RANDOM.nextDouble(fieldSize.width() - 80) + 80);
        fish.getCoordinates().setY(fish.getDirection() == Direction.DOWN ? -50 : fieldSize.height() + 50);
    }

    private static double getSizeScale() {
        return RANDOM.nextDouble(1, 2);
    }
}
