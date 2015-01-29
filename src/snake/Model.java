package snake;

import java.util.Arrays;
import java.util.Observable;

public class Model extends Observable {

    private int score;
    private Board board;

    /** Constants **/
    private final int BOARD_WIDTH = 300;
    private final int BOARD_HEIGHT = 300;

    private Snake snake;
    private Apple apple;
    private Obstacles obstacles;

    private boolean endGame;
    private boolean pauseGame;
    private boolean inGame;

    public Model() {

        // initialize snake
        snake = new Snake();
        apple = new Apple();
        apple.setPerimeter(BOARD_WIDTH, BOARD_HEIGHT);
        obstacles = new Obstacles();
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        endGame = false;
        pauseGame = false;
        inGame = false;
        score = 0;

    }

    public void initGame() {

        score = 0;
        snake.initialize();
        obstacles.generatePerimeter(BOARD_WIDTH, BOARD_HEIGHT);
        setChanged();
        notifyObservers("ingame");
        inGame = true;
        endGame = false;
        pauseGame = false;
    }

    public void restartGame() {
        initGame();
    }

    public void pauseGame() {
        if (!endGame) {
            if (pauseGame) {
                pauseGame = false;
                inGame = true;
                setChanged();
                notifyObservers("unpause");
            } else {
                setChanged();
                notifyObservers("pause");
                pauseGame = true;
                inGame = false;
            }
        }
    }

    public void exitGame() {

    }

    private void endGame() {
        setChanged();
        notifyObservers("endgame");
        inGame = false;
        endGame = true;
    }

    public void update() {

        if (inGame) {
            snake.update();
            checkApple();
            checkCollision();

            // need to update view about:
            setChanged();
            notifyObservers(new int[][] { apple.getCoordinates(),
                    snake.getXCoordinates(), snake.getYCoordinates(),
                    obstacles.getXCoordinates(), obstacles.getYCoordinates()});
        }
    }

    public void checkCollision() {

        if (snake.checkWallCollision(BOARD_WIDTH, BOARD_HEIGHT) ||
                snake.checkSnakeCollision() || obstacles.checkCollision(snake.getHeadCoordinates())) {
            endGame();
        }

    }

    public void checkApple() {
        if (snake.checkCollision(apple.getXCoordinate(), apple.getYCoordinate())) {
            score += 1;
            apple.generateNewCoordinates();
        }
    }

    public void moveSnake(Direction direction) {
        snake.setDirection(direction);
    }

    public Snake getSnake() {
        return snake;
    }

    public Board getBoard() {
        return board;
    }

    public Apple getApple() {
        return apple;
    }

    public int getScore() {
        return score;
    }

    public int[][] getBoardLayout() {

        int dotSize = snake.getDotSize();

        int height = BOARD_HEIGHT / dotSize;
        int width = BOARD_WIDTH / dotSize;

        int[][] layout = new int[width][height];

        int[] snakeCoordinates = snake.getHeadCoordinates();
        int xSnake = snakeCoordinates[0] / dotSize;
        int ySnake = snakeCoordinates[1] / dotSize;
        layout[xSnake][ySnake] = 1;

        int[] xObstacles = obstacles.getXCoordinates();
        int[] yObstacles = obstacles.getYCoordinates();

        for (int i = 0; i < obstacles.getSize(); i++) {
            layout[xObstacles[i] / dotSize][yObstacles[i] / dotSize] = 2;
        }

        int xApple = apple.getXCoordinate() / dotSize;
        int yApple = apple.getYCoordinate() / dotSize;

        layout[xApple][yApple] = 3;
        return layout;
    }
}
