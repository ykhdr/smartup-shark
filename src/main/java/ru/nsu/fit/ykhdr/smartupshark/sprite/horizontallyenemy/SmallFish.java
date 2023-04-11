package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

public class SmallFish extends MovingAlongSineWaveHorizontallyEnemy {
    public SmallFish(double x, double y, double sizeScale) {
        super(x,y,20 * sizeScale, 10 * sizeScale);
    }
}
