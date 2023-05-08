package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.SceneSize;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;

import java.util.Random;

public class FishPositionGenerator {
    private static final @NotNull Random RANDOM = new Random();

    public static void setRandomCoordinates(@NotNull FishModel fish, @NotNull SceneSize fieldSize) {
        switch (fish.getDirection()) {
            case RIGHT, LEFT -> setRandomHorizontalCoordinates(fish, fieldSize);
            case UP, DOWN -> setRandomVerticalCoordinates(fish, fieldSize);
        }
    }

    private static void setRandomHorizontalCoordinates(@NotNull FishModel fish, @NotNull SceneSize fieldSize) {
        double x = fish.getDirection() == Direction.RIGHT ? -50 : fieldSize.width() + 50;
        double y = RANDOM.nextDouble(fieldSize.height() - 80) + 80;
        fish.setPosition(new Position(x,y));
    }

    private static void setRandomVerticalCoordinates(@NotNull FishModel fish, @NotNull SceneSize fieldSize) {
        double x = RANDOM.nextDouble(fieldSize.width() - 80) + 80;
        double y = fish.getDirection() == Direction.DOWN ? -50 : fieldSize.height() + 50;
        fish.setPosition(new Position(x,y));
    }
}
