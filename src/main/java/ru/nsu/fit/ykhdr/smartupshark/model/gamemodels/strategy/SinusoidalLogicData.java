package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.strategy;

import java.util.Random;

class SinusoidalLogicData {
    private final double amplitude;
    private final double frequency;
    private final double phase;
    private final double speed;

    public SinusoidalLogicData() {
        Random random = new Random();

        phase = random.nextDouble(1);
        speed = random.nextDouble(1, 1.5);
        amplitude = random.nextDouble(0.8);
        frequency = random.nextDouble(0.4, 0.5);
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPhase() {
        return phase;
    }

    public double getSpeed() {
        return speed;
    }
}
