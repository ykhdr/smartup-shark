package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;

import java.util.Random;

public class FishPositionGenerator {
    private static final @NotNull Random RANDOM = new Random();

    public static @NotNull Position getRandomCoordinates(@NotNull Direction direction, @NotNull GameField gameField) {
        return switch (direction) {
            case RIGHT, LEFT -> getRandomHorizontalCoordinates(direction, gameField);
            case UP, DOWN -> getRandomVerticalCoordinates(direction, gameField);
        };
    }

    private static @NotNull Position getRandomHorizontalCoordinates(@NotNull Direction direction, @NotNull GameField gameField) {
        double x = direction == Direction.RIGHT ? -gameField.spawnOffset() : gameField.width() + gameField.spawnOffset();
        double y = RANDOM.nextDouble(gameField.height() - gameField.spawnOffset()) + gameField.spawnOffset();
        return new Position(x, y);
    }

    private static @NotNull Position getRandomVerticalCoordinates(@NotNull Direction direction, @NotNull GameField gameField) {
        double x = RANDOM.nextDouble(gameField.width() - gameField.spawnOffset()) + gameField.spawnOffset();
        double y = direction == Direction.UP ? -gameField.spawnOffset() : gameField.height() + gameField.spawnOffset();
        return new Position(x, y);
    }
}
