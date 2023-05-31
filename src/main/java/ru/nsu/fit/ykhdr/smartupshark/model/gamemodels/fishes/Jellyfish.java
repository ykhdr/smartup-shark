package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.strategy.VerticalSinusoidalStrategy;

public final class Jellyfish extends Fish {
    public Jellyfish(@NotNull Size size, @NotNull Position position, @NotNull Direction direction) {
        super(size, position, direction, new VerticalSinusoidalStrategy());
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public void setEatable(boolean ignored) {
    }
}
