package snakeTestSuite;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.*;
import org.junit.After;
import org.junit.Test;

import snake.Direction;
import snake.Snake;

public class TestSnake {

    private Snake snake;
    private final int DEFAULT_SNAKE_LENGTH = 5;
    private final int CUSTOM_SNAKE_LENGTH = 10;

    private final int DEFAULT_DOT_SIZE = 10;

    private final int SMALL_BOARD_WIDTH = 10;
    private final int SMALL_BOARD_HEIGHT = 10;

    private final int LARGE_BOARD_WIDTH = 1000;
    private final int LARGE_BOARD_HEIGHT = 1000;

    private final int[] DEFAULT_X_COORDINATES = {200, 190, 180, 170, 160, 150, 140, 130, 120, 110};
    private final int[] DEFAULT_Y_COORDINATES = {200, 200, 200, 200, 200, 200, 200, 200, 200, 200};

    @Before
    public void setUp() {
        snake = new Snake();
    }

    @Test
    public void testSnakeConstructors() {

        // test default constructor
        snake = new Snake();
        assertEquals("Default Snake length should be " + DEFAULT_SNAKE_LENGTH + ".", DEFAULT_SNAKE_LENGTH, snake.getLength());

        // test custom constructor
        snake = new Snake(CUSTOM_SNAKE_LENGTH);
        assertEquals("Custom Snake length should be " + CUSTOM_SNAKE_LENGTH + ".", CUSTOM_SNAKE_LENGTH, snake.getLength());

    }

    @Test
    public void testSnakeInitialization() {

        snake.initialize();
        int[] xCoordinates = snake.getXCoordinates();
        int[] yCoordinates = snake.getYCoordinates();

        assertEquals("Snake should have " + DEFAULT_SNAKE_LENGTH + " x coordinates.", DEFAULT_SNAKE_LENGTH, xCoordinates.length);
        assertEquals("Snake should have " + DEFAULT_SNAKE_LENGTH + " y coordinates.", DEFAULT_SNAKE_LENGTH, yCoordinates.length);

        for (int i = 0; i < xCoordinates.length; i++)
            assertEquals("Coordinate #" + i + " should be " + DEFAULT_X_COORDINATES[i] + ".", DEFAULT_X_COORDINATES[i], xCoordinates[i]);

        for (int i = 0; i < yCoordinates.length; i++)
            assertEquals("Coordiante #" + i + " should be " + DEFAULT_Y_COORDINATES[i] + ".", DEFAULT_Y_COORDINATES[i], yCoordinates[i]);
    }

    @Test
    public void testSnakeMoveDirections() {

        assertNull("Snake movement direction should not be see (should be null).", snake.getDirection());

        for (Direction dir : Direction.values()) {
            snake.setDirection(dir);
            assertEquals("Snake should be moving " + dir.name() + ".", dir, snake.getDirection());
        }

        snake.setDirection(Direction.UP);
        snake.setDirection(Direction.DOWN);
        assertEquals("Snake should continue moving up when down is selected.", Direction.UP, snake.getDirection());

        snake.setDirection(Direction.RIGHT);
        snake.setDirection(Direction.LEFT);
        assertEquals("Snake should continue moving right when left is selected.", Direction.RIGHT, snake.getDirection());

        snake.setDirection(Direction.DOWN);
        snake.setDirection(Direction.UP);
        assertEquals("Snake should continue moving down when up is selected.", Direction.DOWN, snake.getDirection());

        snake.setDirection(Direction.LEFT);
        snake.setDirection(Direction.RIGHT);
        assertEquals("Snake should continue moving left when right is selected.", Direction.LEFT, snake.getDirection());

    }

    @Test
    public void testUpdateSnakeLocation() {

        int[] snakeXCoordinates;
        int[] snakeYCoordinates;

        moveSnake(Direction.UP);
        snakeXCoordinates = snake.getXCoordinates();
        snakeYCoordinates = snake.getYCoordinates();

        assertEquals("(up) Snake head should have x coordinate: " + DEFAULT_X_COORDINATES[0] + ".", DEFAULT_X_COORDINATES[0], snakeXCoordinates[0]);
        assertEquals("(up) Snake head should have y coordinate: " + (DEFAULT_Y_COORDINATES[0] + DEFAULT_DOT_SIZE) + ".", DEFAULT_Y_COORDINATES[0] - DEFAULT_DOT_SIZE, snakeYCoordinates[0]);
        moveSnake(Direction.LEFT);
        snakeXCoordinates = snake.getXCoordinates();
        snakeYCoordinates = snake.getYCoordinates();

        assertEquals("(left) Snake head should have x coordinate: " + DEFAULT_X_COORDINATES[0] + ".", DEFAULT_X_COORDINATES[0] - DEFAULT_DOT_SIZE, snakeXCoordinates[0]);
        assertEquals("(left) Snake head should have y coordinate: " + (DEFAULT_Y_COORDINATES[0] + DEFAULT_DOT_SIZE) + ".", DEFAULT_Y_COORDINATES[0], snakeYCoordinates[0]);

        moveSnake(Direction.DOWN);
        snakeXCoordinates = snake.getXCoordinates();
        snakeYCoordinates = snake.getYCoordinates();

        assertEquals("(down) Snake head should have x coordinate: " + DEFAULT_X_COORDINATES[0] + ".", DEFAULT_X_COORDINATES[0], snakeXCoordinates[0]);
        assertEquals("(down) Snake head should have y coordinate: " + (DEFAULT_Y_COORDINATES[0] + DEFAULT_DOT_SIZE) + ".", DEFAULT_Y_COORDINATES[0] + DEFAULT_DOT_SIZE, snakeYCoordinates[0]);

        moveSnake(Direction.RIGHT);
        snakeXCoordinates = snake.getXCoordinates();
        snakeYCoordinates = snake.getYCoordinates();

        assertEquals("(right) Snake head should have x coordinate: " + DEFAULT_X_COORDINATES[0] + ".", DEFAULT_X_COORDINATES[0] + DEFAULT_DOT_SIZE, snakeXCoordinates[0]);
        assertEquals("(right) Snake head should have y coordinate: " + (DEFAULT_Y_COORDINATES[0] + DEFAULT_DOT_SIZE) + ".", DEFAULT_Y_COORDINATES[0], snakeYCoordinates[0]);

    }

    @Test
    public void testWallCollision() {

        assertTrue("Snake should have collided with the wall", snake.checkWallCollision(SMALL_BOARD_WIDTH, SMALL_BOARD_HEIGHT));
        assertFalse("Snake should not have collided with the wall", snake.checkWallCollision(LARGE_BOARD_WIDTH, LARGE_BOARD_HEIGHT));

    }

    @Test
    public void testSnakeCollision() {

        snake = new Snake(CUSTOM_SNAKE_LENGTH);


        assertFalse("Snake should not have collided with itself.", snake.checkSnakeCollision());

        snake.setDirection(Direction.UP);
        snake.update();
        snake.setDirection(Direction.LEFT);
        snake.update();
        snake.setDirection(Direction.DOWN);
        snake.update();

        assertTrue("Snake should have collided with itself.", snake.checkSnakeCollision());
    }

    @Test
    public void testAppleCollision() {

        assertTrue("Snake should have collided with the apple", snake.checkCollision(DEFAULT_X_COORDINATES[0], DEFAULT_Y_COORDINATES[0]));
        assertFalse("Snake should not have collided with the apple", snake.checkCollision(LARGE_BOARD_WIDTH, LARGE_BOARD_HEIGHT));
    }

    private void moveSnake(Direction dir) {
        snake.initialize();
        snake.setDirection(dir);
        snake.update();
    }

    @After
    public void tearDown() {
        snake = null;
    }
}
