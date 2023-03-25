package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import javafx.scene.paint.Color;

public class FatFish extends Enemy{
    public FatFish(double x, double y) {
        super(x, y, 40, 40, Color.SILVER);
        setId("fat-fish");
    }
}
