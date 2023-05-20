package ru.nsu.fit.ykhdr.smartupshark.model.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.score.ScoreData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// CR: make singleton
// CR: store top-20
public class ScoreFileHandler {

    // CR(minor): maybe not csv?
    private static final Path CSV_PATH = Path.of("src/main/resources/data/scores.csv");

    private static final List<ScoreData> scoreDataList = new ArrayList<>();

    static {
        loadScoreDataFromCsv();
    }

    public static @NotNull List<ScoreData> getScoreDataList() {
        return scoreDataList;
    }

    public static void writeScore(int score) {
        scoreDataList.add(createScoreDataFromScore(score));

        // CR: move to separate method
        if (!Files.exists(CSV_PATH)) {
            createFile();
        }

        try (FileWriter writer = new FileWriter(CSV_PATH.toString(), true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + "," + score + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadScoreDataFromCsv() {
        if (!Files.exists(CSV_PATH)) {
            createFile();
        }

        try (Reader in = new BufferedReader(new FileReader(CSV_PATH.toString()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                addRecordToScoreDataList(record);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addRecordToScoreDataList(@NotNull CSVRecord record) {
        try {
            scoreDataList.add(createScoreDataFromRecord(record));
        } catch (NumberFormatException ignored) {
        }
    }

    private static @NotNull ScoreData createScoreDataFromRecord(@NotNull CSVRecord record) {
        return new ScoreData(record.get(0), Integer.parseInt(record.get(1)));
    }

    private static @NotNull ScoreData createScoreDataFromScore(int score) {
        return new ScoreData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")), score);
    }

    private static void createFile() {
        try {
            Files.createFile(ScoreFileHandler.CSV_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
