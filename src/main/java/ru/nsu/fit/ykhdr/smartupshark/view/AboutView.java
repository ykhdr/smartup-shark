package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class AboutView extends VBox {
    private final @NotNull Label aboutLabel = new Label();

    private final @NotNull Button backBtn = new Button();

    public AboutView() {
        configureView();

        configureLabel();
        configureBackBtn();
        addStylesheets();
    }

    private void configureView() {
        setPrefHeight(720);
        setPrefWidth(1024);
        setAlignment(Pos.CENTER);

        getStyleClass().add("bg");

        getChildren().add(aboutLabel);
        getChildren().add(backBtn);
    }

    private void addStylesheets(){
        String bgStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/bg.css")).toExternalForm();
        String btnStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/button.css")).toExternalForm();
        String aboutStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/about.css")).toExternalForm();

        getStylesheets().add(bgStyle);
        getStylesheets().add(btnStyle);
        getStylesheets().add(aboutStyle);
    }

    private void configureLabel(){
        aboutLabel.setPrefWidth(1000);
        aboutLabel.setPrefHeight(500);
        aboutLabel.getStyleClass().add("label");

        aboutLabel.setText("""
                                This game allows you to dive into the underwater world
                                    and experience the life of a dangerous shark.
                You will have to survive in this harsh world among thousands of predators
                
                The shark is controlled by the cursor. You can eat fish with a blue outline.
                        Each fish you eat gives you a point and makes your shark bigger.
                   The bigger your shark is, the more blue-rimmed fish you will encounter.
                                            Beware of jellyfish, you can't eat them!
                """);
    }

    private void configureBackBtn() {
        backBtn.setPrefWidth(130);
        backBtn.setPrefHeight(80);
        backBtn.setText("Menu");
    }

    public @NotNull Button getBackBtn(){
        return backBtn;
    }
}
