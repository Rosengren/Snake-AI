package snake;

public class Apple {

    private static final int SEED_VALUE = 29;

    private int xCoordinate;
    private int yCoordinate;

    private int height;
    private int width;

    public Apple() {
        height = -1;
        width = -1;
    }

    public void setCoordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }


    public void generateNewCoordinates(int[] xObstacles, int[] yObstacles) {

        do {
            xCoordinate = generateCoordinate();
            yCoordinate = generateCoordinate();
        } while (isBehindObstacle(xCoordinate, yCoordinate, xObstacles, yObstacles));
    }


    private int generateCoordinate() {
        return (int) (Math.random() * SEED_VALUE);
    }


    private boolean isBehindObstacle(int x, int y, int[] xObstacles, int[] yObstacles) {
        if (xObstacles.length != yObstacles.length) return false;
        for (int i = 0; i < xObstacles.length; i++)
            if (x == xObstacles[i] && y == yObstacles[i])
                return true;
        return false;
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
