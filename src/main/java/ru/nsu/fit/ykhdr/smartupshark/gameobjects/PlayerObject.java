package ru.nsu.fit.ykhdr.smartupshark.gameobjects;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

public record PlayerObject(@NotNull Size size, @NotNull Position position, @NotNull Direction direction) {
}
