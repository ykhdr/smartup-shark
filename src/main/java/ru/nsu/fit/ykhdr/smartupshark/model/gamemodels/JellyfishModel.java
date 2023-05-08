package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;
import ru.nsu.fit.ykhdr.smartupshark.strategy.VerticalSinusoidalStrategy;

import java.util.ArrayList;
import java.util.List;

public final class JellyfishModel extends FishModel {
    private static final @NotNull List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.add(Direction.UP);
        availableDirections.add(Direction.DOWN);
    }

    public JellyfishModel(double sizeScale) {
        super(new VerticalSinusoidalStrategy(), Direction.UP);

        this.size = new Size(25 * sizeScale, 40 * sizeScale);
    }

    @Override
    public boolean isEatable() {
        return false;
    }

    @Override
    public void setEatable(boolean ignored) {
    }

    @Override
    public @NotNull List<Direction> getAvailableDirections() {
        return availableDirections;
    }
}
