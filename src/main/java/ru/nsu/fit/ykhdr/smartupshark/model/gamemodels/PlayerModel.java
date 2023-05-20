package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.Position;
import ru.nsu.fit.ykhdr.smartupshark.model.Size;

public class PlayerModel extends GameObjectModel {
    private static final double PLAYER_SIZE_SCALE = 1;
    private static final double MAX_PLAYER_AREA = 18050;

    public PlayerModel(@NotNull Direction direction) {
        super(direction);

        this.size = new Size(30, 20);
    }

    public void increaseSize() {
        if (size.height() * size.width() >= MAX_PLAYER_AREA) {
            return;
        }

        double width = size.width();
        double height = size.height();

        size = new Size(width + PLAYER_SIZE_SCALE, height + PLAYER_SIZE_SCALE);
    }

    public void move(double x, double y) {
        double curX = position.x();

        // CR: use setter
        position = new Position(x - size.width() / 2, y - size.height() / 2);

        setDirection(position.x() < curX ? Direction.LEFT : Direction.RIGHT);
    }
}