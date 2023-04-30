package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

public class PlayerObject extends GameObject {
    {
        size.setWidth(30);
        size.setHeight(20);
    }

    private boolean leftDirection = false;

    public PlayerObject(int id) {
        super(id);
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }
}
