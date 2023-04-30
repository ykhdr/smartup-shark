package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.GameObject;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;
import ru.nsu.fit.ykhdr.smartupshark.strategy.Strategy;

public sealed abstract class FishObject extends GameObject permits FatFishObject, JellyfishObject, LongFishObject, MidFishObject, SmallFishObject {
    private boolean isEatable;
    private final @NotNull Strategy strategy;
    private @NotNull Direction direction;

    protected FishObject(@NotNull Strategy strategy, @NotNull Direction direction, int id) {
        super(id);
        this.strategy = strategy;
        this.direction = direction;
    }

    public void move() {
        strategy.move(coordinates, direction);
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }

    public @NotNull Direction getDirection() {
        return direction;
    }

    public void setDirection(@NotNull Direction direction) {
        this.direction = direction;
    }
}
