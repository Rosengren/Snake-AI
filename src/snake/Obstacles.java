package snake;

public class Obstacles {

    private static final int MAX_OBSTACLES = 1000;

    private int[] xCoordinates;
    private int[] yCoordinates;

    private int numberOfObstacles;

    public Obstacles() {
        xCoordinates = new int[MAX_OBSTACLES];
        yCoordinates = new int[MAX_OBSTACLES];

        numberOfObstacles = 0;
    }

    public void generatePerimeter(int width, int height) {

        generateHorizontalPerimeter(height, width);
        generateVerticalPerimeter(height, width);
        numberOfObstacles = 2 * height + 2 * width;
    }


    private void generateHorizontalPerimeter(int height, int width) {

        for (int i = 0; i <= width - 1; i++) {
            xCoordinates[i] = i;
            xCoordinates[i + width] = i;
            yCoordinates[i] = 0;
            yCoordinates[i + width] = height - 1;
        }
    }


    private void generateVerticalPerimeter(int height, int width) {

        for (int i = 0; i <= height - 1; i++) {
            xCoordinates[i + 2 * width] = 0;
            xCoordinates[i + 2 * width + height] = width - 1;
            yCoordinates[i + 2 * width] = i;
            yCoordinates[i + 2 * width + height] = i;
        }
    }


    public void generateObstacle(int width, int height) {

        int x = (int)(Math.random() * width);
        int y = (int)(Math.random() * height);

        if (!addObstacle(x, y))
            generateObstacle(width, height);

        numberOfObstacles++;
    }

    public boolean addObstacle(int x, int y) {

        for (int i = 0; i < numberOfObstacles; i++)
            if (xCoordinates[i] == x && yCoordinates[i] == y)
                return false;

        xCoordinates[numberOfObstacles] = x;
        yCoordinates[numberOfObstacles] = y;
        numberOfObstacles++;
        return true;
    }

    public boolean checkCollision(int[] coordinate) {

        if (coordinate.length < 2) return false;

        for (int i = 0; i < numberOfObstacles; i++)
            if (xCoordinates[i] == coordinate[0] && yCoordinates[i] == coordinate[1])
                return true;

        return false;
    }

    public int[] getXCoordinates() {
        return xCoordinates;
    }

    public int[] getYCoordinates() {
        return yCoordinates;
    }

    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }

}
