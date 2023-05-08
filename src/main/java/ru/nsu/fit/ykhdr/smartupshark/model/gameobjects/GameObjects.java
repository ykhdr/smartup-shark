package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record GameObjects(@NotNull List<FishObject> enemies, @NotNull PlayerObject player, int score) {
}