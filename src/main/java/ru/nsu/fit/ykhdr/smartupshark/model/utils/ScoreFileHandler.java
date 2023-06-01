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


/*

20.02.2022,12
20.02.2022,10
20.02.2023,1

 */
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
        }
    }

    private @NotNull ScoreData createScoreDataFromRecord(@NotNull CSVRecord record) {
        return new ScoreData(record.get(0), Integer.parseInt(record.get(1)));
    }

    private @NotNull ScoreData createScoreDataFromScore(int score) {
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