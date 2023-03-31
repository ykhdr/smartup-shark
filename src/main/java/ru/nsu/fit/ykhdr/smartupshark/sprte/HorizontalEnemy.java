package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public abstract class HorizontalEnemy extends Sprite implements Enemy {
    private double amplitude = 1;
    private double frequency = 0.5;
    private double phase = 0;
    private double speed = 1.5;
    final Direction direction;

    public HorizontalEnemy(double x, double y, double width, double height, @NotNull Color color, @NotNull Direction direction) {
        super(x, y, width, height, color);

        setFill(color);
        this.direction = direction;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    abstract void setStyleId();

    public void layout() {
        double time = System.currentTimeMillis() / 1000.0; // current time in seconds
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        setTranslateY(getTranslateY() + speed * offset);
        setTranslateX(direction == Direction.RIGHT ? getTranslateX() + speed : getTranslateX() - speed);
    }
}