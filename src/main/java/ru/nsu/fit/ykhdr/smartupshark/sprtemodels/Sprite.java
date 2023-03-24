package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Sprite extends Rectangle {
    boolean dead = false;

    public Sprite(double x, double y, double w, double h, Color color) {
        super(x, y, w, h);
        setFill(color);
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}