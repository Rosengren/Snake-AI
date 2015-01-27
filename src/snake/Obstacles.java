package snake;

public class Obstacles {

    private static final int DEFAULT_OBSTACLE_SIZE = 10;
    private static final int MAX_OBSTACLES = 400;
    private static final int SEED_VALUE = 37;

    private int[] xCoordinates;
    private int[] yCoordinates;

    private int numOfObstacles;

    private int size;

    public Obstacles() {
        this(DEFAULT_OBSTACLE_SIZE);
    }

    public Obstacles(int size) {
        xCoordinates = new int[MAX_OBSTACLES];
        yCoordinates = new int[MAX_OBSTACLES];

        numOfObstacles = 0;
        this.size = size;
    }

    public void generatePerimeter(int width, int height) {

        int horizontalObstacles = width / size + 1;
        int verticalObstacles = height / size + 1;

        for (int i = 0; i <= horizontalObstacles; i++) {
            xCoordinates[i] = i * size;
            xCoordinates[i + horizontalObstacles] = i * size;
            yCoordinates[i] = 0;
            yCoordinates[i + horizontalObstacles] = height + size;
        }

        for (int i = 0; i <= verticalObstacles; i++) {
            xCoordinates[i + 2 * horizontalObstacles] = 0;
            xCoordinates[i + 2 * horizontalObstacles + verticalObstacles] = width + size;
            yCoordinates[i + 2 * horizontalObstacles] = i * size;
            yCoordinates[i + 2 * horizontalObstacles + verticalObstacles] = i * size;
        }

        numOfObstacles = 2 * verticalObstacles + 2 * horizontalObstacles;
    }

    public void generateObstacle() {
        int x = (int)(Math.random() * SEED_VALUE) * size;
        int y = (int)(Math.random() * SEED_VALUE) * size;

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

    public int[] getXCoordinates() {
        return xCoordinates;
    }

    public int[] getYCoordinates() {
        return yCoordinates;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfObstacles() {
        return numOfObstacles;
    }

}
