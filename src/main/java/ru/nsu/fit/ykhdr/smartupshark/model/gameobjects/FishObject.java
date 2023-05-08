package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;

public record FishObject(@NotNull FishType type, @NotNull Size size, boolean isEatable,
                         @NotNull Position position, @NotNull Direction direction) {
}