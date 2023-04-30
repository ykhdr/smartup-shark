package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Coordinates;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;

public class VerticalSinusoidalStrategy extends SinusoidalLogic implements Strategy {
    @Override
    public void move(@NotNull Coordinates coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        coordinates.setX(coordinates.getX() + offset * speed);
        coordinates.setY(direction == Direction.UP ? coordinates.getY() - speed : coordinates.getY() + speed);
    }
}
