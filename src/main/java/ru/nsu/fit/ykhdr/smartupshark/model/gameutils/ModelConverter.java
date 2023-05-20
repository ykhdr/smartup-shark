package ru.nsu.fit.ykhdr.smartupshark.model.gameutils;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.PlayerModel;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.fishes.*;

import java.util.List;

public class ModelConverter {

    public static @NotNull PlayerObject convertPlayerToObject(@NotNull PlayerModel model) {
        return new PlayerObject(model.getSize(), model.getPosition(), model.getDirection());
    }

    public static @NotNull List<FishObject> convertFishListToObjectList(@NotNull List<FishModel> modelList) {
        return modelList.stream()
                .map(ModelConverter::convertFishToObject)
                .toList();
    }

    private static @NotNull FishObject convertFishToObject(@NotNull FishModel model) {
        return new FishObject(matchFishType(model),
                model.getSize(),
                model.isEatable(),
                model.getPosition(),
                model.getDirection());
    }

    private static @NotNull FishType matchFishType(@NotNull FishModel fish) {
        return switch (fish) {
            case FatFishModel ignored -> FishType.FAT;
            case JellyfishModel ignored -> FishType.JELLY;
            case LongFishModel ignored -> FishType.LONG;
            case MidFishModel ignored -> FishType.MID;
            case SmallFishModel ignored -> FishType.SMALL;
        };
    }
}
