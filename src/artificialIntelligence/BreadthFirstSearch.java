package artificialIntelligence;

import snake.*;

import java.util.*;

public class BreadthFirstSearch extends AbstractStrategy implements AIStrategy {

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

}