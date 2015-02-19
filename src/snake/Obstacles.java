package snake;

import java.util.Arrays;

public class Obstacles {

    private static final int MAX_OBSTACLES = 400;
    private static final int SEED_VALUE = 37;

    private int[] xCoordinates;
    private int[] yCoordinates;

    private int numberOfObstacles;

    public Obstacles() {
        xCoordinates = new int[MAX_OBSTACLES];
        yCoordinates = new int[MAX_OBSTACLES];

        numberOfObstacles = 0;
    }

    public void generatePerimeter(int width, int height) {

        int horizontalObstacles = width;
        int verticalObstacles = height;

        generateHorizontalPerimeter(horizontalObstacles, height);
        generateVerticalPerimeter(horizontalObstacles, verticalObstacles, width);

        numberOfObstacles = 2 * verticalObstacles + 2 * horizontalObstacles;
    }


    private void generateHorizontalPerimeter(int horizontalObstacles, int height) {

        for (int i = 0; i <= horizontalObstacles - 1; i++) {
            xCoordinates[i] = i;
            xCoordinates[i + horizontalObstacles] = i;
            yCoordinates[i] = 0;
            yCoordinates[i + horizontalObstacles] = height - 1;
        }
    }


    private void generateVerticalPerimeter(int numberOfObstacles, int verticalObstacles, int width) {

        for (int i = 0; i <= verticalObstacles - 1; i++) {
            xCoordinates[i + 2 * numberOfObstacles] = 0;
            xCoordinates[i + 2 * numberOfObstacles + verticalObstacles] = width - 1;
            yCoordinates[i + 2 * numberOfObstacles] = i;
            yCoordinates[i + 2 * numberOfObstacles + verticalObstacles] = i;
        }
    }


    public void generateObstacle() {
        int x = (int)(Math.random() * SEED_VALUE);
        int y = (int)(Math.random() * SEED_VALUE);

        if (!addObstacle(x, y)) {
            generateObstacle();
        }
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

        if (coordinate.length < 2)
            return false;

        for (int i = 0; i < numberOfObstacles; i++) {
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

    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }

}
