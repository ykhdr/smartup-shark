package ru.nsu.fit.ykhdr.smartupshark.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.ScoreFileHandler;
import ru.nsu.fit.ykhdr.smartupshark.view.ScoreboardView;

public class ScoreboardController implements Controller{

    private final @NotNull ScoreboardView view;
    private final @NotNull SceneManager sceneManager;


    public ScoreboardController(@NotNull SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.view = new ScoreboardView();

        setupBackBtn();
        setupScoreboardTable();
    }

    private void setupBackBtn(){
        view.getBackBtn().setOnAction(event -> sceneManager.setMenuScene());
    }

    private void setupScoreboardTable(){
        // CR: move to view
        view.getDateColumn().setCellValueFactory(data -> convertStringToObservable(data.getValue().date()));
        view.getScoreColumn().setCellValueFactory(data -> convertIntegerToObservable(data.getValue().score()));
        view.getScoreColumn().setSortType(TableColumn.SortType.DESCENDING);
        view.getScoreColumn().setComparator(Integer::compareTo);
        view.getScoreColumn().setSortable(true);
        view.getScoreboardTable().getSortOrder().add(view.getScoreColumn());
    }

    private @NotNull ObservableValue<String> convertStringToObservable(@NotNull String string){
        return new SimpleStringProperty(string);
    }

    private @NotNull ObservableValue<Integer> convertIntegerToObservable(@NotNull Integer integer){
        return new SimpleIntegerProperty(integer).asObject();
    }

    private void resetScoreboardItems(){
        view.getScoreboardTable().getItems().clear();
        // CR: separate method to add data
        view.getScoreboardTable().getItems().addAll(ScoreFileHandler.getScoreDataList());
        view.getScoreboardTable().sort();

    }

    @Override
    public @NotNull Scene getScene() {
        resetScoreboardItems();
        return view.getScene() == null ? new Scene(view) : view.getScene();
    }
}
