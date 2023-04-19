package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.view.AboutView;

public class AboutController {
    private final @NotNull AboutView view;

    public AboutController(@NotNull Runnable backToMenuScene) {
        this.view = new AboutView();

        setupBackBtn(backToMenuScene);
    }

    // CR: use custom interface instead of runnable
    private void setupBackBtn(@NotNull Runnable backToMenuScene){
        view.getBackBtn().setOnAction(event -> backToMenuScene.run());
    }

    public @NotNull Scene getScene(){
        // CR: create view here (and store to field?)
        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
