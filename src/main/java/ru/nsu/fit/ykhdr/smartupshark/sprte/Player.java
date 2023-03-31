package ru.nsu.fit.ykhdr.smartupshark.sprte;


import javafx.scene.paint.Color;

public class Player extends Sprite{
    public Player() {
        super(0,0,30,15, Color.ALICEBLUE);
    }

    public void reset(){
        setWidth(30);
        setHeight(15);
        dead = false;
    }
}
