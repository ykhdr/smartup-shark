package ru.nsu.fit.ykhdr.smartupshark.gameobjects;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;

public record PlayerObject(@NotNull Size size, @NotNull Position position, @NotNull Direction direction) {
}
