package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;

public class VerticalSinusoidalStrategy extends SinusoidalLogic implements Strategy {
    @Override
    public @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);


        double x = coordinates.x() + speed * offset;
        double y = direction == Direction.UP ? coordinates.y() + speed : coordinates.y() - speed;

        return new Position(x, y);
    }
}
