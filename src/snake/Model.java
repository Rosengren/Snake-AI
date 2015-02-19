package snake;

import artificialIntelligence.AIContext;
import artificialIntelligence.AstarTraversal;
import artificialIntelligence.DepthFirstSearch;

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

        // initialize snake
        snake = new Snake();
        apple = new Apple();
        apple.setPerimeter(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
        obstacles = new Obstacles();
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
        resetSnake();
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
            notifyObservers(new int[][]{apple.getCoordinates(),
                    snake.getXCoordinates(), snake.getYCoordinates(),
                    obstacles.getXCoordinates(), obstacles.getYCoordinates()});
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


    public int[][] getBoardLayout() {

        int height = Settings.BOARD_HEIGHT;
        int width = Settings.BOARD_WIDTH;

        int[][] layout = new int[width][height];

        int[] xSnake = snake.getXCoordinates();
        int[] ySnake = snake.getYCoordinates();
        for (int i = 0; i < xSnake.length; i++) {
            layout[xSnake[i]][ySnake[i]] = 1;
        }

        int[] xObstacles = obstacles.getXCoordinates();
        int[] yObstacles = obstacles.getYCoordinates();

        for (int i = 0; i < obstacles.getNumberOfObstacles(); i++)
            layout[xObstacles[i]][yObstacles[i]] = 2;


        int xApple = apple.getXCoordinate();
        int yApple = apple.getYCoordinate();

        layout[xApple][yApple] = 3;

        return layout;
    }


    public Direction[] runAI() {
//        AIContext ai = new AIContext(new BreadthFirstSearch());
//        AIContext ai = new AIContext(new DepthFirstSearch());
        AIContext ai = new AIContext(new AstarTraversal());
        return ai.getPath(getBoardLayout(), snake.getHeadCoordinates());
    }
}
