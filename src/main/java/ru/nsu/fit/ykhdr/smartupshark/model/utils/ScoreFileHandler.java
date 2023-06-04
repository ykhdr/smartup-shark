package ru.nsu.fit.ykhdr.smartupshark.model.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.score.ScoreData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ScoreFileHandler {

    private static final @NotNull Path CSV_PATH = Path.of("src/main/resources/data/scores.csv");
    private static final Comparator<ScoreData> COMPARATOR = Comparator.comparingInt(ScoreData::score);
    private static final @NotNull ScoreFileHandler INSTANCE = new ScoreFileHandler();
    private static final int SCORE_LIMIT = 20;
    private final List<ScoreData> scoreDataList = new ArrayList<>();

    private ScoreFileHandler() {
        loadScoreDataFromCsv();
    }

    public static @NotNull ScoreFileHandler getInstance() {
        return INSTANCE;
    }

    public @NotNull List<ScoreData> getScoreDataList() {
        return scoreDataList;
    }

    private void loadScoreDataFromCsv() {
        // CR: implement the same logic as in writeScoreToFile
        checkFileExists();

        try (BufferedReader in = Files.newBufferedReader(CSV_PATH, StandardCharsets.UTF_8)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                addRecordToScoreDataList(record);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeScoreToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(CSV_PATH, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (ScoreData scoreData : scoreDataList) {
                writer.write(scoreData.date() + "," + scoreData.score() + "\n");
            }
        } catch (IOException e) {
            // CR: log?
            throw new RuntimeException(e);
        }
    }

    private void checkFileExists() {
        if (!Files.exists(CSV_PATH)) {
            createFile();
        }
    }

    public void cacheNewScore(int score) {
        if (scoreDataList.size() < SCORE_LIMIT) {
            scoreDataList.add(createScoreDataFromScore(score));
        } else if (score > scoreDataList.get(scoreDataList.size() - 1).score()) {
            updateScoreDataList(createScoreDataFromScore(score));
        }
    }

    private void updateScoreDataList(@NotNull ScoreData newScoreData) {
        // CR(minor): can use tree set instead
        scoreDataList.stream()
                .min(COMPARATOR)
                .ifPresent(scoreDataList::remove);

        scoreDataList.add(newScoreData);
        scoreDataList.sort(COMPARATOR.reversed());
    }

    private void addRecordToScoreDataList(@NotNull CSVRecord record) {
        try {
            scoreDataList.add(createScoreDataFromRecord(record));
        } catch (NumberFormatException ignored) {
            // CR: log
        }
    }

    private @NotNull ScoreData createScoreDataFromRecord(@NotNull CSVRecord record) {
        // CR: we can also get an npe here
        return new ScoreData(record.get(0), Integer.parseInt(record.get(1)));
    }

    private @NotNull ScoreData createScoreDataFromScore(int score) {
        // CR: better to move DateTimeFormatter.ofPattern(...) to field and not recreate
        return new ScoreData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")), score);
    }

    private void createFile() {
        try {
            Files.createFile(CSV_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}