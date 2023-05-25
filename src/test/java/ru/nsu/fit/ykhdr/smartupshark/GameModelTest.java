package ru.nsu.fit.ykhdr.smartupshark;

import junit.framework.TestCase;
import org.junit.Test;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;

import java.util.List;

// CR: think which invariants we have in program, move them to config file in order to test them

/*
протестировать:
 spawn config - delay and time step,

 */
public class GameModelTest {

    @Test
    public void playerDoesNotMoveTest() {
        double playerPositionX = 512;
        double playerPositionY = 360;

        GameObjects configGameObjects = new GameObjects(List.of(),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(playerPositionX, playerPositionY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(playerPositionX - playerWidth / 2, player.position().x());
        TestCase.assertEquals(playerPositionY - playerHeight / 2, player.position().y());
    }

    @Test
    public void playerMovesTest() {
        GameObjects configGameObjects = new GameObjects(List.of(),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        double mouseX = 205;
        double mouseY = 105;

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(mouseX, mouseY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
        TestCase.assertEquals(Direction.LEFT, player.direction());
    }

    @Test
    public void enemyMovesOutsideGameFieldTest() {
        FishObject fishObject = new FishObject(FishType.FAT, new Size(30, 20), false, new Position(-49, 0), Direction.LEFT);

        GameObjects configGameObjects = new GameObjects(List.of(fishObject),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        gameModel.update();

        TestCase.assertTrue(gameModel.getGameObjects().enemies().isEmpty());
    }

    @Test
    public void enemyMovesInsideGameFieldTest() {
        FishObject fishObject = new FishObject(FishType.MID, new Size(35, 25), false, new Position(10, 10), Direction.LEFT);

        GameObjects configGameObjects = new GameObjects(List.of(fishObject),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        gameModel.update();

        TestCase.assertFalse(gameModel.getGameObjects().enemies().isEmpty());
    }

    @Test
    public void playerEatsNonEatableFishTest() {
        FishObject fishObject = new FishObject(FishType.FAT, new Size(30, 20), false, new Position(1024, 720), Direction.LEFT);

        PlayerObject playerObject = new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT);
        GameObjects configGameObjects = new GameObjects(List.of(fishObject),
                playerObject,
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        gameModel.movePlayer(1024, 720);
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertTrue(gameModel.isGameOver());
        TestCase.assertEquals(0, gameModel.getScore());
        TestCase.assertEquals(playerObject.size().width(), player.size().width());
        TestCase.assertEquals(playerObject.size().height(), player.size().height());
    }

    @Test
    public void playerEatsEatableFishTest() {
        PlayerObject playerObject = new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT);

        FishObject fish = new FishObject(FishType.SMALL, new Size(20, 15), true, new Position(1024, 720), Direction.LEFT);
        GameObjects configGameObjects = new GameObjects(List.of(fish),
                playerObject,
                0);

        Size fieldSize = new Size(1024, 720);
        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        gameModel.movePlayer(1024, 720);
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertFalse(gameModel.isGameOver());
        TestCase.assertEquals(1, gameModel.getScore());
        TestCase.assertEquals(playerObject.size().width() + 1, player.size().width());
        TestCase.assertEquals(playerObject.size().height() + 1, player.size().height());
    }
}