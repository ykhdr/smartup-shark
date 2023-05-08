package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;
import ru.nsu.fit.ykhdr.smartupshark.strategy.HorizontalSinusoidalStrategy;

import java.util.ArrayList;
import java.util.List;

public final class SmallFishModel extends FishModel {
    private static final @NotNull List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.add(Direction.LEFT);
        availableDirections.add(Direction.RIGHT);
    }

    public SmallFishModel(double sizeScale) {
        super(new HorizontalSinusoidalStrategy(), Direction.LEFT);

        this.size = new Size(20 * sizeScale, 10 * sizeScale);
    }

    @Override
    public @NotNull List<Direction> getAvailableDirections() {
        return availableDirections;
    }
}
