package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import javafx.scene.shape.Rectangle;

public abstract class Sprite extends Rectangle {
    boolean dead = false;

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}