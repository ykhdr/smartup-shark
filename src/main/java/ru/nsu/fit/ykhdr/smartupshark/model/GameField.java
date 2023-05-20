package ru.nsu.fit.ykhdr.smartupshark.model;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.GameObjectModel;

public record GameField(double width, double height) {
    private static final int SPAWN_OFFSET = 50;

    public boolean checkOutOfBounds(@NotNull GameObjectModel object) {
        Position position = object.getPosition();
        return position.x() < -SPAWN_OFFSET ||
                position.x() > width + SPAWN_OFFSET ||
                position.y() < -SPAWN_OFFSET ||
                position.y() > height + SPAWN_OFFSET;
    }
}