package ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;

public class Jellyfish extends MovingAlongSineWaveVerticallyEnemy {
    public Jellyfish(double fieldWeight, double fieldHeight, double sizeScale, double playerSize) {
        super(25 * sizeScale, 45 * sizeScale);
        setRandomX(fieldWeight);
        setRandomY(fieldHeight);
        setEatable(false);
        setDirection(Direction.UP);
    }
}
