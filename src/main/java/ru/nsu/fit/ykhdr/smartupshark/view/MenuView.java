package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MenuView extends VBox {
    private final Label welcomeLabel = new Label();
    private final Button newGameBtn = new Button();
    private final Button scoreboardBtn = new Button();
    private final Button exitBtn = new Button();

    private final Region upperRegion = new Region();
    private final Region lowerRegion = new Region();

    public MenuView() {

        configureView();
        configureWelcomeLabel();
        configureNewGameBtn();
        configureScoreboardBtn();
        configureExitBtn();
        configureUpperRegion();
        configureLowerRegion();

        addStyleSheetsToView();
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
        getChildren().add(exitBtn);
        getChildren().add(lowerRegion);
    }

    private void addStyleSheetsToView() {
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
        welcomeLabel.getStyleClass().add("outline");
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

    private void configureExitBtn() {
        exitBtn.setAlignment(Pos.CENTER);
        exitBtn.setPrefHeight(92);
        exitBtn.setPrefWidth(132);
        exitBtn.setText("Exit");
        exitBtn.getStyleClass().add("button");
        exitBtn.getStyleClass().add("outline");
    }

    private void configureUpperRegion() {
        upperRegion.setPrefHeight(95);
    }

    private void configureLowerRegion() {
        lowerRegion.setPrefHeight(197);
    }

    public @NotNull Button getNewGameBtn(){
        return newGameBtn;
    }

    public @NotNull Button getScoreboardBtn() {
        return scoreboardBtn;
    }

    public @NotNull Button getExitBtn() {
        return exitBtn;
    }
}
