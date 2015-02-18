package snake;


public class SnakeGame {

    public SnakeGame() {

        SnakeFrame frame = new SnakeFrame();
//        View view = new View();

        Model model = new Model();
        Controller controller = new Controller(model);

//        model.addObserver(frame.getView());
        frame.addController(controller.getKeyListener());
//        frame.addPanel(view);
        controller.initGame();

    }
    public static void main(String[] args) {
        new SnakeGame();
    }
}
