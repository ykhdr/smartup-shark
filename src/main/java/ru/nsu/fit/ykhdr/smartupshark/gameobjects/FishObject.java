package ru.nsu.fit.ykhdr.smartupshark.gameobjects;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

public record FishObject(@NotNull FishType type, @NotNull Size size, boolean isEatable,
                         @NotNull Position position, @NotNull Direction direction) {
}