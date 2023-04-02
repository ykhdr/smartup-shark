package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class SmallFish extends MovingAlongSineWaveHorizontallyEnemy {
    public SmallFish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super(20 * sizeScale, 10 * sizeScale);
        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(size() < playerSize);
        setDirection(getX() < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    protected void setStyleId() {
        if (isEatable()) {
            setId(direction == Direction.LEFT ? "small-fish-left-blue" : "small-fish-right-blue");
        } else {
            setId(direction == Direction.LEFT ? "small-fish-left-red" : "small-fish-right-red");
        }
    }
}
