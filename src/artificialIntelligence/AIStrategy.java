package artificialIntelligence;

import snake.*;

interface AIStrategy {

    public Direction[] getPath(int[][] boardLayout, int[] snakeHead);

}
