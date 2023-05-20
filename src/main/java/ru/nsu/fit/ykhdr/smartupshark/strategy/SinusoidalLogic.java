package ru.nsu.fit.ykhdr.smartupshark.strategy;

import java.util.Random;

// CR: store data as field in strategies
public abstract class SinusoidalLogic {
    protected double amplitude;
    protected double frequency;
    protected double phase;
    protected double speed;

    protected SinusoidalLogic() {
        Random random = new Random();

        phase = random.nextDouble(1);
        speed = random.nextDouble(1, 2);
        amplitude = random.nextDouble(0.8);
        frequency = random.nextDouble(0.4, 0.5);
    }
}
