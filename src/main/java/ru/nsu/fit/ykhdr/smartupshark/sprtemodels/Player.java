package ru.nsu.fit.ykhdr.smartupshark.sprtemodels;

import javafx.scene.paint.Color;

public class Player extends Sprite{
    private final double defaultWeight;
    private final double defaultHeight;

    public Player(double x, double y, double w, double h, Color color) {
        super(x, y, w, h, color);
        this.defaultWeight = w;
        this.defaultHeight = h;
    }

    public void reset(){
        setWidth(defaultWeight);
        setHeight(defaultHeight);
        dead = false;
    }
}
