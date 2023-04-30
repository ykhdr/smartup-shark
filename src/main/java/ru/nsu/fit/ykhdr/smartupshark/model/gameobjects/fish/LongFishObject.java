package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;
import ru.nsu.fit.ykhdr.smartupshark.strategy.HorizontalSinusoidalStrategy;

import java.util.ArrayList;
import java.util.List;

public final class LongFishObject extends FishObject {
    private static final @NotNull List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.add(Direction.LEFT);
        availableDirections.add(Direction.RIGHT);
    }

    public LongFishObject(double sizeScale, int id) {
        super(new HorizontalSinusoidalStrategy(),Direction.LEFT,id );

        size.setWidth( 60 * sizeScale);
        size.setHeight(30 * sizeScale);
    }


    public @NotNull List<Direction> getAvailableDirections() {
        return availableDirections;
    }
}
