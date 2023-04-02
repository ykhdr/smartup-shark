package ru.nsu.fit.ykhdr.smartupshark.model;

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

    public @NotNull ObservableList<ScoreData> getScoreDataObservableList() {
        ObservableList<ScoreData> scoreDataList = FXCollections.observableArrayList();

        Path scoreboardPath = Path.of("src/main/resources/ru/nsu/fit/ykhdr/smartupshark/data/scores.csv");

        if(!Files.exists(scoreboardPath)){
            createFile(scoreboardPath);
        }

        try (Reader in = new BufferedReader(new FileReader(scoreboardPath.toString()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                try {
                    scoreDataList.add(getScoreDataFromRecord(record));
                } catch (NumberFormatException ignored) {
                }
            }

            return scoreDataList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull ScoreData getScoreDataFromRecord(@NotNull CSVRecord record) {
        return new ScoreData(record.get(0), Integer.parseInt(record.get(1)));
    }

    private void createFile(@NotNull Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
