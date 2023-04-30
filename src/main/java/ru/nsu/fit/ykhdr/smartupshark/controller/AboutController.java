package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.AboutModel;
import ru.nsu.fit.ykhdr.smartupshark.view.AboutView;

public class AboutController implements Controller {
    private final @NotNull AboutView view;
    private final @NotNull AboutModel model;
    private final @NotNull SceneManager sceneManager;

    private boolean isSetup = false;

    public AboutController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new AboutView();
        this.model = new AboutModel();
    }

    private void setupBackBtn() {
        view.getBackBtn().setOnAction(event -> sceneManager.setMenuScene());
    }

    private void setupViewDependencies() {
        setupBackBtn();
    }


    @Override
    public @NotNull Scene getScene() {
        if(!isSetup){
            view.setup(model.getSceneSize());
            setupViewDependencies();

            isSetup = true;
        }

        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
