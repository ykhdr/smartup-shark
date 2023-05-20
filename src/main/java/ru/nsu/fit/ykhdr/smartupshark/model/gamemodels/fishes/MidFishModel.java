package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.strategy.HorizontalSinusoidalStrategy;

public final class MidFishModel extends FishModel {
    public MidFishModel(@NotNull Size size, @NotNull Direction direction) {
        super(size, direction, new HorizontalSinusoidalStrategy());
    }
}
