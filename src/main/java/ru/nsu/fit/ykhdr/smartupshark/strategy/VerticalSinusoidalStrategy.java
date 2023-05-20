package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;

public class VerticalSinusoidalStrategy implements Strategy {

    private final @NotNull SinusoidalLogicData logicData = new SinusoidalLogicData();

    @Override
    public @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = logicData.getAmplitude() * Math.sin(2 * Math.PI * logicData.getFrequency() * time + logicData.getPhase());


        double x = coordinates.x() + logicData.getSpeed() * offset;
        double y = direction == Direction.UP ? coordinates.y() + logicData.getSpeed() : coordinates.y() - logicData.getSpeed();

        return new Position(x, y);
    }
}
