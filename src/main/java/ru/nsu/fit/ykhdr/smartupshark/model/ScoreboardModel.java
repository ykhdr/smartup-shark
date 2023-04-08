package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScoreboardModel {
    private final Path CSV_PATH = Path.of("src/main/resources/data/scores.csv");
    private final ObservableList<ScoreData> scoreDataList = FXCollections.observableArrayList();

    public @NotNull ObservableList<ScoreData> loadScoreDataFromCsv() {
        scoreDataList.clear();
        if(!Files.exists(CSV_PATH)){
            createFile(CSV_PATH);
        }

        try (Reader in = new BufferedReader(new FileReader(CSV_PATH.toString()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                try {
                    scoreDataList.add(getScoreDataFromRecord(record));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scoreDataList;
    }

    private @NotNull ScoreData getScoreDataFromRecord(@NotNull CSVRecord record) {
        return new ScoreData(new SimpleStringProperty(record.get(0)), new SimpleIntegerProperty(Integer.parseInt(record.get(1))));
    }

    private void createFile(@NotNull Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
