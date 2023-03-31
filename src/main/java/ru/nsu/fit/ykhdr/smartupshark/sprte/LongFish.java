package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;

public class LongFish extends HorizontalEnemy {
    public LongFish(double x, double y, double sizeScale) {
        super(x, y, 60 * sizeScale, 30 * sizeScale, Color.CRIMSON, x < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    void setStyleId() {
        setId(direction == Direction.LEFT ? "long-fish-left-red" : "long-fish-right-red");
    }
}
