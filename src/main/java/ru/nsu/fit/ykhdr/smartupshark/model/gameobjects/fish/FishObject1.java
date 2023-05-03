package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish;

import java.util.List;

public record FishObject1(FishType type, Size size, boolean isEatable, Position position) {}

enum FishType {
    FAT,
    JELLY,
    LONG,
    MID,
    SMALL
}

record Size(int x, int y) {}

record Position(int x, int y) {}

record PlayerObject1(Size size, Position position) {}

record GameObjects1(List<FishObject1> enemies, PlayerObject1 player, int score) {}


