package snakeTestSuite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import org.junit.*;

import snake.Apple;

public class TestApple {

    private static final int DEFAULT_APPLE_SIZE = 10;
    private static final int CUSTOM_APPLE_SIZE = 20;

    private static final int X_COORDINATE = 10;
    private static final int Y_COORDINATE = 10;

    private Apple apple;

    @Before
    public void setUp() {
        apple = new Apple();
    }

    @Test
    public void testConstructors() {

        apple = new Apple();
        assertEquals("The default apple size should be " + DEFAULT_APPLE_SIZE + ".", DEFAULT_APPLE_SIZE, apple.getSize());

        apple = new Apple(CUSTOM_APPLE_SIZE);
        assertEquals("The apple size should be " + CUSTOM_APPLE_SIZE + ".", CUSTOM_APPLE_SIZE, apple.getSize());
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
