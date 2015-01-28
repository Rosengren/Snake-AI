package snake;

public class Apple {

    private static final int SEED_VALUE = 29;
    private static final int DEFAULT_APPLE_SIZE = 10;

    private int size;

    private int xCoordinate;
    private int yCoordinate;

    private int height;
    private int width;

    public Apple(int size) {
        this.size = size;
        generateNewCoordinates();
        height = -1;
        width = -1;
    }

    public Apple() {
        this(DEFAULT_APPLE_SIZE);
    }

    public void setCoordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    // TODO: make sure apple is not behind an obstacle
    public void generateNewCoordinates() {
        int r = (int) (Math.random() * SEED_VALUE);
        xCoordinate = r * size;
        r = (int) (Math.random() * SEED_VALUE);
        yCoordinate = r * size;

        if (xCoordinate == 0 || xCoordinate == width ||
                yCoordinate == 0 || yCoordinate == height) {
            generateNewCoordinates();
        }
    }

    public boolean checkCollision(int x, int y) {
        return x == xCoordinate && y == yCoordinate;
    }

    public int[] getCoordinates() {
        return new int[] {xCoordinate, yCoordinate};
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int getSize() {
        return size;
    }

    public void setPerimeter(int width, int height) {
        this.height = height;
        this.width = width;
    }
}