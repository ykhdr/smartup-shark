package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;

public interface Strategy {
    @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction);
}