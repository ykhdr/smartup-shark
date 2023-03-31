package ru.nsu.fit.ykhdr.smartupshark.sprte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public abstract class Sprite extends Rectangle {
    public Sprite(double x, double y, double width, double height, @NotNull Color color) {
        super(x, y, width, height);
        setFill(color);
    }

    boolean dead = false;

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}