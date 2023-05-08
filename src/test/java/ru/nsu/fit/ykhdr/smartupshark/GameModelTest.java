package ru.nsu.fit.ykhdr.smartupshark;

import junit.framework.TestCase;
import org.junit.Test;
import ru.nsu.fit.ykhdr.smartupshark.model.Direction;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.model.gameobjects.PlayerObject;

public class GameModelTest {

    @Test
    public void playerDoesNotMoveTest() {
        GameModel gameModel = new GameModel();

        double mouseX = gameModel.getSceneSize().width() / 2;
        double mouseY = gameModel.getSceneSize().height() / 2;

        double playerWidth = 30;
        double playerHeight = 20;

        gameModel.movePlayer(mouseX, mouseY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
    }

    @Test
    public void playerMovesTest() {
        GameModel gameModel = new GameModel();

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
        GameModel gameModel = new GameModel("/configs/non-eatable-config.properties");

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
        GameModel gameModel = new GameModel("/configs/eatable-config.properties");

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
