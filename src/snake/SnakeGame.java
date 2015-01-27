package snake;


public class SnakeGame {

    private View view;
    private SnakeFrame frame;
    private Model model;
    private Controller controller;

    public SnakeGame() {

        frame = new SnakeFrame();
        view = new View();

        model = new Model();
        controller = new Controller(model);

        model.addObserver(frame.getView());
        frame.addController(controller.getKeyListener());
        frame.addPanel(view);
        controller.initGame();

    }
    public static void main(String[] args) {
        new SnakeGame();
    }
}
