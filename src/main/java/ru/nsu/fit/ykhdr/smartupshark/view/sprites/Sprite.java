package ru.nsu.fit.ykhdr.smartupshark.view.sprites;

import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle{
    private final int id;
    public Sprite(double width, double height, int id) {
        super(width, height);
        this.id = id;
    }

    public int getSpriteId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprite sprite = (Sprite) o;

        return id == sprite.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
