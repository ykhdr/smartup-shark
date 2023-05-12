package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class AboutView extends VBox implements View {
    private final @NotNull Label aboutLabel = new Label();
    private final @NotNull Button backBtn = new Button();

    @Override
    public void setup() {
        setPrefHeight(720);
        setPrefWidth(1024);
        setAlignment(Pos.CENTER);

        configureViewComponents();
        addStylesheets();

        getStyleClass().add("bg");
        getChildren().add(aboutLabel);
        getChildren().add(backBtn);
    }

    private void configureViewComponents() {
        configureLabel();
        configureBackBtn();
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
        aboutLabel.setTextAlignment(TextAlignment.CENTER);
    }

    private void configureBackBtn() {
        backBtn.setPrefWidth(130);
        backBtn.setPrefHeight(80);
        backBtn.setText("Menu");
    }

    public void setActionOnBtnBackToMenu(@NotNull EventHandler<ActionEvent> action){
        backBtn.setOnAction(action);
    }

    public void setText(@NotNull String text){
        aboutLabel.setText(text);
    }
}
