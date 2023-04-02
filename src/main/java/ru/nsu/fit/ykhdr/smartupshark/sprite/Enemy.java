package ru.nsu.fit.ykhdr.smartupshark.sprite;

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

    public abstract void layout();

    protected abstract void setStyleId();
}

