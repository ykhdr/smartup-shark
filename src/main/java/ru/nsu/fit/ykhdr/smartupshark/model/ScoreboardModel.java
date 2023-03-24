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

public class ScoreboardModel {

    public @NotNull ObservableList<ScoreData> getScoreDataObservableList() {
        ObservableList<ScoreData> scoreDataList = FXCollections.observableArrayList();

        try (Reader in = new BufferedReader(new FileReader("src/main/resources/ru/nsu/fit/ykhdr/smartupshark/data/scores.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                try {
                    scoreDataList.add(getScoreDataFromRecord(record));
                } catch (NumberFormatException ignored) {
                }
            }

            scoreDataList.add(new ScoreData("2023-03-21", 10));
            scoreDataList.add(new ScoreData("2023-03-20", 20));
            scoreDataList.add(new ScoreData("2023-03-19", 30));


            return scoreDataList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull ScoreData getScoreDataFromRecord(@NotNull CSVRecord record) {
        return new ScoreData(record.get(0), Integer.parseInt(record.get(1)));
    }
}
