package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;

public class HorizontalSinusoidalStrategy extends SinusoidalLogic implements Strategy {
    @Override
    public @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        double x = direction == Direction.RIGHT ? coordinates.x() + speed : coordinates.x() - speed;
        double y = coordinates.y() + speed * offset;

        return new Position(x, y);
    }
}
