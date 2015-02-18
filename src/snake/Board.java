package snake;

public class Board {

    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Board() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
