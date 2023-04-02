package ru.nsu.fit.ykhdr.smartupshark.sprite.verticallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.MovingAlongSineWaveEnemy;

public abstract class MovingAlongSineWaveVerticallyEnemy extends MovingAlongSineWaveEnemy {
    public MovingAlongSineWaveVerticallyEnemy(double width, double height) {
        super(width, height);
    }

    @Override
    public void layout() {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        setTranslateX(getTranslateX() + offset * speed);
        setTranslateY(getTranslateY() - speed);
    }

    protected void setRandomX(double fieldWeight) {
        setX(RANDOM.nextDouble(fieldWeight - 80) + 80);
    }

    protected void setRandomY(double fieldHeight) {
        setY(fieldHeight);
    }
}
