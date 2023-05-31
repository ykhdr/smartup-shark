package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes;


import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.config.FactoryConfig;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;
import ru.nsu.fit.ykhdr.smartupshark.model.gameutils.FishPositionGenerator;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class FishFactory {

    @FunctionalInterface
    private interface FishConstructor {
        @NotNull Fish getFish(@NotNull Size size, @NotNull Position position, @NotNull Direction direction);
    }

    private static final @NotNull Map<FishType, FishConstructor> FISH_FACTORIES = Map.of(
            FishType.FAT, FatFish::new,
            FishType.JELLY, Jellyfish::new,
            FishType.LONG, LongFish::new,
            FishType.MID, MidFish::new,
            FishType.SMALL, SmallFish::new
    );

    private final @NotNull Map<FishType, Size> defaultSizes;

    public FishFactory(@NotNull FactoryConfig config) {
        this.defaultSizes = config.defaultFishSizes();
    }

    public @NotNull Fish generate(@NotNull GameField fieldSize) {
        Random random = new Random();

        double sizeScale = random.nextDouble(1, 2);
        FishType fishType = FishType.values()[random.nextInt(FISH_FACTORIES.size())];
        List<Direction> directionList = Fish.getAvailableDirections(fishType);
        Size size = defaultSizes.get(fishType);

        if (size == null) {
            size = new Size(random.nextDouble(10, 30), random.nextDouble(10, 30));
        }

        Direction direction = directionList.get(random.nextInt(directionList.size()));
        Size scaledSize = new Size(size.width() * sizeScale, size.height() * sizeScale);
        Position position = FishPositionGenerator.getRandomCoordinates(direction, fieldSize);

        return FISH_FACTORIES.get(fishType).getFish(scaledSize, position, direction);
    }

    public @NotNull Fish createFishFromObject(@NotNull FishObject fishObject) {
        Fish fish = FISH_FACTORIES.get(fishObject.type()).getFish(fishObject.size(), fishObject.position(), fishObject.direction());
        fish.setEatable(fishObject.isEatable());

        return fish;
    }
}