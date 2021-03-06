package artificialIntelligence;

import snake.*;

import java.util.*;

public class AStarTraversal extends AbstractStrategy implements AIStrategy {

    private static final int DISTANCE_BETWEEN_SQUARES = 1;
    private Heuristic heuristic;

    public AStarTraversal(int selectedHeuristic) {
        heuristic = new Heuristic(selectedHeuristic);
    }

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] start, int[] goal) {
        PriorityQueue<int[]> frontier = new PriorityQueue<int[]>();
        frontier.enqueue(start, 0);


        Map<String, int[]> came_from = new HashMap<String, int[]>();
        Map<String, Integer> cost_so_far = new HashMap<String, Integer>();

        came_from.put(coordinatesToString(start), start);
        cost_so_far.put(coordinatesToString(start), 0);

        int new_cost;
        double priority;
        int[] current = new int[] {0, 0};
        while (!frontier.isEmpty()) {
            current = frontier.dequeue();
            printBoard(boardLayout);
            if (isAppleCoordinates(boardLayout, current)) {
                break;
            }

            for (int[] neighbor : getNeighbors(current, boardLayout)) {
                new_cost = cost_so_far.get(coordinatesToString(current)) + DISTANCE_BETWEEN_SQUARES;
                if (validMove(boardLayout, boardLayout, neighbor)) {
                    if (!cost_so_far.containsKey(coordinatesToString(neighbor)) || new_cost < cost_so_far.get(coordinatesToString(neighbor))) {
                        cost_so_far.put(coordinatesToString(neighbor), new_cost);
                        priority = new_cost + heuristic.calculate(current, goal);
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


    private class Heuristic {
        
        private int selectedHeuristic;


        public Heuristic(int heuristic) {
            selectedHeuristic = heuristic;
        }


        public double calculate(int[] start, int[] goal) {
            switch (selectedHeuristic) {
                case Settings.MANHATTAN_DISTANCE:
                    return manhattanDistance(start, goal);
                case Settings.TIE_BREAKER_MANHATTAN_DISTANCE:
                    return tieBreakerManhattanDistance(start, goal);
                case Settings.DIAGONAL_DISTANCE_HEURISTIC:
                    return diagonalDistance(start, goal);
                case Settings.AVERAGE_HEURISTIC:
                    return average(start, goal);
                case Settings.AVERAGE_HEURISTIC_WITH_TIE_BREAKER:
                    return averageWithWeight(start, goal);
                case Settings.EUCLIDEAN_DISTANCE:
                    return euclideanDistance(start, goal);
                default:
                    return manhattanDistance(start, goal);
            }
        }


        private double manhattanDistance(int[] a, int[] b) {
            int dx = Math.abs(a[X] - b[X]);
            int dy = Math.abs(a[Y] - b[Y]);
            return dy + dx;
        }


        private double tieBreakerManhattanDistance(int[] a, int[] b) {
            return manhattanDistance(a, b) * (1.001); // with Tie Breaker
        }


        private double diagonalDistance(int[] a, int[] b) {
            int dx = Math.abs(a[X] - b[X]);
            int dy = Math.abs(a[Y] - b[Y]);
            return Math.max(dx, dy);
        }

        private double euclideanDistance(int[] a, int[] b) {
            int dx = Math.abs(a[X] - b[X]);
            int dy = Math.abs(a[Y] - b[Y]);
            return Math.sqrt(dx * dx + dy * dy);
        }

        private double average(int[] a, int[] b) {
            return (manhattanDistance(a, b) + diagonalDistance(a, b)) / 2;
        }


        private double averageWithWeight(int[] a, int[] b) {
            return (tieBreakerManhattanDistance(a, b) + diagonalDistance(a, b)) / 2;
        }

    }
}


