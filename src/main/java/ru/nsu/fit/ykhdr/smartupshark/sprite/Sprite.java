package ru.nsu.fit.ykhdr.smartupshark.sprite;

import javafx.scene.shape.Rectangle;

public abstract class Sprite extends Rectangle {
    boolean dead = false;

    public Sprite(double width, double height) {
        super(width, height);
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead(){
        return dead;
    }

    public double size() {
        return getHeight() * getWidth();
    }
}