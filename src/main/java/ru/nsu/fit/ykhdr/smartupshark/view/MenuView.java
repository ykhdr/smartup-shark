package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MenuView extends VBox {
    private final @NotNull Label welcomeLabel = new Label();
    private final @NotNull Button newGameBtn = new Button();
    private final @NotNull Button scoreboardBtn = new Button();
    private final @NotNull Button aboutBtn = new Button();
    private final @NotNull Button exitBtn = new Button();

    private final @NotNull Region upperRegion = new Region();
    private final @NotNull Region lowerRegion = new Region();

    public MenuView() {
        configureView();
        configureWelcomeLabel();
        configureNewGameBtn();
        configureScoreboardBtn();
        configureAboutBtn();
        configureExitBtn();
        configureUpperRegion();
        configureLowerRegion();

        addStylesheets();
    }

    private void configureView() {
        setAlignment(Pos.CENTER);
        setPrefHeight(720);
        setPrefWidth(1024);
        setSpacing(20);

        getStyleClass().add("bg");

        getChildren().add(welcomeLabel);
        getChildren().add(upperRegion);
        getChildren().add(newGameBtn);
        getChildren().add(scoreboardBtn);
        getChildren().add(aboutBtn);
        getChildren().add(exitBtn);
        getChildren().add(lowerRegion);
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

    private void configureAboutBtn(){
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

    private void configureUpperRegion() {
        upperRegion.setPrefHeight(95);
    }

    private void configureLowerRegion() {
        lowerRegion.setPrefHeight(125);
    }

    public @NotNull Button getNewGameBtn(){
        return newGameBtn;
    }

    public @NotNull Button getScoreboardBtn() {
        return scoreboardBtn;
    }

    public @NotNull Button getAboutBtn() {
        return aboutBtn;
    }

    public @NotNull Button getExitBtn() {
        return exitBtn;
    }
}
