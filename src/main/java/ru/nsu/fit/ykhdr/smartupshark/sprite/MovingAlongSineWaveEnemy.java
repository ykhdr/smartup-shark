package ru.nsu.fit.ykhdr.smartupshark.sprite;

import java.util.Random;

public abstract class MovingAlongSineWaveEnemy extends Enemy{
    protected double amplitude = 1;
    protected double frequency = 0.5;
    protected double phase = 0;
    protected double speed = 1.5;

    protected static final Random RANDOM = new Random();

    public MovingAlongSineWaveEnemy(double width, double height) {
        super(width, height);
        setRandomSineParams();
    }
    private void setRandomSineParams(){
        phase = RANDOM.nextDouble(1);
        speed = RANDOM.nextDouble(1, 2);
        amplitude = RANDOM.nextDouble(0.8);
        frequency = RANDOM.nextDouble(0.4, 0.5);
    }
}
