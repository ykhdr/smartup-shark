package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;

public class ScoreboardModel {
    private final @NotNull SceneSize sceneSize = new SceneSize(1024, 720);
    public @NotNull SceneSize getSceneSize() {
        return sceneSize;
    }
}
