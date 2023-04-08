package ru.nsu.fit.ykhdr.smartupshark.sprite;

public class Player extends Sprite {

    public Player() {
        super(30, 15);
    }

    public void reset() {
        setWidth(30);
        setHeight(15);
        dead = false;
    }
}
