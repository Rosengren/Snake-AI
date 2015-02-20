package snakeTestSuite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import snake.Obstacles;

import org.junit.*;
import snake.Settings;

public class TestObstacles {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final int CUSTOM_OBSTACLE_X = 10;
    private static final int CUSTOM_OBSTACLE_Y = 20;

    private Obstacles obstacles;

    @Before
    public void setUp() {
        obstacles = new Obstacles();
    }

    @Test
    public void testPerimeterGeneration() {

        obstacles.generatePerimeter(WIDTH, HEIGHT);
        int totalObstacles = WIDTH * 2 + HEIGHT * 2 + 4;

        int[] xCoordinates = obstacles.getXCoordinates();
        int[] yCoordinates = obstacles.getYCoordinates();

        assertEquals("There should be a total of " + totalObstacles + " x coordinates.", 0, xCoordinates[totalObstacles + 1]);
        assertEquals("There should be a total of " + totalObstacles + " y coordinates.", 0, yCoordinates[totalObstacles + 1]);
    }

    @Test
    public void testAddObstacle() {
        obstacles.addObstacle(CUSTOM_OBSTACLE_X, CUSTOM_OBSTACLE_Y);
        assertEquals("There should only be 1 obstacle.", 1, obstacles.getNumberOfObstacles());
    }

    @Test
    public void testGenerateObstacle() {
        obstacles.generateObstacle(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
        assertEquals("There should only be 1 randomly generated obstacle.", 1, obstacles.getNumberOfObstacles());
    }

    @Test
    public void testAddingObstacleTwice() {
        obstacles.addObstacle(CUSTOM_OBSTACLE_X, CUSTOM_OBSTACLE_Y);
        assertFalse("Duplicate obstacle should not be added", obstacles.addObstacle(CUSTOM_OBSTACLE_X, CUSTOM_OBSTACLE_Y));
        assertTrue("Non-duplicate obstacle should be added.", obstacles.addObstacle(CUSTOM_OBSTACLE_X + 10, CUSTOM_OBSTACLE_Y + 10));
    }

    @After
    public void tearDown() {
        obstacles = null;
    }

}
