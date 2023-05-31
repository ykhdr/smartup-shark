package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;

public class HorizontalSinusoidalStrategy implements Strategy {

    private final @NotNull SinusoidalLogicData logicData = new SinusoidalLogicData();

    @Override
    public @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction) {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = logicData.getAmplitude() * Math.sin(2 * Math.PI * logicData.getFrequency() * time + logicData.getPhase());

        double x = direction == Direction.RIGHT ? coordinates.x() + logicData.getSpeed() : coordinates.x() - logicData.getSpeed();
        double y = coordinates.y() + logicData.getSpeed() * offset;

        return new Position(x, y);
    }
}
