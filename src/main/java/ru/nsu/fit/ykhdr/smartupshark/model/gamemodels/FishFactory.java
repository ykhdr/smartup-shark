package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;


import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.FishType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@FunctionalInterface
public interface FishFactory {
    @NotNull List<FishFactory> FISH_FACTORIES = Arrays.asList(
            FatFishModel::new,
            JellyfishModel::new,
            LongFishModel::new,
            MidFishModel::new,
            SmallFishModel::new
    );

    static @NotNull FishModel generate() {
        Random random = new Random();
        FishModel fish = FISH_FACTORIES.get(random.nextInt(FISH_FACTORIES.size())).getFish(random.nextDouble(1, 2));
        fish.setDirection(fish.getAvailableDirections().get(random.nextInt(fish.getAvailableDirections().size())));
        return fish;
    }

    static @NotNull FishModel generate(@NotNull FishType type){
        FishModel fish = FISH_FACTORIES.get(type.ordinal()).getFish(1);
        fish.setDirection(fish.getAvailableDirections().get(0));
        return fish;
    }

    @NotNull FishModel getFish(double scale);
}