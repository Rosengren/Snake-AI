package snake;

public class Apple {

    private static final int SEED_VALUE = 29;
    private static final int DEFAULT_APPLE_SIZE = 10;

    private int size;

    private int xCoordinate;
    private int yCoordinate;

    public Apple(int size) {
        this.size = size;
        generateNewCoordinates();
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
}
