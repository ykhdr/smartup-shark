package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreboardModel;
import ru.nsu.fit.ykhdr.smartupshark.view.ScoreboardView;

public class ScoreboardController {

    private final @NotNull ScoreboardView view;
    private final @NotNull ScoreboardModel model;

    public ScoreboardController(@NotNull Runnable backToMenuScene) {
        this.model = new ScoreboardModel();
        this.view = new ScoreboardView();

        setupBackBtn(backToMenuScene);
        setupScoreboardTable();
    }

    private void setupBackBtn(@NotNull Runnable backToMenuScene){
        view.getBackBtn().setOnAction(event -> backToMenuScene.run());
    }

    private void setupScoreboardTable(){
        view.getDateColumn().setCellValueFactory(data -> data.getValue().date());
        view.getScoreColumn().setCellValueFactory(data -> data.getValue().score().asObject());
        view.getScoreColumn().setSortType(TableColumn.SortType.DESCENDING);
        view.getScoreColumn().setComparator(Integer::compareTo);
        view.getScoreColumn().setSortable(true);
        view.getScoreboardTable().getSortOrder().add(view.getScoreColumn());
    }

    private void resetScoreboardItems(){
        view.getScoreboardTable().getItems().clear();
        view.getScoreboardTable().getItems().addAll(model.loadScoreDataFromCsv());
        view.getScoreboardTable().sort();
    }

    public @NotNull Scene getScene() {
        resetScoreboardItems();
        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
