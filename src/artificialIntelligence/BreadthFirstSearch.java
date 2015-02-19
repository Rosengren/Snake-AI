package artificialIntelligence;

import snake.*;

import java.util.*;

public class BreadthFirstSearch implements AIStrategy {

    private static final int VISITED = -1;
    private static final int SNAKE = 1;
    private static final int OBSTACLE = 2;
    private static final int APPLE = 3;

    // TODO: handle directions that the snake cannot move in

    @Override
    public Direction[] getPath(int[][] boardLayout, Direction direction, int[] snakeHead) {

        Queue<int[]> frontier = new LinkedList<int[]>();
        frontier.add(snakeHead);

        Map<String, int[]> came_from = new HashMap<String, int[]>();
        came_from.put(coordinatesToString(snakeHead), snakeHead);

        int[] current = new int[] {0, 0};
        while (!frontier.isEmpty()) {
            current = frontier.remove();
            if (isAppleCoordinates(boardLayout, current)) {
                break;
            }

            for (int[] neighbor : getNeighbors(current, boardLayout)) {
                if (notVisited(boardLayout, neighbor) && notObstacle(boardLayout, neighbor)) {
                    if (!came_from.containsKey(coordinatesToString(neighbor))) {
                        frontier.add(neighbor);
                        came_from.put(coordinatesToString(neighbor), current);
                    }
                }
            }
            setVisited(boardLayout, current);
        }

        System.out.println("Found Apple at " + current[0] + ", " + current[1] + " " + boardLayout[current[0]][current[1]]);
        ArrayList<int[]> shortestPath = reconstructShortestPath(current, snakeHead, came_from);

        String result = "";
        for (int i = 0; i < shortestPath.size(); i++) {
            int[] p = shortestPath.get(i);
            result += "[" + p[0] + "," + p[1] + "] ";
        }


        System.out.println("Shortest Path: " + result);

        printDirections(getDirectionPath(shortestPath));

        return getDirectionPath(shortestPath);

    }

    private void printDirections(Direction[] dir) {
        String result = "";
        for (int i = 0; i < dir.length; i++) {
            result += dir[i] + " ";
        }

        System.out.println("Directions: " + result);
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

    private String coordinatesToString(int[] coordinates) {
        return coordinates[0] + "," + coordinates[1];
    }

    private boolean notVisited(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != VISITED;
    }

    private void setVisited(int[][] boardLayout, int[] coordinates) {
        boardLayout[coordinates[0]][coordinates[1]] = VISITED;
    }

    private boolean notObstacle(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != OBSTACLE;
    }

    private boolean isAppleCoordinates(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] == APPLE;
    }


    private ArrayList<int[]> reconstructShortestPath(int[] goal, int[] start, Map came_from) {
        int[] current = goal;

        ArrayList<int[]> path = new ArrayList<int[]>();
        path.add(goal);

        while (current[0] != start[0] || current[1] != start[1]) {
            current = (int[])came_from.get(coordinatesToString(current));
            path.add(current);
        }

        return path;
    }

    private Direction[] getDirectionPath(ArrayList<int[]> path) {

        Direction[] directionPath = new Direction[path.size() - 1];

        int[] previous = path.get(0);

        int[] current;

        for (int i = 1; i < path.size(); i++) {
            current = path.get(i);

            if (current[0] > previous[0]) {
                directionPath[i - 1] = Direction.LEFT;
            } else if (current[0] < previous[0]) {
                directionPath[i - 1] = Direction.RIGHT;
            } else if (current[1] > previous[1]) {
                directionPath[i - 1] = Direction.UP;
            } else if (current[1] < previous[1]) {
                directionPath[i - 1] = Direction.DOWN;
            }
        }

        return directionPath;
    }

    private ArrayList<int[]> getNeighbors(int[] current, int[][] board) {


        ArrayList<int[]> result = new ArrayList<int[]>();

        if (current[0] != 0)
            result.add(new int[]{current[0] - 1, current[1]});

        if (current[0] != board.length - 1)
            result.add(new int[]{current[0] + 1, current[1]});

        if (current[1] != 0)
            result.add(new int[]{current[0], current[1] - 1});

        if (current[1] != board[0].length)
            result.add(new int[]{current[0], current[1] + 1});

        return result;
    }
}