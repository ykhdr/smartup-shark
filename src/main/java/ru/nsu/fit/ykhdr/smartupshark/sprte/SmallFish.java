package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;

public class SmallFish extends HorizontalEnemy {
    public SmallFish(double x, double y, double sizeScale) {
        super(x, y, 20 * sizeScale, 10 * sizeScale, Color.FUCHSIA, x < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    void setStyleId() {
        setId(direction == Direction.LEFT ? "small-fish-left-red" : "small-fish-right-red");
    }
}
