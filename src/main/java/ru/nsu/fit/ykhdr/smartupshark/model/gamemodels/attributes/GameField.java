package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.GameObjectModel;

public record GameField(double width, double height) {
    // CR: should it be relative to width / height?
    public static final int SPAWN_OFFSET = 50;

    public boolean checkOutOfBounds(@NotNull GameObjectModel object) {
        Position position = object.getPosition();
        return position.x() < -SPAWN_OFFSET ||
                position.x() > width + SPAWN_OFFSET ||
                position.y() < -SPAWN_OFFSET ||
                position.y() > height + SPAWN_OFFSET;
    }
}
