package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Enemy;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Player;
import ru.nsu.fit.ykhdr.smartupshark.sprite.Sprite;
import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.FatFish;
import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.LongFish;
import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.MidFish;
import ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy.SmallFish;
import ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy.Jellyfish;

import java.util.Objects;

public class GameView extends AnchorPane {

    public static class GameField extends Pane {
        private final @NotNull Label curScoreLabel = new Label();

        public GameField() {
            setPrefHeight(720);
            setPrefWidth(1024);

            configureCurScoreLabel();
            this.getChildren().add(curScoreLabel);
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
            curScoreLabel.setText("A");
        }

        public void addSprite(@NotNull Sprite sprite) {
            getChildren().add(sprite);
            sprite.setVisible(true);
        }

        public void removeSprite(@NotNull Sprite sprite) {
            getChildren().remove(sprite);
        }

        public void updatePlayerStyle(@NotNull Player player, boolean leftDirection) {
            player.setId(leftDirection ? "player-left" : "player-right");
        }

        public void updateNonEatableEnemyView(@NotNull Enemy enemy, boolean leftDirection) {
            switch (enemy) {
                case FatFish fatFish -> fatFish.setId(leftDirection ? "fat-fish-left-red" : "fat-fish-right-red");
                case LongFish longFish -> longFish.setId(leftDirection ? "long-fish-left-red" : "long-fish-right-red");
                case MidFish midFish -> midFish.setId(leftDirection ? "mid-fish-left-red" : "mid-fish-right-red");
                case SmallFish smallFish -> smallFish.setId(leftDirection ? "small-fish-left-red" : "small-fish-right-red");
                case Jellyfish jellyfish -> jellyfish.setId("jellyfish");
                default -> {
                } //skip
            }
        }

        public void updateEatableEnemyView(@NotNull Enemy enemy, boolean leftDirection) {
            switch (enemy) {
                case FatFish fatFish -> fatFish.setId(leftDirection ? "fat-fish-left-blue" : "fat-fish-right-blue");
                case LongFish longFish -> longFish.setId(leftDirection ? "long-fish-left-blue" : "long-fish-right-blue");
                case MidFish midFish -> midFish.setId(leftDirection ? "mid-fish-left-blue" : "mid-fish-right-blue");
                case SmallFish smallFish -> smallFish.setId(leftDirection ? "small-fish-left-blue" : "small-fish-right-blue");
                default -> {
                } //skip
            }
        }

        public @NotNull Label getCurScoreLabel() {
            return curScoreLabel;
        }
    }

    public static class StartBox extends VBox {
        private final @NotNull Button startBtn = new Button();

        public StartBox() {
            setAlignment(Pos.CENTER);
            setLayoutX(414);
            setLayoutY(362);
            setPrefHeight(135);
            setPrefWidth(200);

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

           // startBtn.setFont(Font.font(25));
        }

        public @NotNull Button getStartBtn() {
            return startBtn;
        }
    }

    public static class EndBox extends VBox {
        private final @NotNull Label endLabel = new Label();
        private final @NotNull Label scoreLabel = new Label();
        private final @NotNull Region offset = new Region();
        private final @NotNull Button newGameBtn = new Button();
        private final @NotNull Button backToMenuBtn = new Button();

        public EndBox() {
            setAlignment(Pos.CENTER);
            setPrefHeight(720);
            setPrefWidth(1024);
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

        public @NotNull Button getBtnNewGame() {
            return newGameBtn;
        }

        public @NotNull Button getBtnBackToMenu() {
            return backToMenuBtn;
        }

        public @NotNull Label getScoreLabel() {
            return scoreLabel;
        }
    }

    private final @NotNull GameField gameField;
    private final @NotNull StartBox startBox;
    private final @NotNull EndBox endBox;

    public GameView() {
        gameField = new GameField();
        startBox = new StartBox();
        endBox = new EndBox();
        configureView();

        addStylesheets();

        startBox.setVisible(true);
        endBox.setVisible(false);
        gameField.setVisible(false);
    }


    private void configureView() {
        setPrefHeight(720);
        setPrefWidth(1024);

        getStyleClass().add("bg");

        getChildren().add(startBox);
        getChildren().add(gameField);
        getChildren().add(endBox);
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
        gameField.setVisible(true);

        startBox.setVisible(false);
        endBox.setVisible(false);
    }

    public void endGame() {
        endBox.setVisible(true);

        gameField.setVisible(false);
        startBox.setVisible(false);
    }

    public void reset() {
        startBox.setVisible(true);

        gameField.setVisible(false);
        endBox.setVisible(false);
    }

    public @NotNull StartBox getStartBox() {
        return startBox;
    }

    public @NotNull EndBox getEndBox() {
        return endBox;
    }

    public @NotNull GameField getGameField() {
        return gameField;
    }
}
