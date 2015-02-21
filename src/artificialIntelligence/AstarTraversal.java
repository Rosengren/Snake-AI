package artificialIntelligence;

import snake.*;

import java.util.*;

public class AStarTraversal extends AbstractStrategy implements AIStrategy {

    private static final int DISTANCE_BETWEEN_SQUARES = 1;

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] start, int[] goal) {
        PriorityQueue<int[]> frontier = new PriorityQueue<int[]>();
        frontier.enqueue(start, 0);


        Map<String, int[]> came_from = new HashMap<String, int[]>();
        Map<String, Integer> cost_so_far = new HashMap<String, Integer>();

        came_from.put(coordinatesToString(start), start);
        cost_so_far.put(coordinatesToString(start), 0);

        int new_cost;
        int priority;
        int[] current = new int[] {0, 0};
        while (!frontier.isEmpty()) {
            current = frontier.dequeue();
            printBoard(boardLayout);
            if (isAppleCoordinates(boardLayout, current)) {
                break;
            }

            for (int[] neighbor : getNeighbors(current, boardLayout)) {
                new_cost = cost_so_far.get(coordinatesToString(current)) + DISTANCE_BETWEEN_SQUARES;
                if (notVisited(boardLayout, neighbor) && notObstacle(boardLayout, neighbor) && notSnake(boardLayout, neighbor)) {
                    if (!cost_so_far.containsKey(coordinatesToString(neighbor)) || new_cost < cost_so_far.get(coordinatesToString(neighbor))) {
                        cost_so_far.put(coordinatesToString(neighbor), new_cost);
                        priority = new_cost + heuristic(current, goal);
                        frontier.enqueue(neighbor, priority);
                        came_from.put(coordinatesToString(neighbor), current);
                    }
                }
            }
            setVisited(boardLayout, current);
        }

        ArrayList<int[]> shortestPath = reconstructShortestPath(current, start, came_from);

        return getDirectionPath(shortestPath);
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


    // TODO: create separate object for heuristics maybe (strategy pattern)
    private int heuristic(int[] a, int[] b) {
        // Manhattan distance on a square grid
        return Math.abs(a[X] - b[X]) + Math.abs(a[Y] - b[Y]);
    }

}


