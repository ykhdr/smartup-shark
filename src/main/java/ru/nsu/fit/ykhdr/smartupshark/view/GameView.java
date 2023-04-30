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
import ru.nsu.fit.ykhdr.smartupshark.model.SceneSize;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.GameObject;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.fish.*;
import ru.nsu.fit.ykhdr.smartupshark.view.sprites.Sprite;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GameView extends StackPane implements View {
    private final @NotNull GameField gameField = new GameField();
    private final @NotNull StartBox startBox = new StartBox();
    private final @NotNull EndBox endBox = new EndBox();
    private @NotNull GameObjects gameObjects = new GameObjects();
    private final @NotNull Set<Sprite> sprites = new HashSet<>();

    @Override
    public void setup(@NotNull SceneSize size) {
        gameField.setup(size);
        startBox.setup(size);
        endBox.setup(size);

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
        sprites.clear();
        gameObjects.clear();

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

        sprites.clear();
        gameObjects.clear();
        gameField.clear();
    }

    public void repaint() {
        gameField.repaintScore();

        for (GameObject gameObject : gameObjects) {

            Sprite sprite = sprites
                    .stream()
                    .filter(s -> s.getSpriteId() == gameObject.getId())
                    .findFirst()
                    .orElse(new Sprite(gameObject.getSize().getWidth(), gameObject.getSize().getHeight(), gameObject.getId()));

            if (gameObject.isDead()) {
                sprites.remove(sprite);
                gameField.removeSprite(sprite);
                return;
            }

            if (sprites.add(sprite)) {
                gameField.addSprite(sprite);
            }

            switch (gameObject) {
                case PlayerObject playerObject -> gameField.updatePlayerSprite(sprite, playerObject);
                case FishObject fishObject -> gameField.updateFishSprite(sprite, fishObject);

                default -> {// ignored
                }
            }

        }
    }

    public void update(@NotNull GameObjects gameObjects, int score) {
        this.gameObjects = gameObjects;

        gameField.updateScore(score);
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

    private int score;

    @Override
    public void setup(@NotNull SceneSize size) {
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

    public void removeSprite(@NotNull Sprite sprite) {
        getChildren().remove(sprite);
    }

    public void updatePlayerSprite(@NotNull Sprite player, @NotNull PlayerObject playerObject) {
        player.setLayoutX(playerObject.getCoordinates().getX());
        player.setLayoutY(playerObject.getCoordinates().getY());

        player.setHeight(playerObject.getSize().getHeight());
        player.setWidth(playerObject.getSize().getWidth());

        updatePlayerStyle(player, playerObject);
    }

    public void updatePlayerStyle(@NotNull Sprite player, @NotNull PlayerObject playerObject) {
        player.setId(playerObject.isLeftDirection() ? "player-left" : "player-right");
    }

    public void updateNonEatableFishStyle(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        Direction direction = fishObject.getDirection();

        sprite.setId(
                switch (fishObject) {
                    case FatFishObject ignored ->
                            direction == Direction.LEFT ? "fat-fish-left-red" : "fat-fish-right-red";
                    case LongFishObject ignored ->
                            direction == Direction.LEFT ? "long-fish-left-red" : "long-fish-right-red";
                    case MidFishObject ignored ->
                            direction == Direction.LEFT ? "mid-fish-left-red" : "mid-fish-right-red";
                    case SmallFishObject ignored ->
                            direction == Direction.LEFT ? "small-fish-left-red" : "small-fish-right-red";
                    case JellyfishObject ignored -> "jellyfish";
                }
        );

    }

    public void updateEatableFishStyle(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        Direction direction = fishObject.getDirection();

        sprite.setId(
                switch (fishObject) {
                    case FatFishObject ignored ->
                            direction == Direction.LEFT ? "fat-fish-left-blue" : "fat-fish-right-blue";
                    case LongFishObject ignored ->
                            direction == Direction.LEFT ? "long-fish-left-blue" : "long-fish-right-blue";
                    case MidFishObject ignored ->
                            direction == Direction.LEFT ? "mid-fish-left-blue" : "mid-fish-right-blue";
                    case SmallFishObject ignored ->
                            direction == Direction.LEFT ? "small-fish-left-blue" : "small-fish-right-blue";
                    case JellyfishObject ignored -> "jellyfish";
                });
    }

    public void updateFishSprite(@NotNull Sprite sprite, @NotNull FishObject fishObject) {
        sprite.setLayoutX(fishObject.getCoordinates().getX());
        sprite.setLayoutY(fishObject.getCoordinates().getY());

        if (fishObject.isEatable()) {
            updateEatableFishStyle(sprite, fishObject);
        } else {
            updateNonEatableFishStyle(sprite, fishObject);
        }
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public void repaintScore() {
        curScoreLabel.setText("Score: " + score);
    }

    public void hideScore() {
        curScoreLabel.setVisible(false);
    }

    public void showScore() {
        curScoreLabel.setVisible(true);
    }

    public void clear(){
        getChildren().removeIf(s -> s instanceof Sprite);
    }
}

class StartBox extends VBox implements View {
    private final @NotNull Button startBtn = new Button();

    @Override
    public void setup(@NotNull SceneSize size) {
        setAlignment(Pos.CENTER);
        setPrefWidth(size.width());
        setPrefHeight(size.height());

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
    public void setup(@NotNull SceneSize size) {
        setAlignment(Pos.CENTER);
        setPrefWidth(size.width());
        setPrefHeight(size.height());
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
        scoreLabel.setText("Score : "+ score);
    }
}