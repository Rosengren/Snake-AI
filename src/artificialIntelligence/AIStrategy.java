package artificialIntelligence;

import snake.*;

interface AIStrategy {

    public static final int SNAKE = 1;
    public static final int OBSTACLES = 2;
    public static final int APPLE = 3;

    public Direction[] getPath(int[][] boardLayout, Direction direction, int[] snakeHead);

}
