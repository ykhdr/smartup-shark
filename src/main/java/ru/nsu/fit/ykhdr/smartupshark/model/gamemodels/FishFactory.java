package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;


import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@FunctionalInterface
public interface FishFactory {
    @NotNull List<FishFactory> FISH_FACTORIES = Arrays.asList(
            SmallFishModel::new,
            LongFishModel::new,
            MidFishModel::new,
            JellyfishModel::new,
            FatFishModel::new
    );

    static @NotNull FishModel generate() {
        Random random = new Random();
        FishModel fish = FISH_FACTORIES.get(random.nextInt(FISH_FACTORIES.size())).getFish(random.nextDouble(1, 2));
        fish.setDirection(fish.getAvailableDirections().get(random.nextInt(fish.getAvailableDirections().size())));
        return fish;
    }

    @NotNull FishModel getFish(double scale);
}