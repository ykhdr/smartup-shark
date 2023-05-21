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
import java.util.Comparator;
import java.util.List;

public class ScoreFileHandler {

    // CR(minor): maybe not csv?
    private static final @NotNull Path CSV_PATH = Path.of("src/main/resources/data/scores.csv");
    private static final @NotNull ScoreFileHandler INSTANCE = new ScoreFileHandler();

    private final List<ScoreData> scoreDataList;
    private int minScore = 0;

    private ScoreFileHandler() {
        scoreDataList = loadScoreDataFromCsv();
    }

    public static @NotNull ScoreFileHandler getInstance() {
        return INSTANCE;
    }

    public @NotNull List<ScoreData> getScoreDataList() {
        return scoreDataList;
    }

    public void writeScore(int score) {
        cacheNewScore(score);
        checkFileExist();

        try (FileWriter writer = new FileWriter(CSV_PATH.toString(), true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + "," + score + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull List<ScoreData> loadScoreDataFromCsv() {
        checkFileExist();
        List<ScoreData> allScoresList = new ArrayList<>();

        try (Reader in = new BufferedReader(new FileReader(CSV_PATH.toString()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                addRecordToScoreDataList(allScoresList, record);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sortScoreDataList(allScoresList);

        return allScoresList.subList(0, 20);
    }

    private void checkFileExist() {
        if (!Files.exists(CSV_PATH)) {
            createFile();
        }
    }

    private void cacheNewScore(int score) {
        if (scoreDataList.size() < 20 || score > minScore) {
            ScoreData scoreData = createScoreDataFromScore(score);
            removeMinScore();
            scoreDataList.add(scoreData);
            sortScoreDataList(scoreDataList);
            minScore = scoreDataList.stream().min(Comparator.comparingInt(ScoreData::score)).orElse(scoreData).score();
        }
    }

    private void removeMinScore() {
        scoreDataList.stream()
                .filter(scoreData -> scoreData.score() < minScore)
                .findFirst()
                .ifPresent(scoreDataList::remove);
    }

    private static void sortScoreDataList(@NotNull List<ScoreData> scoreList) {
        scoreList.sort(Comparator.comparingInt(ScoreData::score).reversed());
    }

    private void addRecordToScoreDataList(@NotNull List<ScoreData> scoreDataList, @NotNull CSVRecord record) {
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
            Files.createFile(ScoreFileHandler.CSV_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
