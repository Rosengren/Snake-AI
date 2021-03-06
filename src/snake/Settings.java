package snake;

public final class Settings {

    public static final int BOARD_WIDTH = 30;
    public static final int BOARD_HEIGHT = 30;
    public static final int SNAKE_STARTING_POSITION = 20;

    public static final int NUMBER_OF_TRIAL_RUNS = 10000;

    public static final double OBSTACLE_PERCENT_COVERAGE = 0.05 * (BOARD_HEIGHT * BOARD_WIDTH);

    public static final int FRAME_WIDTH = 300;
    public static final int FRAME_HEIGHT = 320;

    public static final int SNAKE    =  1;
    public static final int OBSTACLE =  2;
    public static final int APPLE    =  3;

    public static final int MANHATTAN_DISTANCE = 0;
    public static final int TIE_BREAKER_MANHATTAN_DISTANCE = 1;
    public static final int DIAGONAL_DISTANCE_HEURISTIC = 2;
    public static final int AVERAGE_HEURISTIC = 3;
    public static final int AVERAGE_HEURISTIC_WITH_TIE_BREAKER = 4;
    public static final int EUCLIDEAN_DISTANCE = 5;
}
