package snake;

import artificialIntelligence.AIContext;
import artificialIntelligence.BreadthFirstSearch;

import java.util.Observable;

public class Model extends Observable {

    private int score;
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
        score = 0;

    }

    public void initGame() {

        score = 0;
        snake.initialize();
        obstacles.generatePerimeter(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
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

        if (snake.checkWallCollision(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT) ||
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

    public int[] getSnakeHead() {
        return snake.getHeadCoordinates();
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

        int height = Settings.BOARD_HEIGHT;
        int width = Settings.BOARD_WIDTH;

        int[][] layout = new int[width][height];

        int[] snakeCoordinates = snake.getHeadCoordinates();
        int xSnake = snakeCoordinates[0];
        int ySnake = snakeCoordinates[1];
        layout[xSnake][ySnake] = 1;

        int[] xObstacles = obstacles.getXCoordinates();
        int[] yObstacles = obstacles.getYCoordinates();

        System.out.println("w: " + width + ", h: " + height);
        System.out.println("x: " + xObstacles.length + ". y: " + yObstacles.length + ". size: " + obstacles.getNumberOfObstacles());
        System.out.println("xx: " + xObstacles[10]);
        for (int i = 0; i < obstacles.getNumberOfObstacles() - 1; i++) {
//            layout[xObstacles[i] / dotSize][yObstacles[i] / dotSize] = 2;
            System.out.println("x: " + xObstacles[i] + " y: " + yObstacles[i]);
        }

        int xApple = apple.getXCoordinate();
        int yApple = apple.getYCoordinate();

        layout[xApple][yApple] = 3;
        return layout;
    }


    public void runAI() {
        AIContext ai = new AIContext(new BreadthFirstSearch());
//        ai.getPath(getBoardLayout(), snake.getDirection(), snake.getHeadCoordinates());

        int[][] bl = getBoardLayout();
        String result = "";
        for (int i = 0; i < bl.length; i++) {
            for (int j = 0; j < bl[0].length; j++) {
                result += bl[i][j] + " ";
            }
            result += "\n";
        }

        System.out.println(result);

    }
}
