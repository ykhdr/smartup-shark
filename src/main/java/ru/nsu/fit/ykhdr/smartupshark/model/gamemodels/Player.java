package ru.nsu.fit.ykhdr.smartupshark.model.gamemodels;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;

public class Player extends GameObjectModel {
    private static final double PLAYER_SIZE_SCALE = 1;
    private static final double MAX_PLAYER_AREA = 18050;

    public Player(@NotNull Size size, @NotNull Position position, @NotNull Direction direction) {
        super(size, position, direction);
    }

    public void increaseSize() {
        if (getSize().height() * getSize().width() >= MAX_PLAYER_AREA) {
            return;
        }

        double width = getSize().width();
        double height = getSize().height();

        setSize(new Size(width + PLAYER_SIZE_SCALE, height + PLAYER_SIZE_SCALE));
    }

    public void move(double x, double y) {
        double curX = getPosition().x();

        setPosition(new Position(x - getSize().width() / 2, y - getSize().height() / 2));
        setDirection(getPosition().x() < curX ? Direction.LEFT : Direction.RIGHT);
    }
}