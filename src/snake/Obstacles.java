package snake;

public class Obstacles {

    private static final int DEFAULT_OBSTACLE_SIZE = 10;
    private static final int MAX_OBSTACLES = 400;
    private static final int SEED_VALUE = 37;

    private int[] xCoordinates;
    private int[] yCoordinates;

    private int numOfObstacles;

    private int perimeterSize;

    public Obstacles() {
        this(DEFAULT_OBSTACLE_SIZE);
    }

    public Obstacles(int size) {
        xCoordinates = new int[MAX_OBSTACLES];
        yCoordinates = new int[MAX_OBSTACLES];

        numOfObstacles = 0;
        this.perimeterSize = size;
    }

    public void generatePerimeter(int width, int height) {

        int horizontalObstacles = width / perimeterSize + 1;
        int verticalObstacles = height / perimeterSize + 1;

        generateHorizontalPerimeter(horizontalObstacles, height);
        generateVerticalPerimeter(horizontalObstacles, verticalObstacles, width);

        numOfObstacles = 2 * verticalObstacles + 2 * horizontalObstacles;
    }


    private void generateHorizontalPerimeter(int horizontalObstacles, int height) {

        for (int i = 0; i <= horizontalObstacles; i++) {
            xCoordinates[i] = i * perimeterSize;
            xCoordinates[i + horizontalObstacles] = i * perimeterSize;
            yCoordinates[i] = 0;
            yCoordinates[i + horizontalObstacles] = height + perimeterSize;
        }
    }


    private void generateVerticalPerimeter(int numberOfObstacles, int verticalObstacles, int width) {

        for (int i = 0; i <= verticalObstacles; i++) {
            xCoordinates[i + 2 * numberOfObstacles] = 0;
            xCoordinates[i + 2 * numberOfObstacles + verticalObstacles] = width + perimeterSize;
            yCoordinates[i + 2 * numberOfObstacles] = i * perimeterSize;
            yCoordinates[i + 2 * numberOfObstacles + verticalObstacles] = i * perimeterSize;
        }
    }


    public void generateObstacle() {
        int x = (int)(Math.random() * SEED_VALUE) * perimeterSize;
        int y = (int)(Math.random() * SEED_VALUE) * perimeterSize;

        if (!addObstacle(x, y)) {
            generateObstacle();
        }
    }

    public boolean addObstacle(int x, int y) {
        for (int i = 0; i < numOfObstacles; i++)
            if (xCoordinates[i] == x && yCoordinates[i] == y)
                return false;

        xCoordinates[numOfObstacles] = x;
        yCoordinates[numOfObstacles] = y;
        numOfObstacles++;
        return true;
    }

    public boolean checkCollision(int[] coordinate) {

        if (coordinate.length < 2)
            return false;

        for (int i = 0; i < perimeterSize; i++) {
            if (xCoordinates[i] == coordinate[0] && yCoordinates[i] == coordinate[1])
                return true;
        }

        return false;
    }

    public int[] getXCoordinates() {
        return xCoordinates;
    }

    public int[] getYCoordinates() {
        return yCoordinates;
    }

    public int getPerimeterSize() {
        return perimeterSize;
    }

    public int getNumberOfObstacles() {
        return numOfObstacles;
    }

}
