package snakeTestSuite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import snake.Obstacles;

import org.junit.*;

public class TestObstacles {

    private static final int DEFAULT_OBSTACLE_SIZE = 10;
    private static final int CUSTOM_OBSTACLE_SIZE = 20;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private static final int CUSTOM_OBSTACLE_X = 10;
    private static final int CUSTOM_OBSTACLE_Y = 20;

    private Obstacles obstacles;

    @Before
    public void setUp() {
        obstacles = new Obstacles();
    }

    @Test
    public void testObstacleSize() {

        assertEquals("Obstacle should have default size: " + DEFAULT_OBSTACLE_SIZE + ".", DEFAULT_OBSTACLE_SIZE, obstacles.getPerimeterSize());
        obstacles = new Obstacles(CUSTOM_OBSTACLE_SIZE);
        assertEquals("Obstacle should have custom size: " + CUSTOM_OBSTACLE_SIZE + ".", CUSTOM_OBSTACLE_SIZE, obstacles.getPerimeterSize());
    }

    @Test
    public void testPerimeterGeneration() {

        obstacles.generatePerimeter(WIDTH, HEIGHT);
        int totalObstacles = WIDTH / DEFAULT_OBSTACLE_SIZE * 2 + HEIGHT / DEFAULT_OBSTACLE_SIZE * 2 + 4;

        int[] xCoordinates = obstacles.getXCoordinates();
        int[] yCoordinates = obstacles.getYCoordinates();

        assertEquals("There should be a total of " + totalObstacles + " coordinates.", 0, xCoordinates[totalObstacles + 1]);
        assertEquals("There should be a total of " + totalObstacles + " y coordinates.", 0, yCoordinates[totalObstacles + 1]);
    }

    @Test
    public void testAddObstacle() {
        obstacles.addObstacle(CUSTOM_OBSTACLE_X, CUSTOM_OBSTACLE_Y);
        assertEquals("There should only be 1 obstacle.", 1, obstacles.getNumberOfObstacles());
    }

    @Test
    public void testGenerateObstacle() {
        obstacles.generateObstacle();
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
