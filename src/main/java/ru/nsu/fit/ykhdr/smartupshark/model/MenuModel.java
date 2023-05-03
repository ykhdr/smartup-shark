package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;

// CR: move to view
public class MenuModel {
    private final @NotNull SceneSize sceneSize = new SceneSize(1024, 720);
    public @NotNull SceneSize getSceneSize() {
        return sceneSize;
    }
}
