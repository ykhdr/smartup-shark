package ru.nsu.fit.ykhdr.smartupshark;

// CR: think which invariants we have in program, move them to config file in order to test them
public class GameModelTest {

//    @Test
//    public void playerDoesNotMoveTest() {
//        GameModel gameModel = new GameModel();
//
//        double mouseX = 512;
//        double mouseY = 360;
//
//        double playerWidth = 30;
//        double playerHeight = 20;
//
//        gameModel.movePlayer(mouseX, mouseY);
//
//        PlayerObject player = gameModel.getGameObjects().player();
//
//        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
//        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
//    }
//
//    @Test
//    public void playerMovesTest() {
//        GameModel gameModel = new GameModel();
//
//        double mouseX = 205;
//        double mouseY = 105;
//
//        double playerWidth = 30;
//        double playerHeight = 20;
//
//        gameModel.movePlayer(mouseX, mouseY);
//
//        PlayerObject player = gameModel.getGameObjects().player();
//
//        TestCase.assertEquals(mouseX - playerWidth / 2, player.position().x());
//        TestCase.assertEquals(mouseY - playerHeight / 2, player.position().y());
//        TestCase.assertEquals(Direction.LEFT, player.direction());
//    }
//
//    @Test
//    public void playerEatsNonEatableFishTest() {
//        GameModel gameModel = new GameModel("/configs/non-eatable-config.properties");
//
//        double playerWidth = 30;
//        double playerHeight = 20;
//
//        gameModel.movePlayer(1024, 720);
//        gameModel.update();
//
//        GameObjects gameObjects = gameModel.getGameObjects();
//        PlayerObject player = gameObjects.player();
//
//        TestCase.assertTrue(gameModel.isGameOver());
//        TestCase.assertEquals(0, gameModel.getScore());
//        TestCase.assertEquals(playerWidth, player.size().width());
//        TestCase.assertEquals(playerHeight, player.size().height());
//    }
//
//    @Test
//    public void playerEatsEatableFishTest() {
//        GameModel gameModel = new GameModel("/configs/eatable-config.properties");
//
//        double playerWidth = 30;
//        double playerHeight = 20;
//
//        gameModel.movePlayer(1024, 720);
//        gameModel.update();
//
//        GameObjects gameObjects = gameModel.getGameObjects();
//        PlayerObject player = gameObjects.player();
//
//        TestCase.assertFalse(gameModel.isGameOver());
//        TestCase.assertEquals(1, gameModel.getScore());
//        TestCase.assertEquals(playerWidth + 1, player.size().width());
//        TestCase.assertEquals(playerHeight + 1, player.size().height());
//    }
}
