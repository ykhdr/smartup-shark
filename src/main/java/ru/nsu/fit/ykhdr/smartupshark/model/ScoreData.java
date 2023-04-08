package ru.nsu.fit.ykhdr.smartupshark.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;

public record ScoreData(@NotNull StringProperty date, @NotNull IntegerProperty score) {
}