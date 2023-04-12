package ru.nsu.fit.ykhdr.smartupshark.sprite;

import java.util.Random;

public abstract class SinusoidalEnemy extends Enemy{
    protected double amplitude = 1;
    protected double frequency = 0.5;
    protected double phase = 0;
    protected double speed = 1.5;

    protected static final Random RANDOM = new Random();

    public SinusoidalEnemy(double x, double y, double width, double height) {
        super(x,y,width, height);
        setRandomSineParams();
    }
    private void setRandomSineParams(){
        phase = RANDOM.nextDouble(1);
        speed = RANDOM.nextDouble(1, 2);
        amplitude = RANDOM.nextDouble(0.8);
        frequency = RANDOM.nextDouble(0.4, 0.5);
    }
}
