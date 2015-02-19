package artificialIntelligence;

import snake.*;

import java.util.*;

public class BreadthFirstSearch implements AIStrategy {

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] snakeHead) {

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
                if (notVisited(boardLayout, neighbor) && notObstacle(boardLayout, neighbor) && notSnake(boardLayout, neighbor)) {
                    if (!came_from.containsKey(coordinatesToString(neighbor))) {
                        frontier.add(neighbor);
                        came_from.put(coordinatesToString(neighbor), current);
                    }
                }
            }
            setVisited(boardLayout, current);
        }

        ArrayList<int[]> shortestPath = reconstructShortestPath(current, snakeHead, came_from);

        return getDirectionPath(shortestPath);

    }

    private String coordinatesToString(int[] coordinates) {
        return coordinates[0] + "," + coordinates[1];
    }

    private boolean notSnake(int[][] boardLayout, int[] coordinates) {
        return boardLayout[coordinates[0]][coordinates[1]] != SNAKE;
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
            path.add(0, current);
        }

        return path;
    }

    private Direction[] getDirectionPath(ArrayList<int[]> path) {

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