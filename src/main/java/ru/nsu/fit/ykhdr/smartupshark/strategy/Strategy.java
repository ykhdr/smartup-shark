package ru.nsu.fit.ykhdr.smartupshark.strategy;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Coordinates;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;

public interface Strategy {
    void move(@NotNull Coordinates coordinates, @NotNull Direction direction);
}