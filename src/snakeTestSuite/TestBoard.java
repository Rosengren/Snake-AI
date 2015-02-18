package snakeTestSuite;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

import org.junit.*;

import snake.Board;

public class TestBoard {

    private Board board;

    private final int DEFAULT_BOARD_WIDTH = 50;
    private final int DEFAULT_BOARD_HEIGHT = 50;
    private final int CUSTOM_BOARD_WIDTH = 100;
    private final int CUSTOM_BOARD_HEIGHT = 100;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardConstructors() {

        // test default board
        board = new Board();
        assertEquals("Default Board width should be " + DEFAULT_BOARD_WIDTH + ".", DEFAULT_BOARD_WIDTH, board.getWidth());
        assertEquals("Default Board height should be " + DEFAULT_BOARD_HEIGHT + ".", DEFAULT_BOARD_HEIGHT, board.getHeight());

        // test custom board
        board = new Board(CUSTOM_BOARD_WIDTH, CUSTOM_BOARD_HEIGHT);
        assertEquals("Custom Board width should be " + CUSTOM_BOARD_WIDTH + ".", CUSTOM_BOARD_WIDTH, board.getWidth());
        assertEquals("Custom Board height should be " + CUSTOM_BOARD_HEIGHT + ".", CUSTOM_BOARD_HEIGHT, board.getHeight());

    }

    @After
    public void tearDown() {
        board = null;
    }
}
