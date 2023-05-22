package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.Bounds;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

public abstract class GameObjectModel {
    private @NotNull Position position = new Position(0,0);
    private @NotNull Size size;
    private @NotNull Direction direction;

    protected GameObjectModel(@NotNull Size size,@NotNull Direction direction) {
        this.direction = direction;
        this.size = size;
    }

    public @NotNull Position getPosition() {
        return position;
    }

    public void setPosition(@NotNull Position position) {
        this.position = position;
    }

    public @NotNull Size getSize() {
        return size;
    }
    public void setSize(@NotNull Size size) {
        this.size = size;
    }

    public @NotNull Bounds getBounds() {
        double minX = position.x() - size.width() / 2;
        double minY = position.y() - size.height() / 2;
        double maxX = position.x() + size.width() / 2;
        double maxY = position.y() + size.height() / 2;

        return new Bounds(maxX, minX, maxY, minY);
    }

    public @NotNull Direction getDirection() {
        return direction;
    }

    public void setDirection(@NotNull Direction direction) {
        this.direction = direction;
    }

    public boolean checkInBoundsOf(@NotNull GameObjectModel other) {
        Bounds enemyBounds = other.getBounds();

        return getBounds().maxX() > enemyBounds.minX() && getBounds().minX() < enemyBounds.maxX() &&
                getBounds().maxY() > enemyBounds.minY() && getBounds().minY() < enemyBounds.maxY();
    }
}