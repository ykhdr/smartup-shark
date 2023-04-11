package ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy;

public class Jellyfish extends MovingAlongSineWaveVerticallyEnemy {
    public Jellyfish(double x, double y, double sizeScale) {
        super(x, y, 25 * sizeScale, 45 * sizeScale);
    }

    @Override
    public void setEatable(boolean eatable){
        //ignore
    }
}
