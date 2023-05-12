package ru.nsu.fit.ykhdr.smartupshark.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreData;

import java.util.List;
import java.util.Objects;

public class ScoreboardView extends AnchorPane implements View {
    private final @NotNull TableView<ScoreData> scoreboardTable = new TableView<>();
    private final @NotNull TableColumn<ScoreData, String> dateColumn = new TableColumn<>();
    private final @NotNull TableColumn<ScoreData, Integer> scoreColumn = new TableColumn<>();
    private final @NotNull Button backBtn = new Button();

    @Override
    public void setup() {
        setPrefWidth(1024);
        setPrefHeight(720);

        configureViewComponents();

        addStyleSheets();

        getStyleClass().add("bg");
        getChildren().add(scoreboardTable);
        getChildren().add(backBtn);
    }

    private void configureViewComponents() {
        configureScoreboard();
        configureDateColumn();
        configureScoreColumn();
        configureBackBtn();
    }

    private void configureScoreboard() {
        scoreboardTable.setLayoutX(162);
        scoreboardTable.setLayoutY(110);
        scoreboardTable.setPrefHeight(500);
        scoreboardTable.setPrefWidth(700);
        scoreboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        scoreboardTable.getColumns().add(dateColumn);
        scoreboardTable.getColumns().add(scoreColumn);

        scoreboardTable.getSortOrder().add(scoreColumn);
    }

    private void configureDateColumn() {
        dateColumn.setPrefWidth(350);
        dateColumn.setText("Date");

        dateColumn.setCellValueFactory(data -> convertStringToObservable(data.getValue().date()));

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

        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        scoreColumn.setComparator(Integer::compareTo);
        scoreColumn.setSortable(true);

        scoreColumn.setCellValueFactory(data -> convertIntegerToObservable(data.getValue().score()));

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

    private @NotNull ObservableValue<String> convertStringToObservable(@NotNull String string){
        return new SimpleStringProperty(string);
    }

    private @NotNull ObservableValue<Integer> convertIntegerToObservable(@NotNull Integer integer){
        return new SimpleIntegerProperty(integer).asObject();
    }

    public void setActionOnBtnBackToMenu(@NotNull EventHandler<ActionEvent> action) {
        backBtn.setOnAction(action);
    }

    public void setItems(@NotNull List<ScoreData> scoreDataList){
        scoreboardTable.getItems().clear();
        scoreboardTable.getItems().addAll(scoreDataList);
        scoreboardTable.sort();
    }
}
