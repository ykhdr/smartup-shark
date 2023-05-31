package ru.nsu.fit.ykhdr.smartupshark;

import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import ru.nsu.fit.ykhdr.smartupshark.config.FactoryConfig;
import ru.nsu.fit.ykhdr.smartupshark.config.GameConfig;
import ru.nsu.fit.ykhdr.smartupshark.config.SpawnTimeConfig;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.FishObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.GameObjects;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.PlayerObject;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Direction;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.FishType;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Position;
import ru.nsu.fit.ykhdr.smartupshark.gameobjects.attributes.Size;
import ru.nsu.fit.ykhdr.smartupshark.model.GameModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


public class GameModelTest {
    private static void testWithModel(@NotNull Consumer<GameModel> testBody, @NotNull GameConfig config) {
        GameModel gameModel = new GameModel(config);
        testBody.accept(gameModel);
    }

    @Test
    public void playerDoesNotMoveTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            double playerWidth = config.gameObjects().player().size().width();
            double playerHeight = config.gameObjects().player().size().height();

            double mouseX = 512;
            double mouseY = 360;

            gameModel.movePlayer(mouseX, mouseY);

            PlayerObject player = gameModel.getGameObjects().player();

            TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
            TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
            TestCase.assertEquals(Direction.LEFT, player.direction());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void playerMovesTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            double fieldHeight = config.fieldSize().height();
            double fieldWidth = config.fieldSize().width();

            for (int i = 0; i < 2; i++) {
                double mouseX = i == 0 ? 0 : fieldWidth;
                double mouseY = i == 0 ? 0 : fieldHeight;

                Size playerSize = config.gameObjects().player().size();

                gameModel.movePlayer(mouseX, mouseY);

                PlayerObject player = gameModel.getGameObjects().player();

                TestCase.assertEquals(mouseX - playerSize.width() / 2, player.position().x());
                TestCase.assertEquals(mouseY - playerSize.height() / 2, player.position().y());
                TestCase.assertEquals(i == 0 ? Direction.LEFT : Direction.RIGHT, player.direction());
            }
        };

        testWithModel(testBody, config);
    }

    @Test
    public void fishMovesInsideGameFieldTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(new FishObject(FishType.FAT, new Size(40, 40), false, new Position(512, 360), Direction.LEFT)),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            gameModel.update();

            TestCase.assertFalse(gameModel.getGameObjects().enemies().isEmpty());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void fishMovesOutsideGameFieldTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(new FishObject(FishType.FAT, new Size(40, 40), false, new Position(-49, 0), Direction.LEFT)),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            gameModel.update();

            TestCase.assertTrue(gameModel.getGameObjects().enemies().isEmpty());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void playerEatsNonEatableFishTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(new FishObject(FishType.FAT, new Size(40, 40), false, new Position(0, 0), Direction.RIGHT)),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            Position fishPosition = config.gameObjects().enemies().get(0).position();

            gameModel.movePlayer(fishPosition.x(), fishPosition.y());
            gameModel.update();

            GameObjects gameObjects = gameModel.getGameObjects();
            PlayerObject player = gameObjects.player();

            TestCase.assertTrue(gameModel.isGameOver());
            TestCase.assertEquals(config.gameObjects().score(), gameModel.getScore());
            TestCase.assertEquals(config.gameObjects().player().size().width(), player.size().width());
            TestCase.assertEquals(config.gameObjects().player().size().height(), player.size().height());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void playerEatsEatableFishTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(new FishObject(FishType.SMALL, new Size(15, 10), true, new Position(0, 0), Direction.LEFT)),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            Position fishPosition = config.gameObjects().enemies().get(0).position();

            gameModel.movePlayer(fishPosition.x(), fishPosition.y());
            gameModel.update();

            GameObjects gameObjects = gameModel.getGameObjects();
            PlayerObject player = gameObjects.player();

            TestCase.assertFalse(gameModel.isGameOver());
            TestCase.assertEquals(config.gameObjects().score() + 1, gameModel.getScore());
            TestCase.assertEquals(config.gameObjects().player().size().width() + 1, player.size().width());
            TestCase.assertEquals(config.gameObjects().player().size().height() + 1, player.size().height());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void playerDoesNotGrowTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(new FishObject(FishType.SMALL, new Size(15, 10), true, new Position(0, 0), Direction.LEFT)),
                        new PlayerObject(new Size(150, 121), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            Position fishPosition = config.gameObjects().enemies().get(0).position();

            gameModel.movePlayer(fishPosition.x(), fishPosition.y());
            gameModel.update();

            GameObjects gameObjects = gameModel.getGameObjects();
            PlayerObject player = gameObjects.player();

            TestCase.assertEquals(config.gameObjects().player().size().width(), player.size().width());
            TestCase.assertEquals(config.gameObjects().player().size().height(), player.size().height());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void fishCollidesWithFishTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(
                                new FishObject(FishType.SMALL, new Size(15, 10), false, new Position(0, 0), Direction.RIGHT),
                                new FishObject(FishType.LONG, new Size(60, 30), false, new Position(1, 0), Direction.LEFT)),
                        new PlayerObject(new Size(150, 121), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(0.016, 1, 0.05),
                new FactoryConfig(new HashMap<>()),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            gameModel.update();
            GameObjects gameObjects = gameModel.getGameObjects();

            TestCase.assertFalse(gameModel.isGameOver());
            TestCase.assertEquals(2, gameObjects.enemies().size());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void immediateSpawnTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(1, 0, 0),
                new FactoryConfig(Map.of(
                        FishType.FAT, new Size(40, 40),
                        FishType.SMALL, new Size(20, 10),
                        FishType.LONG, new Size(60, 30),
                        FishType.MID, new Size(35, 25),
                        FishType.JELLY, new Size(25, 40)
                )),
                50
        );

        Consumer<GameModel> testBody = (gameModel) -> {
            gameModel.update();
            GameObjects gameObjects = gameModel.getGameObjects();

            TestCase.assertEquals(1, gameObjects.enemies().size());

            FishObject fishObject = gameObjects.enemies().get(0);

            TestCase.assertTrue(
                    fishObject.position().x() < config.fieldSize().width() + config.spawnOffset() ||
                            fishObject.position().x() > -config.spawnOffset() ||
                            fishObject.position().y() < config.fieldSize().height() + config.spawnOffset() ||
                            fishObject.position().y() > -config.spawnOffset());
        };

        testWithModel(testBody, config);
    }

    @Test
    public void nonZeroSpawnDelayTest() {
        GameConfig config = new GameConfig(
                new Size(1024, 720),
                new GameObjects(
                        List.of(),
                        new PlayerObject(new Size(30, 20), new Position(512, 360), Direction.LEFT),
                        0),
                new SpawnTimeConfig(1, 50, 0),
                new FactoryConfig(Map.of(
                        FishType.FAT, new Size(40, 40),
                        FishType.SMALL, new Size(20, 10),
                        FishType.LONG, new Size(60, 30),
                        FishType.MID, new Size(35, 25),
                        FishType.JELLY, new Size(25, 40)
                )),
                50
        );


        Consumer<GameModel> testBody = (gameModel) -> {
            for (int i = 0; i < config.spawnTime().spawnDelay() - 1; i++) {
                gameModel.update();
            }

            GameObjects gameObjects = gameModel.getGameObjects();

            TestCase.assertEquals(0, gameObjects.enemies().size());
        };

        testWithModel(testBody, config);
    }

}