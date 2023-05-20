package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.strategy.VerticalSinusoidalStrategy;

public final class JellyfishModel extends FishModel {
    public JellyfishModel(@NotNull Size size, @NotNull Direction direction) {
        super(size, direction, new VerticalSinusoidalStrategy());
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public void setEatable(boolean ignored) {
    }
}
