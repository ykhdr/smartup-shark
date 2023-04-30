package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

import org.jetbrains.annotations.NotNull;

public abstract class GameObject {
    protected final @NotNull Coordinates coordinates = new Coordinates();
    protected final @NotNull Size size = new Size();

    private final int id;
    private boolean isDead = false;

    public GameObject(int id) {
        this.id = id;
    }

    public @NotNull Coordinates getCoordinates() {
        return coordinates;
    }

    public @NotNull Size getSize() {
        return size;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public @NotNull Bounds getBounds() {
        double minX = coordinates.getX() - size.getWidth() / 2;
        double minY = coordinates.getY() - size.getHeight() / 2;
        double maxX = coordinates.getX() + size.getWidth() / 2;
        double maxY = coordinates.getY() + size.getHeight() / 2;

        return new Bounds(maxX, minX, maxY, minY);
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject that = (GameObject) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
