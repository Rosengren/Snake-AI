package artificialIntelligence;

import snake.*;

import java.util.List;
import java.util.Stack;

public class AbstractStrategy {

    protected static final int VISITED  = -1;

    /** Coordinates **/
    private static final int X = 0;
    private static final int Y = 1;


    protected Direction getDirection(int[] current, int[] previous) {

        if (current[X] > previous[X])
            return Direction.RIGHT;
        if (current[X] < previous[X])
            return Direction.LEFT;
        if (current[Y] > previous[Y])
            return Direction.DOWN;

        return Direction.UP;
    }

    protected Direction[] getDirectionPath(List<int[]> path) {

        if (path.size() < 1) {
            return null;
        }

        Direction[] directionPath = new Direction[path.size() - 1];

        int[] previous = path.get(0);
        int[] current;

        for (int i = 1; i < path.size(); i++) {
            current = path.get(i);

            directionPath[i - 1] = getDirection(current, previous);
            previous = path.get(i);
        }

        return directionPath;
    }


    protected Stack<int[]> getNeighbors(int[] current, int[][] board) {

        Stack<int[]> result = new Stack<int[]>();

        if (current[0] != board.length - 1)
            result.push(new int[]{current[X] + 1, current[Y]}); // RIGHT

        if (current[1] != board[0].length)
            result.push(new int[]{current[X], current[Y] + 1}); // DOWN

        if (current[0] != 0)
            result.push(new int[]{current[X] - 1, current[Y]}); // LEFT

        if (current[1] != 0)
            result.push(new int[]{current[X], current[Y] - 1}); // UP

        return result;
    }

    protected boolean notVisited(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[X]][coordinates[Y]] != VISITED;
    }

    protected void setVisited(int[][] boardLayout, int[] coordinates) {
        boardLayout[coordinates[X]][coordinates[Y]] = VISITED;
    }

    protected boolean notSnake(int[][] board, int[] coordinates) {
        return board[coordinates[X]][coordinates[Y]] != Settings.SNAKE;
    }


    protected boolean notObstacle(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[X]][coordinates[Y]] != Settings.OBSTACLE;
    }

    protected boolean isAppleCoordinates(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[X]][coordinates[Y]] == Settings.APPLE;
    }

    protected String coordinatesToString(int[] coordinates) {
        return coordinates[X] + "," + coordinates[Y];
    }

}
