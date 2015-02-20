package snake;

import artificialIntelligence.AIContext;

import java.util.Observable;

public class Model extends Observable {

    private Board board;
    private Snake snake;
    private Apple apple;
    private Obstacles obstacles;

    private boolean endGame;
    private boolean pauseGame;
    private boolean inGame;

    public Model() {
        initializeComponents();
    }


    private void initializeComponents() {
        snake = new Snake();
        apple = new Apple();
        apple.setPerimeter(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
        obstacles = new Obstacles();
        apple.generateNewCoordinates(obstacles.getXCoordinates(), obstacles.getYCoordinates());
        board = new Board(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
        endGame = false;
        pauseGame = false;
        inGame = false;
    }


    public void initGame() {

        resetSnake();
        setChanged();
        notifyObservers("ingame");
        inGame = true;
        endGame = false;
        pauseGame = false;
    }


    public void resetSnake() {
        snake.initialize();
        obstacles.generatePerimeter(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);

        for (int i = 0; i < 10; i++)
            obstacles.generateObstacle(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
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


    private void endGame() {
        setChanged();
        notifyObservers("endgame");
        resetSnake();
        inGame = false;
        endGame = true;
    }


    public void update() {

        if (inGame) {
            snake.update();
            checkApple();
            checkCollision();

            setChanged();
            notifyObservers(new int[][]{apple.getCoordinates(),
                                        snake.getXCoordinates(),
                                        snake.getYCoordinates(),
                                        obstacles.getXCoordinates(),
                                        obstacles.getYCoordinates()});
        }
    }


    public void checkCollision() {

        if (snake.checkWallCollision(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT) || snake.checkSnakeCollision() ||
             obstacles.checkCollision(snake.getHeadCoordinates())) {
            endGame();
        }

    }


    public void checkApple() {
        if (snake.checkCollision(apple.getXCoordinate(), apple.getYCoordinate())) {
            apple.generateNewCoordinates(obstacles.getXCoordinates(), obstacles.getYCoordinates());
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


    public int[][] getBoardLayout() {

        int[][] layout = new int[Settings.BOARD_WIDTH][Settings.BOARD_HEIGHT];
        addSnakeToBoardLayout(layout);
        addObstaclesToBoardLayout(layout);
        addAppleToBoardLayout(layout);
        return layout;
    }


    private void addSnakeToBoardLayout(int[][] board) {
        int[] xSnake = snake.getXCoordinates();
        int[] ySnake = snake.getYCoordinates();
        for (int i = 0; i < xSnake.length; i++)
            board[xSnake[i]][ySnake[i]] = Settings.SNAKE;
    }


    private void addObstaclesToBoardLayout(int[][] board) {
        int[] xObstacles = obstacles.getXCoordinates();
        int[] yObstacles = obstacles.getYCoordinates();
        for (int i = 0; i < obstacles.getNumberOfObstacles(); i++)
            board[xObstacles[i]][yObstacles[i]] = Settings.OBSTACLE;
    }


    private void addAppleToBoardLayout(int[][] board) {
        board[apple.getXCoordinate()][apple.getYCoordinate()] = Settings.APPLE;
    }


    public Direction[] runAI(AIContext ai) {
        return ai.getPath(getBoardLayout(), snake.getHeadCoordinates(), apple.getCoordinates());
    }
}
