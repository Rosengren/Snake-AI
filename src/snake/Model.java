package snake;

import java.util.Observable;

public class Model extends Observable {

    private int score;
    private Board board;

    /** Constants **/
    private final int BOARD_WIDTH = 500;
    private final int BOARD_HEIGHT = 500;

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
            checkApple();
            checkCollision();
            snake.update();

            // need to update view about:
            setChanged();
            notifyObservers(new int[][] { apple.getCoordinates(),
                    snake.getXCoordinates(), snake.getYCoordinates(),
                    obstacles.getXCoordinates(), obstacles.getYCoordinates()});
        }
    }

    public void checkCollision() {

        if (snake.checkWallCollision(BOARD_WIDTH, BOARD_HEIGHT) ||
                snake.checkSnakeCollision()) {
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
}
