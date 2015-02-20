package artificialIntelligence;

import snake.Direction;

public class AIContext {

    private AIStrategy strategy;

    public AIContext(AIStrategy strategy) {
        this.strategy = strategy;
    }

    public Direction[] getPath(int[][] boardLayout, int[] snakeHead, int[] goal) {
        return strategy.getPath(boardLayout, snakeHead, goal);
    }

}
