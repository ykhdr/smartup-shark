package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class MidFish extends MovingAlongSineWaveHorizontallyEnemy {
    public MidFish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super(35 * sizeScale, 25 * sizeScale);
        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(size() < playerSize);
        setDirection(getX() < 0 ? Direction.RIGHT : Direction.LEFT);
    }
}
