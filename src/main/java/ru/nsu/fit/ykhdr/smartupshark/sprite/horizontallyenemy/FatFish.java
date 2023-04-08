package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class FatFish extends MovingAlongSineWaveHorizontallyEnemy {
    public FatFish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super(40 * sizeScale, 40 * sizeScale);

        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(size() < playerSize);
        setDirection(getX() < 0 ? Direction.RIGHT : Direction.LEFT);
    }
}
