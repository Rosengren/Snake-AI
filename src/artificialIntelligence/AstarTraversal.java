package artificialIntelligence;

import snake.*;

import java.util.*;

public class AStarTraversal extends AbstractStrategy implements AIStrategy {

//    TODO:
//    Quickly get the location of the goal and use that
//    Use basic heuristic
//    Use Second heuristic
//    Average out heuristics

    private static final int DISTANCE_BETWEEN_SQUARES = 1;
//    frontier = PriorityQueue()
//    frontier.put(start, 0)
//    came_from = {}
//    cost_so_far = {}
//    came_from[start] = None
//    cost_so_far[start] = 0
//
//    while not frontier.empty():
//      current = frontier.get()
//
//      if current == goal:
//          break
//
//      for next in graph.neighbors(current):
//          new_cost = cost_so_far[current] + graph.cost(current, next)
//          if next not in cost_so_far or new_cost < cost_so_far[next]:
//              cost_so_far[next] = new_cost
//              priority = new_cost + heuristic(goal, next)
//              frontier.put(next, priority)
//              came_from[next] = current

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] snakeHead) {
        Queue<int[]> frontier = new PriorityQueue<int[]>();
        frontier.add(snakeHead); // TODO: make this a priority thing
//        frontier.


        Map<String, int[]> came_from = new HashMap<String, int[]>();
        Map<String, Integer> cost_so_far = new HashMap<String, Integer>();

        came_from.put(coordinatesToString(snakeHead), snakeHead);
        cost_so_far.put(coordinatesToString(snakeHead), 0);

        int new_cost;
        int priority;
        int[] current = new int[] {0, 0};
        while (!frontier.isEmpty()) {
            current = frontier.remove();
            if (isAppleCoordinates(boardLayout, current)) {
                break;
            }

            for (int[] neighbor : getNeighbors(current, boardLayout)) {
                new_cost = cost_so_far.get(current) + DISTANCE_BETWEEN_SQUARES;
                if (notVisited(boardLayout, neighbor) && notObstacle(boardLayout, neighbor) && notSnake(boardLayout, neighbor)) {
                    if (!cost_so_far.containsKey(coordinatesToString(neighbor)) || new_cost < cost_so_far.get(neighbor)) {
                        cost_so_far.put(coordinatesToString(neighbor), new_cost);
//                        priority = new_cost + heuristic();
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


    // TODO: create separate object for heuristics maybe (strategy pattern)
    private int heuristic(int[] current, int[] goal) {
        return 1;
    }

}


