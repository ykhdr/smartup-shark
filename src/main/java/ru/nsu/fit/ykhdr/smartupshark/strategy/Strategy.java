package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;

public interface Strategy {
    @NotNull Position move(@NotNull Position coordinates, @NotNull Direction direction);
}