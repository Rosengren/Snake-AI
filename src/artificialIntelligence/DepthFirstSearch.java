package artificialIntelligence;

import snake.*;

import java.util.List;
import java.util.ArrayList;

public class DepthFirstSearch extends AbstractStrategy implements AIStrategy {

    private List<int[]> path;

    @Override
    public Direction[] getPath(int[][] boardLayout, int[] snakeHead, int[] goal) {
        path = new ArrayList<int[]>();
        int[][] visited = new int[boardLayout.length][boardLayout[0].length];
        depthFirstSearch(boardLayout, visited, snakeHead);
        path.add(0, snakeHead); // append head
        return getDirectionPath(path);
    }


    private boolean depthFirstSearch(int[][] board, int[][] visited, int[] current) {

        setVisited(visited, current);
        printBoard(board);
        if (isAppleCoordinates(board, current)) return true;

        for (int[] neighbor : getNeighbors(current, board)) {
            if (validMove(visited, board, neighbor)) {
                if (depthFirstSearch(board, visited, neighbor)) {
                    path.add(0, neighbor);
                    return true;
                }
            }
        }

        return false;
    }

}