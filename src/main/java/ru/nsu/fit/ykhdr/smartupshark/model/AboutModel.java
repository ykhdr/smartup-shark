package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AboutModel {
    private final @NotNull SceneSize sceneSize = new SceneSize(1024, 720);

    private final @NotNull Path FILE_PATH = Path.of("src/main/resources/data/about.txt");

    public @NotNull SceneSize getSceneSize() {
        return sceneSize;
    }

    public @NotNull String getAboutText() {
        return readFile();
    }

    private @NotNull String readFile() {
        try {
            return new String(Files.readAllBytes(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
