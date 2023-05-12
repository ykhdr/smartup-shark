package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.*;
import ru.nsu.fit.ykhdr.smartupshark.view.sprites.Sprite;

import java.util.List;
import java.util.Objects;

public class GameView extends StackPane implements View {
    private final @NotNull GameField gameField = new GameField();
    private final @NotNull StartBox startBox = new StartBox();
    private final @NotNull EndBox endBox = new EndBox();
    @Override
    public void setup() {
        gameField.setup();
        startBox.setup();
        endBox.setup();

        addStylesheets();

        getStyleClass().add("bg");
        getChildren().add(gameField);
        getChildren().add(startBox);
    }

    private void addStylesheets() {
        String bgStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/bg.css")).toExternalForm();
        String btnStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/button.css")).toExternalForm();
        String spriteStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/model.css")).toExternalForm();
        String gameStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/game.css")).toExternalForm();

        getStylesheets().add(bgStyle);
        getStylesheets().add(btnStyle);
        getStylesheets().add(spriteStyle);
        getStylesheets().add(gameStyle);
    }

    public void startGame() {
        getChildren().remove(startBox);
        gameField.showScore();
    }

    public void endGame(int score) {
        endBox.setScore(score);

        gameField.hideScore();
        getChildren().add(endBox);
    }

    public void reset() {
        getChildren().remove(endBox);
        getChildren().remove(startBox);
        getChildren().remove(gameField);

        getChildren().add(gameField);
        getChildren().add(startBox);

        gameField.clear();
    }

    public void repaint(@NotNull GameObjects gameObjects) {
        gameField.clear();
        gameField.repaintScore(gameObjects.score());

        repaintFishes(gameObjects.enemies());
        repaintPlayer(gameObjects.player());
    }

    private void repaintFishes(@NotNull List<FishObject> enemies) {
        for (FishObject fishObject : enemies) {
            Position position = fishObject.position();
            Size size = fishObject.size();

            Sprite sprite = new Sprite(position.x(), position.y(), size.width(), size.height());

            gameField.addSprite(sprite);
            gameField.updateFishSprite(sprite, fishObject);
        }
    }

    private void repaintPlayer(@NotNull PlayerObject playerObject) {
        Position position = playerObject.position();
        Size size = playerObject.size();

        Sprite sprite = new Sprite(position.x(), position.y(), size.width(), size.height());

        gameField.addSprite(sprite);
        gameField.updatePlayerSprite(sprite, playerObject);
    }

    public void setActionOnBtnStart(@NotNull EventHandler<ActionEvent> action) {
        startBox.setActionOnBtnStart(action);
    }

    public void setActionOnBtnBackToMenu(@NotNull EventHandler<ActionEvent> action) {
        endBox.setActionOnBtnBackToMenu(action);
    }

    public void setActionOnBtnNewGame(@NotNull EventHandler<ActionEvent> action) {
        endBox.setActionOnBtnNewGame(action);
    }
}

class GameField extends Pane implements View {
    private final @NotNull Label curScoreLabel = new Label();

    @Override
    public void setup() {
        setPrefWidth(1024);
        setPrefHeight(720);

        configureCurScoreLabel();

        getChildren().add(curScoreLabel);
    }

    private void configureCurScoreLabel() {
        curScoreLabel.setAlignment(Pos.CENTER);
        curScoreLabel.setTextAlignment(TextAlignment.CENTER);
        curScoreLabel.setTranslateX(500);
        curScoreLabel.setTranslateY(500);

        curScoreLabel.setPrefHeight(40);
        curScoreLabel.setPrefWidth(500);
        curScoreLabel.setLayoutX(-250);
        curScoreLabel.setLayoutY(-500);
        curScoreLabel.setText("");
    }

    public void addSprite(@NotNull Sprite sprite) {
        getChildren().add(sprite);
    }

    public void updatePlayerSprite(@NotNull Sprite player, @NotNull PlayerObject playerObject) {
        player.setX(playerObject.position().x());
        player.setY(playerObject.position().y());

        player.setHeight(playerObject.size().height());
        player.setWidth(playerObject.size().width());

        updatePlayerStyle(player, playerObject);
    }

    private void updatePlayerStyle(@NotNull Sprite player, @NotNull PlayerObject playerObject) {
        player.setId(playerObject.direction() == Direction.LEFT ? "player-left" : "player-right");
    }

    private void updateNonEatableFishStyle(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        Direction direction = fishObject.direction();

        sprite.setId(
                switch (fishObject.type()) {
                    case FAT -> direction == Direction.LEFT ? "fat-fish-left-red" : "fat-fish-right-red";
                    case LONG -> direction == Direction.LEFT ? "long-fish-left-red" : "long-fish-right-red";
                    case MID -> direction == Direction.LEFT ? "mid-fish-left-red" : "mid-fish-right-red";
                    case SMALL -> direction == Direction.LEFT ? "small-fish-left-red" : "small-fish-right-red";
                    case JELLY -> "jellyfish";
                }
        );

    }

    private void updateEatableFishStyle(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        Direction direction = fishObject.direction();

        sprite.setId(
                switch (fishObject.type()) {
                    case FAT -> direction == Direction.LEFT ? "fat-fish-left-blue" : "fat-fish-right-blue";
                    case LONG -> direction == Direction.LEFT ? "long-fish-left-blue" : "long-fish-right-blue";
                    case MID -> direction == Direction.LEFT ? "mid-fish-left-blue" : "mid-fish-right-blue";
                    case SMALL -> direction == Direction.LEFT ? "small-fish-left-blue" : "small-fish-right-blue";
                    case JELLY -> "jellyfish";
                });
    }

    public void updateFishSprite(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        sprite.setX(fishObject.position().x());
        sprite.setY(fishObject.position().y());

        if (fishObject.isEatable()) {
            updateEatableFishStyle(sprite, fishObject);
        } else {
            updateNonEatableFishStyle(sprite, fishObject);
        }
    }

    public void repaintScore(int score) {
        curScoreLabel.setText("Score: " + score);
    }

    public void hideScore() {
        curScoreLabel.setVisible(false);
    }

    public void showScore() {
        curScoreLabel.setVisible(true);
    }

    public void clear() {
        getChildren().removeIf(s -> s instanceof Sprite);
    }
}

class StartBox extends VBox implements View {
    private final @NotNull Button startBtn = new Button();

    @Override
    public void setup() {
        setAlignment(Pos.CENTER);
        setPrefWidth(1024);
        setPrefHeight(720);

        configureStartBtn();

        getChildren().add(startBtn);
    }

    private void configureStartBtn() {
        startBtn.setAlignment(Pos.CENTER);
        startBtn.setTextAlignment(TextAlignment.CENTER);
        startBtn.setPrefHeight(109);
        startBtn.setPrefWidth(180);
        startBtn.setContentDisplay(ContentDisplay.CENTER);
        startBtn.setText("Start");
    }

    public void setActionOnBtnStart(@NotNull EventHandler<ActionEvent> action) {
        startBtn.setOnAction(action);
    }
}

class EndBox extends VBox implements View {
    private final @NotNull Label endLabel = new Label();
    private final @NotNull Label scoreLabel = new Label();
    private final @NotNull Region offset = new Region();
    private final @NotNull Button newGameBtn = new Button();
    private final @NotNull Button backToMenuBtn = new Button();

    @Override
    public void setup() {
        setAlignment(Pos.CENTER);
        setPrefWidth(1024);
        setPrefHeight(720);
        setSpacing(20);

        configureEndLabel();
        configureScoreLabel();
        configureOffset();
        configureNewGameBtn();
        configureBackToMenuBtn();

        getChildren().add(endLabel);
        getChildren().add(scoreLabel);
        getChildren().add(offset);
        getChildren().add(newGameBtn);
        getChildren().add(backToMenuBtn);
    }

    private void configureEndLabel() {
        endLabel.setAlignment(Pos.CENTER);
        endLabel.setTextAlignment(TextAlignment.CENTER);
        endLabel.setLayoutX(390);
        endLabel.setLayoutY(57);
        endLabel.setPrefWidth(350);
        endLabel.setText("Game Over!");
    }

    private void configureScoreLabel() {
        scoreLabel.setAlignment(Pos.CENTER);
        scoreLabel.setLayoutX(374);
        scoreLabel.setLayoutY(167);
        scoreLabel.setPrefHeight(80);
        scoreLabel.setPrefWidth(278);
        scoreLabel.setText("Score");
    }

    private void configureOffset() {
        offset.setPrefHeight(170);
        offset.setPrefWidth(554);
    }

    private void configureNewGameBtn() {
        newGameBtn.setLayoutX(457);
        newGameBtn.setLayoutY(292);
        newGameBtn.setPrefWidth(126);
        newGameBtn.setPrefHeight(90);
        newGameBtn.setText("New Game");
    }

    private void configureBackToMenuBtn() {
        backToMenuBtn.setLayoutX(457);
        backToMenuBtn.setLayoutY(292);
        backToMenuBtn.setPrefWidth(126);
        backToMenuBtn.setPrefHeight(90);
        backToMenuBtn.setText("Menu");
    }

    public void setActionOnBtnBackToMenu(@NotNull EventHandler<ActionEvent> action) {
        backToMenuBtn.setOnAction(action);
    }

    public void setActionOnBtnNewGame(@NotNull EventHandler<ActionEvent> action) {
        newGameBtn.setOnAction(action);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score : " + score);
    }
}