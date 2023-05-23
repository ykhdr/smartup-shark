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
public class GameModelTest {

    @Test
    public void playerDoesNotMoveTest() {
        GameObjects configGameObjects = new GameObjects(List.of(),
                new PlayerObject(new Size(30, 20), new Position(0, 0), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);

        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        double mouseX = 512;
        double mouseY = 360;

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(mouseX, mouseY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
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
    public void playerEatsNonEatableFishTest() {
        GameObjects configGameObjects = new GameObjects(List.of(
                new FishObject(FishType.FAT, new Size(30, 20), false, new Position(1024, 720), Direction.LEFT)),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);

        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(1024, 720);
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertTrue(gameModel.isGameOver());
        TestCase.assertEquals(0, gameModel.getScore());
        TestCase.assertEquals(playerWidth, player.size().width());
        TestCase.assertEquals(playerHeight, player.size().height());
    }

    @Test
    public void playerEatsEatableFishTest() {
        GameObjects configGameObjects = new GameObjects(List.of(
                new FishObject(FishType.SMALL, new Size(20, 15), true, new Position(1024, 720), Direction.LEFT)),
                new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                0);

        Size fieldSize = new Size(1024, 720);

        GameModel gameModel = new GameModel(fieldSize, configGameObjects);

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(1024, 720);
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertFalse(gameModel.isGameOver());
        TestCase.assertEquals(1, gameModel.getScore());
        TestCase.assertEquals(playerWidth + 1, player.size().width());
        TestCase.assertEquals(playerHeight + 1, player.size().height());
    }
}
