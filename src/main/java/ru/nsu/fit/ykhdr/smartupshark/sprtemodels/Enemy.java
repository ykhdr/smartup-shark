package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import javafx.scene.paint.Color;

public abstract class Enemy extends Sprite {
    private double amplitude = 1;
    private double frequency = 0.5;
    private double phase = 0;
    private double speed = 1.5;
    private boolean rightDirection = true;

    public Enemy(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        setFill(color);
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

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    public void layout() {
        double time = System.currentTimeMillis() / 1000.0; // current time in seconds
        double offset = amplitude * Math.sin(2 * Math.PI * frequency * time + phase);

        setTranslateY(getTranslateY() + speed * offset);
        setTranslateX(rightDirection ? getTranslateX() + speed : getTranslateX() - speed);
    }
}