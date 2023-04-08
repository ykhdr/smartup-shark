package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreData;

import java.util.Objects;

public class ScoreboardView extends AnchorPane {
    private final TableView<ScoreData> scoreboardTable = new TableView<>();
    private final TableColumn<ScoreData, String> dateColumn = new TableColumn<>();
    private final TableColumn<ScoreData, Integer> scoreColumn = new TableColumn<>();
    private final Button backBtn = new Button();

    public ScoreboardView() {
        configureView();
        configureScoreboard();
        configureDateColumn();
        configureScoreColumn();
        configureBackBtn();

        addStyleSheets();
    }

    private void configureView() {
        setPrefHeight(720);
        setPrefWidth(1024);

        getStyleClass().add("bg");

        getChildren().add(scoreboardTable);
        getChildren().add(backBtn);
    }

    private void configureScoreboard() {
        scoreboardTable.setLayoutX(162);
        scoreboardTable.setLayoutY(110);
        scoreboardTable.setPrefHeight(500);
        scoreboardTable.setPrefWidth(700);
        scoreboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        scoreboardTable.getColumns().add(dateColumn);
        scoreboardTable.getColumns().add(scoreColumn);
    }

    private void configureDateColumn() {
        dateColumn.setPrefWidth(350);
        dateColumn.setText("Date");

        dateColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);

                }
            }
        });
    }

    private void configureScoreColumn() {
        scoreColumn.setPrefWidth(350);
        scoreColumn.setText("Score");
        scoreColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }

    private void configureBackBtn() {
        backBtn.setAlignment(Pos.CENTER);
        backBtn.setLayoutX(870);
        backBtn.setLayoutY(14);
        backBtn.setPrefWidth(130);
        backBtn.setPrefHeight(80);
        backBtn.setText("Menu");
    }


    private void addStyleSheets() {
        String bgStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/bg.css")).toExternalForm();
        String btnStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/button.css")).toExternalForm();
        String scoreboardStyle = Objects.requireNonNull(getClass().getResource("/stylesheets/scoreboard.css")).toExternalForm();

        getStylesheets().add(bgStyle);
        getStylesheets().add(btnStyle);
        getStylesheets().add(scoreboardStyle);
    }

    public @NotNull Button getBackBtn() {
        return backBtn;
    }

    public @NotNull TableView<ScoreData> getScoreboardTable() {
        return scoreboardTable;
    }

    public @NotNull TableColumn<ScoreData, String> getDateColumn() {
        return dateColumn;
    }

    public @NotNull TableColumn<ScoreData, Integer> getScoreColumn() {
        return scoreColumn;
    }
}
