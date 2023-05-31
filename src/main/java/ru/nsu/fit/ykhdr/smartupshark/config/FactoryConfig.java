package ru.nsu.fit.ykhdr.smartupshark.config;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

import java.util.Map;

public record FactoryConfig(@NotNull Map<FishType, Size> defaultFishSizes) {
}
