package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.AboutView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AboutController implements Controller {

    private static final @NotNull Path TEXT_FILE_PATH = Path.of("src/main/resources/data/about.txt");
    private final @NotNull AboutView view;
    private final @NotNull SceneManager sceneManager;

    private boolean isSetup = false;

    public AboutController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new AboutView();
    }

    private void setupViewDependencies() {
        view.setActionOnBtnBackToMenu(event -> sceneManager.setMenuScene());
        view.setText(readFile());
    }

    private @NotNull String readFile() {
        try {
            return new String(Files.readAllBytes(TEXT_FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Scene getScene() {
        if (!isSetup) {
            view.setup();
            setupViewDependencies();

            isSetup = true;
        }

        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
