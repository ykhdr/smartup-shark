package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Coordinates;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;

public class HorizontalSinusoidalStrategy extends SinusoidalLogic implements Strategy{
    @Override
    public void move(@NotNull Coordinates coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        coordinates.setY(coordinates.getY() +  speed * offset);
        coordinates.setX( direction == Direction.RIGHT ? coordinates.getX() + speed : coordinates.getX() - speed);
    }
}
