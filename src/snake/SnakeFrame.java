package snake;

import java.awt.event.KeyAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SnakeFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private View gamePanel;

    public SnakeFrame() {
        gamePanel = new View();
        add(gamePanel);
        initializeFrame();
    }

    public void initializeFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Settings.FRAME_WIDTH, Settings.FRAME_HEIGHT);
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
