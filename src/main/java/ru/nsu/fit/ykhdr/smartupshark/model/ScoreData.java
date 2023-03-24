package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoreData {
    private final StringProperty date;
    private final IntegerProperty score;

    public ScoreData(String fate, Integer score) {
        this.date = new SimpleStringProperty(fate);
        this.score = new SimpleIntegerProperty(score);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
        date.set(value);
    }

    public Integer getScore() {
        return score.get();
    }

    public void setScore(Integer value) {
        score.set(value);
    }
}