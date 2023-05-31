package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.GameObjectModel;

public record GameField(double width, double height, int spawnOffset) {
    public boolean checkOutOfBounds(@NotNull GameObjectModel object) {
        Position position = object.getPosition();
        return position.x() < -spawnOffset ||
                position.x() > width + spawnOffset ||
                position.y() < -spawnOffset ||
                position.y() > height + spawnOffset;
    }
}
