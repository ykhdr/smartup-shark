package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class FatFish extends MovingAlongSineWaveHorizontallyEnemy {
    public FatFish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super(40 * sizeScale, 40 * sizeScale);

        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(size() < playerSize);
        setDirection(getX() < 0 ? Direction.RIGHT : Direction.LEFT);
        setStyleId();
    }

    @Override
    protected void setStyleId() {
        if (isEatable()) {
            setId(direction == Direction.LEFT ? "fat-fish-left-blue" : "fat-fish-right-blue");
        } else {
            setId(direction == Direction.LEFT ? "fat-fish-left-red" : "fat-fish-right-red");
        }
    }

}
