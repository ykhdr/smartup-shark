package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;

public class GameObjects extends HashSet<GameObject> {
    public GameObjects(@NotNull GameObject... gameObjects) {
        super.addAll(List.of(gameObjects));
    }
}