package ru.nsu.fit.ykhdr.smartupshark.model.gameobjects;

public class GameObjectIdManager {

    static final private int PLAYER_ID = 0;

    static int nextId = 1;

    public static int getNextId() {
        if(nextId == Integer.MAX_VALUE) {
            nextId = 1;
        }

        return nextId++;
    }

    public static int getPlayerId() {
        return PLAYER_ID;
    }
}
