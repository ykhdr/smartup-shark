package ru.nsu.fit.ykhdr.smartupshark.model.gameutils;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes.FishModel;

import java.util.Random;

public class FishPositionGenerator {
    private static final @NotNull Random RANDOM = new Random();

    public static void setRandomCoordinates(@NotNull FishModel fish, @NotNull GameField fieldSize) {
        switch (fish.getDirection()) {
            case RIGHT, LEFT -> setRandomHorizontalCoordinates(fish, fieldSize);
            case UP, DOWN -> setRandomVerticalCoordinates(fish, fieldSize);
        }
    }

    private static void setRandomHorizontalCoordinates(@NotNull FishModel fish, @NotNull GameField fieldSize) {
        double x = fish.getDirection() == Direction.RIGHT ? -GameField.SPAWN_OFFSET : fieldSize.width() + GameField.SPAWN_OFFSET;
        double y = RANDOM.nextDouble(fieldSize.height() - GameField.SPAWN_OFFSET) + GameField.SPAWN_OFFSET;
        fish.setPosition(new Position(x, y));
    }

    private static void setRandomVerticalCoordinates(@NotNull FishModel fish, @NotNull GameField fieldSize) {
        double x = RANDOM.nextDouble(fieldSize.width() - GameField.SPAWN_OFFSET) + GameField.SPAWN_OFFSET;
        double y = fish.getDirection() == Direction.UP ? -GameField.SPAWN_OFFSET : fieldSize.height() + GameField.SPAWN_OFFSET;
        fish.setPosition(new Position(x, y));
    }
}
