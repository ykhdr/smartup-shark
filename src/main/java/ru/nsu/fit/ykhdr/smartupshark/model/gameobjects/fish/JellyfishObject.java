package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;
import ru.nsu.fit.ykhdr.smartupshark.strategy.VerticalSinusoidalStrategy;

import java.util.ArrayList;
import java.util.List;

public final class JellyfishObject extends FishObject {
    private static final @NotNull List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.add(Direction.UP);
        availableDirections.add(Direction.DOWN);
    }

    public JellyfishObject(double sizeScale, int id) {
        super(new VerticalSinusoidalStrategy(), Direction.UP,id );

        size.setWidth( 25 * sizeScale);
        size.setHeight(40 * sizeScale);
    }

    public @NotNull List<Direction> getAvailableDirections() {
        return availableDirections;
    }
}
