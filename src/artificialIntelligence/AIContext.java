package artificialIntelligence;

import snake.Direction;

public class AIContext {

    private AIStrategy strategy;

    public AIContext(AIStrategy strategy) {
        this.strategy = strategy;
    }

    public Direction[] getPath(int[][] boardLayout, int[] snakeHead) {
        return strategy.getPath(boardLayout, snakeHead);
    }

}
