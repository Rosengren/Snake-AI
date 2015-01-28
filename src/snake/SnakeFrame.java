package snake;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SnakeFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private View gamePanel;

    /** Constants **/
    private final int WIDTH = 320;
    private final int HEIGHT = 340;

    public SnakeFrame() {
        gamePanel = new View();
        add(gamePanel);
        initializeFrame();
    }

    public void initializeFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Snake");
        setResizable(false);
        setVisible(true);
        setFocusable(true);

    }

    public void addPanel(JPanel panel) {
        add(panel);
    }

    public void addController(KeyAdapter adapter) {
        addKeyListener(adapter);
    }

    public View getView() {
        return gamePanel;
    }
}
