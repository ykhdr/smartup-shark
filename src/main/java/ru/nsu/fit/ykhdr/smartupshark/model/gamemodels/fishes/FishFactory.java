package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;


import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

import java.util.List;
import java.util.Map;
import java.util.Random;

// CR: regular class, init with fishes sizes
@FunctionalInterface
public interface FishFactory {
    @NotNull Map<FishType, FishFactory> FISH_FACTORIES = Map.of(
            FishType.FAT, FatFishModel::new,
            FishType.JELLY, JellyfishModel::new,
            FishType.LONG, LongFishModel::new,
            FishType.MID, MidFishModel::new,
            FishType.SMALL, SmallFishModel::new
    );

    static @NotNull FishModel generate() {
        Random random = new Random();

        double sizeScale = random.nextDouble(1, 2);
        FishType fishType = FishType.values()[random.nextInt(FISH_FACTORIES.size())];
        List<Direction> directionList = FishModel.getAvailableDirections(fishType);
        Size size = FishModel.getDefaultSize(fishType);

        Direction direction = directionList.get(random.nextInt(directionList.size()));
        Size scaledSize = new Size(size.width() * sizeScale, size.height() * sizeScale);

        return FISH_FACTORIES.get(fishType).getFish(scaledSize, direction);
    }
    @NotNull FishModel getFish(@NotNull Size size, @NotNull Direction direction);
}