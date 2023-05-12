package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MenuView extends VBox implements View {
    private final @NotNull Label welcomeLabel = new Label();
    private final @NotNull Button newGameBtn = new Button();
    private final @NotNull Button scoreboardBtn = new Button();
    private final @NotNull Button aboutBtn = new Button();
    private final @NotNull Button exitBtn = new Button();

    private final @NotNull Region upperMargin = new Region();
    private final @NotNull Region lowerMargin = new Region();

    @Override
    public void setup() {
        setPrefWidth(1024);
        setPrefHeight(720);
        setAlignment(Pos.CENTER);
        setSpacing(20);

        configureViewComponents();
        addStylesheets();

        getStyleClass().add("bg");
        getChildren().add(welcomeLabel);
        getChildren().add(upperMargin);
        getChildren().add(newGameBtn);
        getChildren().add(scoreboardBtn);
        getChildren().add(aboutBtn);
        getChildren().add(exitBtn);
        getChildren().add(lowerMargin);
    }

    private void configureViewComponents() {
        configureWelcomeLabel();
        configureNewGameBtn();
        configureScoreboardBtn();
        configureAboutBtn();
        configureExitBtn();
        configureUpperMargin();
        configureLowerMargin();
    }

    private void addStylesheets() {
        String bgStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/bg.css")).toExternalForm();
        String btnStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/button.css")).toExternalForm();
        String menuStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/menu.css")).toExternalForm();

        getStylesheets().add(bgStyle);
        getStylesheets().add(btnStyle);
        getStylesheets().add(menuStyle);
    }


    private void configureWelcomeLabel() {
        welcomeLabel.setPrefHeight(145);
        welcomeLabel.setText("Smartup Shark");
        welcomeLabel.setLayoutY(50);
        welcomeLabel.getStyleClass().add("label");
    }

    private void configureNewGameBtn() {
        newGameBtn.setPrefHeight(92);
        newGameBtn.setPrefWidth(132);
        newGameBtn.setText("New Game");
    }

    private void configureScoreboardBtn() {
        scoreboardBtn.setPrefHeight(92);
        scoreboardBtn.setPrefWidth(132);
        scoreboardBtn.setText("Scoreboard");
    }

    private void configureAboutBtn() {
        aboutBtn.setPrefHeight(92);
        aboutBtn.setPrefWidth(132);
        aboutBtn.setText("About");
    }

    private void configureExitBtn() {
        exitBtn.setAlignment(Pos.CENTER);
        exitBtn.setPrefHeight(92);
        exitBtn.setPrefWidth(132);
        exitBtn.setText("Exit");
    }

    private void configureUpperMargin() {
        upperMargin.setPrefHeight(95);
    }

    private void configureLowerMargin() {
        lowerMargin.setPrefHeight(125);
    }

    public void setActionOnBtnNewGame(@NotNull EventHandler<ActionEvent> action) {
        newGameBtn.setOnAction(action);
    }

    public void setActionOnBtnScoreboard(@NotNull EventHandler<ActionEvent> action) {
        scoreboardBtn.setOnAction(action);
    }

    public void setActionOnBtnAbout(@NotNull EventHandler<ActionEvent> action) {
        aboutBtn.setOnAction(action);
    }

    public void setActionOnBtnExit(@NotNull EventHandler<ActionEvent> action) {
        exitBtn.setOnAction(action);
    }
}
