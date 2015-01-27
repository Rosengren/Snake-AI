package snake;

import java.util.ArrayList;

public class Snake {

    private static final int DEFAULT_LENGTH = 5;
    private static final int DOT_SIZE = 10;
    private static final int DEFAULT_STARTING_POINT = 200;

    private int length;

    private ArrayList<Integer> xCoordinates;
    private ArrayList<Integer> yCoordinates;

    private Direction direction;

    public Snake(int size) {
        this.length = size;

        direction = null; // initially not moving
        initialize();

    }

    public Snake() {
        this(DEFAULT_LENGTH);
    }

    public void setDirection(Direction dir) {

        if ((dir == Direction.UP) && (direction != Direction.DOWN))
            direction = Direction.UP;

        else if ((dir == Direction.DOWN) && (direction != Direction.UP))
            direction = Direction.DOWN;

        else if ((dir == Direction.LEFT) && (direction != Direction.RIGHT))
            direction = Direction.LEFT;

        else if ((dir == Direction.RIGHT) && (direction != Direction.LEFT))
            direction = Direction.RIGHT;
    }

    public void update() {

        if (direction == Direction.LEFT) {
            xCoordinates.add(0, xCoordinates.get(0) - DOT_SIZE);
            yCoordinates.add(0, yCoordinates.get(0));
        } else if (direction == Direction.RIGHT) {
            xCoordinates.add(0, xCoordinates.get(0) + DOT_SIZE);
            yCoordinates.add(0, yCoordinates.get(0));
        } else if (direction == Direction.UP) {
            xCoordinates.add(0, xCoordinates.get(0));
            yCoordinates.add(0, yCoordinates.get(0) - DOT_SIZE);
        } else if (direction == Direction.DOWN) {
            xCoordinates.add(0, xCoordinates.get(0));
            yCoordinates.add(0, yCoordinates.get(0) + DOT_SIZE);
        }

        if (direction != null) {
            xCoordinates.remove(xCoordinates.size() - 1);
            yCoordinates.remove(yCoordinates.size() - 1);
        }

    }

    public boolean checkWallCollision(int width, int height) {

        return ((yCoordinates.get(0) > height)
                || (yCoordinates.get(0) < 0)
                || (xCoordinates.get(0) > width)
                || (xCoordinates.get(0) < 0));

    }

    public boolean checkSnakeCollision() {
        for (int i = xCoordinates.size() - 1; i > 0; i--) {
            if ((i > 4) && (xCoordinates.get(0) == xCoordinates.get(i))
                    && (yCoordinates.get(0) == yCoordinates.get(i)))
                return true;
        }

        return false;
    }

    public boolean checkCollision(int x, int y) {
        return (x == xCoordinates.get(0) && y == yCoordinates.get(0));
    }


    public Direction getDirection() {
        return direction;
    }

    public void initialize() {

        xCoordinates = new ArrayList<Integer>();
        yCoordinates = new ArrayList<Integer>();

        for (int i = 0; i < length; i++) {
            xCoordinates.add(DEFAULT_STARTING_POINT - i * DOT_SIZE);
            yCoordinates.add(DEFAULT_STARTING_POINT);
        }
    }

    public int[] getXCoordinates() {
        return toIntegerArray(xCoordinates);
    }

    public int[] getYCoordinates() {
        return toIntegerArray(yCoordinates);
    }

    public int[] toIntegerArray(ArrayList<Integer> arr) {

        int[] result = new int[arr.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr.get(i);
        }

        return result;
    }

    public int getLength() {
        return length;
    }

}
