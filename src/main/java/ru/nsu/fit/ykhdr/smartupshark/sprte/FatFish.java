package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;

public class FatFish extends HorizontalEnemy {
    public FatFish(double x, double y, double sizeScale) {
        super(x, y, 40 * sizeScale, 40 * sizeScale, Color.SILVER, x < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    void setStyleId() {
        setId(direction == Direction.LEFT ? "fat-fish-left-red" : "fat-fish-right-red");
    }
}
