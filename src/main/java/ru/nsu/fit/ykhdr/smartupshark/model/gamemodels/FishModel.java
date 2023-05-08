package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.strategy.Strategy;

import java.util.List;

public sealed abstract class FishModel extends GameObjectModel permits FatFishModel, JellyfishModel, LongFishModel, MidFishModel, SmallFishModel {
    private boolean isEatable;
    private final @NotNull Strategy strategy;

    protected FishModel(@NotNull Strategy strategy, @NotNull Direction direction) {
        super(direction);
        this.strategy = strategy;
    }

    public void move() {
        position = strategy.move(position, direction);
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }

    public abstract @NotNull List<Direction> getAvailableDirections();
}
