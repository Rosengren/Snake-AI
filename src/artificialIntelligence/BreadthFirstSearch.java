package artificialIntelligence;

import snake.*;

import java.util.*;

public class BreadthFirstSearch implements AIStrategy {

    private static final int VISITED = -1;
    private static final int SNAKE = 1;
    private static final int PERIMETER = 2;
    private static final int APPLE = 3;

    // TODO: handle directions that the snake cannot move in

    @Override
    public Direction[] getPath(int[][] boardLayout, Direction direction, int[] snakeHead) {

        Queue<int[]> frontier = new LinkedList<int[]>();
        frontier.add(snakeHead);

        ArrayList<int[]> came_from = new ArrayList<int[]>();
        came_from.add(snakeHead);


        int[] current = new int[] {0, 0};
        while (!frontier.isEmpty()) {
            current = frontier.remove();
            if (isAppleCoordinates(boardLayout, current)) {
                break;
            }

            for (int[] neighbor : getNeighbors(current, boardLayout)) {
                if (notVisited(boardLayout, neighbor) && notObstacle(boardLayout, neighbor)) {
                    frontier.add(neighbor);
                    came_from.add(neighbor);
                }
            }
            setVisited(boardLayout, current);
        }

        System.out.println("Found Apple at " + current[0] + ", " + current[1] + " " + boardLayout[current[0]][current[1]]);
        return null;
    }

    private void printBoard(int[][] boardLayout) {
        String result = "";
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[0].length; j++) {
                result += boardLayout[i][j] + " ";
            }
            result += "\n";
        }

        System.out.println(result);
    }

    private boolean notVisited(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != VISITED;
    }

    private void setVisited(int[][] boardLayout, int[] coordinates) {
        boardLayout[coordinates[0]][coordinates[1]] = VISITED;
    }

    private boolean notObstacle(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != PERIMETER;
    }

    private boolean isAppleCoordinates(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] == APPLE;
    }


    private ArrayList<int[]> reconstructShortestPath(int[] goal, int[] start) {
        int[] current = goal;

        ArrayList<int[]> path = new ArrayList<int[]>();

        while (current[0] != start[0] || current[1] != start[1]) {
            path.add(current);
        }

        return path;
    }

    private ArrayList<Direction> getDirectionPath(ArrayList<int[]> path) {

        ArrayList<Direction> directionPath = new ArrayList<Direction>();

        int[] previous = path.get(0);

        int[] current;

        for (int i = 1; i < path.size(); i++) {
            current = path.get(i);

            if (current[0] > previous[0]) {
                directionPath.add(Direction.RIGHT);
            } else if (current[0] < previous[0]) {
                directionPath.add(Direction.LEFT);
            } else if (current[1] > previous[1]) {
                directionPath.add(Direction.DOWN);
            } else if (current[1] < previous[1]) {
                directionPath.add(Direction.UP);
            }
        }

        return directionPath;
    }

    private ArrayList<int[]> getNeighbors(int[] current, int[][] board) {


        ArrayList<int[]> result = new ArrayList<int[]>();

        if (current[0] != 0) {
            result.add(new int[]{current[0] - 1, current[1]});
        }

        if (current[0] != board.length - 1) {
            result.add(new int[]{current[0] + 1, current[1]});
        }

        if (current[1] != 0) {
            result.add(new int[]{current[0], current[1] - 1});
        }

        if (current[1] != board[0].length) {
            result.add(new int[]{current[0], current[1] + 1});
        }

        return result;
    }
}