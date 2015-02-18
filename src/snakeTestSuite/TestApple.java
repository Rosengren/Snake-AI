package snakeTestSuite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import org.junit.*;

import snake.Apple;

public class TestApple {

    private static final int X_COORDINATE = 10;
    private static final int Y_COORDINATE = 10;

    private static final int DEFAULT_PERIMETER_HEIGHT = -1;
    private static final int DEFAULT_PERIMETER_WIDTH = -1;


    private Apple apple;

    @Before
    public void setUp() {
        apple = new Apple();
    }

    @Test
    public void testConstructors() {

        apple = new Apple();
        int[] perimeter = apple.getPerimeter();
        assertEquals("The default perimeter width should be " + DEFAULT_PERIMETER_WIDTH, DEFAULT_PERIMETER_WIDTH, perimeter[0]);
        assertEquals("The default perimeter height should be " + DEFAULT_PERIMETER_HEIGHT, DEFAULT_PERIMETER_HEIGHT, perimeter[1]);

    }

    @Test
    public void testCoordinates() {

        assertNotNull("The apple x coordinate should not be null", apple.getXCoordinate());
        assertNotNull("The apple y coordinate should not be null", apple.getYCoordinate());

        apple.setCoordinates(X_COORDINATE, Y_COORDINATE);
        assertEquals("The apple x coordinate should be set to " + X_COORDINATE + ".", X_COORDINATE, apple.getXCoordinate());
        assertEquals("The apple y coordinate should be set to " + Y_COORDINATE + ",", Y_COORDINATE, apple.getYCoordinate());

    }

    @Test
    public void testAppleCollision() {

        apple.setCoordinates(X_COORDINATE, Y_COORDINATE);

        assertTrue("Apple should collide at " + X_COORDINATE + "," + Y_COORDINATE + ".", apple.checkCollision(X_COORDINATE, Y_COORDINATE));
        assertFalse("Apple should not collide at " + (X_COORDINATE - 1) + "," + (Y_COORDINATE - 1) + ".", apple.checkCollision(X_COORDINATE - 1, Y_COORDINATE - 1));
    }

    @After
    public void tearDown() {
        apple = null;
    }

}
