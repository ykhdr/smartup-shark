package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;

public abstract class Enemy extends Sprite {

    private boolean eatable = false;

    protected Direction direction;

    public Enemy(double width, double height) {
        super(width, height);
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void setEatable(boolean eatable){
        this.eatable = eatable;
    }
    public boolean isEatable() {
        return eatable;
    }

    public @NotNull Direction getDirection() {
        return direction;
    }

    public abstract void layout();

}

