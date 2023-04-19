package ru.nsu.fit.ykhdr.smartupshark.sprite.horizontallyenemy;

import ru.nsu.fit.ykhdr.smartupshark.sprite.Direction;
import ru.nsu.fit.ykhdr.smartupshark.sprite.SinusoidalEnemy;

// CR: add strategies instead of inheritance
public abstract class HorizontalSinusoidalEnemy extends SinusoidalEnemy {
    public HorizontalSinusoidalEnemy(double x, double y, double width, double height) {
        super(x,y,width, height);
    }

    @Override
    public void layout() {
        double time = System.currentTimeMillis() / 1000.0;
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        setTranslateY(getTranslateY() + speed * offset);
        setTranslateX(direction == Direction.RIGHT ? getTranslateX() + speed : getTranslateX() - speed);
    }
}