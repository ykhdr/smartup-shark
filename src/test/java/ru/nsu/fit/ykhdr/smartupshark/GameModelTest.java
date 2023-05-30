package ru.nsu.fit.ykhdr.smartupshark;

import junit.framework.TestCase;
import org.junit.Test;
import ru.nsu.fit.ykhdr.smartupshark.config.ConfigParser;
import ru.nsu.fit.ykhdr.smartupshark.config.GameConfig;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;
import ru.nsu.fit.ykhdr.smartupshark.model.gamemodels.attributes.GameField;


/*
CR:
- move player +-
- player does not grow in size at some point -
- enemy collides with enemy -

 */
public class GameModelTest {

    @Test
    public void playerDoesNotMoveTest() {
        // CR: create config in code
        String configPath = "src/test/resources/config/only-player.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        double playerWidth = config.gameObjects().player().size().width();
        double playerHeight = config.gameObjects().player().size().height();

        double mouseX = 512;
        double mouseY = 360;

        gameModel.movePlayer(mouseX, mouseY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
    }

    @Test
    public void playerMovesTest() {
        String configPath = "src/test/resources/config/only-player.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        double mouseX = 205;
        double mouseY = 105;

        Size playerSize = config.gameObjects().player().size();

        gameModel.movePlayer(mouseX, mouseY);

        PlayerObject player = gameModel.getGameObjects().player();

        TestCase.assertEquals(mouseX - playerSize.width() / 2, player.position().x());
        TestCase.assertEquals(mouseY - playerSize.height() / 2, player.position().y());
        // CR: check other directions
        TestCase.assertEquals(Direction.LEFT, player.direction());
    }

    @Test
    public void fishMovesInsideGameFieldTest() {
        String configPath = "src/test/resources/config/fish-inside-game-field.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        gameModel.update();

        TestCase.assertFalse(gameModel.getGameObjects().enemies().isEmpty());
    }

    @Test
    public void fishMovesOutsideGameFieldTest() {
        String configPath = "src/test/resources/config/fish-on-edge-of-game-field.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        gameModel.update();

        TestCase.assertTrue(gameModel.getGameObjects().enemies().isEmpty());
    }

    @Test
    public void playerEatsNonEatableFishTest() {
        String configPath = "src/test/resources/config/player-with-non-eatable-fish.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        Position fishPosition = config.gameObjects().enemies().get(0).position();

        gameModel.movePlayer(fishPosition.x(), fishPosition.y());
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertTrue(gameModel.isGameOver());
        TestCase.assertEquals(config.gameObjects().score(), gameModel.getScore());
        TestCase.assertEquals(config.gameObjects().player().size().width(), player.size().width());
        TestCase.assertEquals(config.gameObjects().player().size().height(), player.size().height());
    }

//    private static void testWithModel(Consumer<GameModel> testBody) {
//
//    }

    @Test
    public void playerEatsEatableFishTest() {
        String configPath = "src/test/resources/config/player-with-eatable-fish.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        Position fishPosition = config.gameObjects().enemies().get(0).position();

        gameModel.movePlayer(fishPosition.x(), fishPosition.y());
        gameModel.update();

        GameObjects gameObjects = gameModel.getGameObjects();
        PlayerObject player = gameObjects.player();

        TestCase.assertFalse(gameModel.isGameOver());
        TestCase.assertEquals(config.gameObjects().score() + 1, gameModel.getScore());
        TestCase.assertEquals(config.gameObjects().player().size().width() + 1, player.size().width());
        TestCase.assertEquals(config.gameObjects().player().size().height() + 1, player.size().height());
    }

    @Test
    public void immediateSpawnTest() {
        String configPath = "src/test/resources/config/zero-spawn-delay.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        gameModel.update();
        GameObjects gameObjects = gameModel.getGameObjects();

        TestCase.assertEquals(1, gameObjects.enemies().size());

        FishObject fishObject = gameObjects.enemies().get(0);

        TestCase.assertTrue(
                fishObject.position().x() < config.fieldSize().width() + GameField.SPAWN_OFFSET ||
                        fishObject.position().x() > -GameField.SPAWN_OFFSET ||
                        fishObject.position().y() < config.fieldSize().height() + GameField.SPAWN_OFFSET ||
                        fishObject.position().y() > -GameField.SPAWN_OFFSET);
    }

    @Test
    public void nonZeroSpawnDelayTest() {
        String configPath = "src/test/resources/config/long-spawn-delay.json";
        GameConfig config = ConfigParser.getInstance().parse(configPath);

        GameModel gameModel = new GameModel(config);

        for (int i = 0; i < config.spawn().spawnDelay() - 1; i++) {
            gameModel.update();
        }

        GameObjects gameObjects = gameModel.getGameObjects();

        TestCase.assertEquals(0, gameObjects.enemies().size());
    }

}