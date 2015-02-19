package artificialIntelligence;

import snake.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch implements AIStrategy {

    private List<int[]> path;

    public DepthFirstSearch() {
        path = new ArrayList<int[]>();

    }

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] snakeHead) {
        boolean[][] visited = new boolean[boardLayout.length][boardLayout[0].length];
        depthFirstSearch(boardLayout, visited, snakeHead);
        path.add(0, snakeHead); // append head
        return getDirectionPath(path);
    }


    private boolean depthFirstSearch(int[][] board, boolean[][] visited, int[] current) {
        visited[current[0]][current[1]] = true;

        if (isAppleCoordinates(board, current)) return true;

        for (int[] neighbor : getNeighbors(current, board)) {
            if (notVisited(visited, neighbor) && notSnake(board, neighbor) && notObstacle(board, neighbor)) {
                if (depthFirstSearch(board, visited, neighbor)) {
                    path.add(0, neighbor);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean notVisited(boolean[][] visited, int[] coordinates) {
        return !visited[coordinates[0]][coordinates[1]];
    }


    private boolean notSnake(int[][] board, int[] coordinates) {
        return board[coordinates[0]][coordinates[1]] != SNAKE;
    }


    private boolean notObstacle(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != OBSTACLE;
    }


    private boolean isAppleCoordinates(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] == APPLE;
    }


    private Stack<int[]> getNeighbors(int[] current, int[][] board) {


        Stack<int[]> result = new Stack<int[]>();


        if (current[0] != board.length - 1)
            result.push(new int[]{current[0] + 1, current[1]}); // RIGHT


        if (current[1] != board[0].length)
            result.push(new int[]{current[0], current[1] + 1}); // DOWN

        if (current[0] != 0)
            result.push(new int[]{current[0] - 1, current[1]}); // LEFT

        if (current[1] != 0)
            result.push(new int[]{current[0], current[1] - 1}); // UP

        return result;
    }


    private Direction[] getDirectionPath(List<int[]> path) {

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

    private Direction getDirection(int[] current, int[] previous) {

        if (current[0] > previous[0]) {
            return Direction.RIGHT;
        }

        else if (current[0] < previous[0])
            return Direction.LEFT;
        else if (current[1] > previous[1])
            return Direction.DOWN;

        return Direction.UP;
    }

}