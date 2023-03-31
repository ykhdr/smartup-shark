package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;

public class MidFish extends HorizontalEnemy{

    public MidFish(double x, double y, double sizeScale) {
        super(x, y, 35 * sizeScale, 25 * sizeScale, Color.SALMON, x < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    void setStyleId() {
        setId(direction == Direction.LEFT ? "mid-fish-left-red" : "mid-fish-right-red");
    }
}
