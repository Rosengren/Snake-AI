package artificialIntelligence;

import snake.*;

interface AIStrategy {

    public static final int VISITED  = -1;
    public static final int SNAKE    =  1;
    public static final int OBSTACLE =  2;
    public static final int APPLE    =  3;

    public Direction[] getPath(int[][] boardLayout, int[] snakeHead);

}
