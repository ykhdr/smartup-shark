package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class LongFish extends MovingAlongSineWaveHorizontallyEnemy {
    public LongFish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super( 60 * sizeScale, 30 * sizeScale);
        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(size() < playerSize);
        setDirection(getX() < 0 ? Direction.RIGHT : Direction.LEFT);
    }
}
