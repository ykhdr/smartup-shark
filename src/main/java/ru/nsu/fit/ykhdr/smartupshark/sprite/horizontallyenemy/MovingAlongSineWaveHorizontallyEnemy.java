package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;
import ru.nsu.fit.ykhdr.smartupshark.sprite.MovingAlongSineWaveEnemy;

public abstract class MovingAlongSineWaveHorizontallyEnemy extends MovingAlongSineWaveEnemy {
    public MovingAlongSineWaveHorizontallyEnemy(double width, double height) {
        super(width, height);
    }

    @Override
    public void layout() {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        setTranslateY(getTranslateY() + speed * offset);
        setTranslateX(direction == Direction.RIGHT ? getTranslateX() + speed : getTranslateX() - speed);
    }

    protected void setRandomX(double fieldWeight) {
        setX(RANDOM.nextBoolean() ? -50 : fieldWeight + 50);
    }

    protected void setRandomY(double fieldHeight) {
        setY(RANDOM.nextDouble(fieldHeight - 80) + 80);
    }
}