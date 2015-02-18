package artificialIntelligence;

import snake.*;

import java.util.*;

public class BreadthFirstSearch implements AIStrategy {

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
                for (int[] neighbor : getNeighbors(current, boardLayout)) {
//                if (!came_from.contains(neighbor)) { // not visited
//                System.out.println("Checking " + neighbor[0] + "," + neighbor[1]);
                    if (doesNotContain(came_from, neighbor)) {
                        frontier.add(neighbor);
                        came_from.add(neighbor);
//                    System.out.println("Visited: " + neighbor[0] + "," + neighbor[1]);
//                    System.out.println("came_from length: " + came_from.size());
                    }
                }
        }

        System.out.println("Found Apple at " + current[0] + ", " + current[1]);
        return null;
    }

    private boolean doesNotContain(ArrayList<int[]> arr, int[] value) {
        for (int i = 0; i < arr.size(); i++) {
            int[] val = arr.get(i);
            if (value[0] == val[0] && value[1] == val[1]) {
                return false;
            }
        }

        return true;
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