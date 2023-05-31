package ru.nsu.fit.ykhdr.smartupshark.model.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.score.ScoreData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private static final @NotNull ScoreFileHandler INSTANCE = new ScoreFileHandler();
    private static final int SCORE_LIMIT = 20;
    private final List<ScoreData> scoreDataList;
    // CR: without score
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

    // CR: write all scores at the end
    public void writeScore(int score) {
        cacheNewScore(score);
        checkFileExist();

        try (BufferedWriter writer = Files.newBufferedWriter(CSV_PATH, StandardCharsets.UTF_8)) {
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

        try (Reader in = Files.newBufferedReader(CSV_PATH)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            for (CSVRecord record : records) {
                addRecordToScoreDataList(allScoresList, record);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sortScoreDataList(allScoresList);

        return allScoresList.subList(0, SCORE_LIMIT);
    }

    private void checkFileExist() {
        if (!Files.exists(CSV_PATH)) {
            createFile();
        }
    }

    private void cacheNewScore(int score) {
        if (scoreDataList.size() < SCORE_LIMIT || score > minScore) {
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
            Files.createFile(CSV_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
