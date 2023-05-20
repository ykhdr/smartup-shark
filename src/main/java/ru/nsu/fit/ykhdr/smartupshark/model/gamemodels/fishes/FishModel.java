package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.GameObjectModel;
import ru.nsu.fit.ykhdr.smartupshark.strategy.Strategy;

import java.util.List;

public sealed abstract class FishModel extends GameObjectModel permits FatFishModel, JellyfishModel, LongFishModel, MidFishModel, SmallFishModel {
    private boolean isEatable;
    private final @NotNull Strategy strategy;

    protected FishModel(@NotNull Size size, @NotNull Direction direction, @NotNull Strategy strategy) {
        super(size, direction);
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

    public static @NotNull List<Direction> getAvailableDirections(@NotNull FishType type) {
        return switch (type) {
            case FAT, SMALL, LONG, MID -> List.of(Direction.LEFT, Direction.RIGHT);
            case JELLY -> List.of(Direction.DOWN, Direction.UP);
        };
    }

    public static @NotNull Size getDefaultSize(@NotNull FishType type) {
        return switch (type) {
            case FAT -> new Size(40, 40);
            case SMALL -> new Size(20, 10);
            case JELLY -> new Size(25,40);
            case LONG -> new Size(60, 30);
            case MID -> new Size(35, 25);
        };
    }
}
