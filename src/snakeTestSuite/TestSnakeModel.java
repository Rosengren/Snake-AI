package snakeTestSuite;

import static org.junit.Assert.*;

import org.junit.*;

import snake.Model;

public class TestSnakeModel {

    private static final int DEFAULT_SNAKE_SIZE = 5;
    private static final int DEFAULT_APPLE_SIZE = 10;
    private static final int DEFAULT_BOARD_WIDTH = 500;
    private static final int DEFAULT_BOARD_HEIGHT = 500;

    private Model game;

    @Before
    public void setUp() {
        game = new Model();
    }

    @Test
    public void testInitialSnakeLength() {
        assertEquals("Default snake length should be " + DEFAULT_SNAKE_SIZE + ".", DEFAULT_SNAKE_SIZE, game.getSnake().getLength());
    }

    @Test
    public void testInitialBoardSize() {
        assertEquals("Default board width should be " + DEFAULT_BOARD_WIDTH + ".", DEFAULT_BOARD_WIDTH, game.getBoard().getWidth());
        assertEquals("Default board height should be " + DEFAULT_BOARD_HEIGHT + ".", DEFAULT_BOARD_HEIGHT, game.getBoard().getHeight());
    }

    @Test
    public void testInitialAppleSize() {
        assertEquals("Default apple size should be " + DEFAULT_APPLE_SIZE + ".", DEFAULT_APPLE_SIZE, game.getApple().getSize());
    }

    @After
    public void tearDown() {
        game = null;
    }
}
