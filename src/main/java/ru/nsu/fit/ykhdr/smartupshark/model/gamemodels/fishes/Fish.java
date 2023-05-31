package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.GameObjectModel;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.strategy.Strategy;

import java.util.List;

public sealed abstract class Fish extends GameObjectModel permits FatFish, Jellyfish, LongFish, MidFish, SmallFish {

    private boolean isEatable;
    private final @NotNull Strategy strategy;

    protected Fish(@NotNull Size size, @NotNull Position position, @NotNull Direction direction, @NotNull Strategy strategy) {
        super(size, position, direction);
        this.strategy = strategy;
    }

    public void move() {
          setPosition(strategy.move(getPosition(), getDirection()));
    }

    public boolean isEatable() {
        return isEatable;
    }

    public void setEatable(boolean eatable) {
        isEatable = eatable;
    }

    public static @NotNull List<Direction> getAvailableDirections(@NotNull FishType type) {
        return switch (type) {
            case FAT, SMALL, LONG, MID -> List.of(Direction.LEFT, Direction.RIGHT);
            case JELLY -> List.of(Direction.DOWN, Direction.UP);
        };
    }
}
