package ru.nsu.fit.ykhdr.smartupshark.sprite;

import org.jetbrains.annotations.NotNull;

public abstract class Enemy extends Sprite {

    private boolean eatable = false;

    protected @NotNull Direction direction = Direction.STAY_PUT;

    public Enemy(double x, double y,double width, double height) {
        super(x,y,width, height);
    }

    public void setDirection(@NotNull Direction direction) {
        this.direction = direction;
    }

    public void setEatable(boolean eatable){
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

