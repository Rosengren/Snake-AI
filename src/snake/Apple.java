package snake;

public class Apple {

    private static final int SEED_VALUE = 29;

    private int xCoordinate;
    private int yCoordinate;

    private int height;
    private int width;

    public Apple() {
        generateNewCoordinates();
        height = -1;
        width = -1;
    }

    public void setCoordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }


    // TODO: make sure apple is not behind an obstacle
    public void generateNewCoordinates() {

        do {
            xCoordinate = generateCoordinate();
            yCoordinate = generateCoordinate();
        } while (isOutsidePerimeter(xCoordinate, yCoordinate));
    }


    private int generateCoordinate() {
        return (int) (Math.random() * SEED_VALUE);
    }


    private boolean isOutsidePerimeter(int x, int y) {
        return (x == 0 || x == width || y == 0 || y == height);
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


    public void setPerimeter(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int[] getPerimeter() {
        return new int[] {width, height};
    }
}
