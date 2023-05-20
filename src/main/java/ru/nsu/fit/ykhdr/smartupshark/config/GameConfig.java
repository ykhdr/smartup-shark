package ru.nsu.fit.ykhdr.smartupshark.config;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

public record GameConfig(@NotNull Size fieldSize, @NotNull GameObjects gameObjects) {
}
