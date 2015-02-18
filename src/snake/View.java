package snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class View extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    /** Constants **/
    private final int WIDTH = 320;
    private final int HEIGHT = 340;

    private boolean inGame = false;
    private boolean pauseGame = false;

    private Image dot;
    private Image apple;
    private Image obstacle;

    private int apple_x;
    private int apple_y;

    private int[] snake_x;
    private int[] snake_y;

    private int[] obstacles_x;
    private int[] obstacles_y;


    public View() {

        apple_x = -1;
        apple_y = -1;

        snake_x = new int[0];
        snake_y = new int[0];

        obstacles_x = new int[0];
        obstacles_y = new int[0];

        initializeAssets();
        setBackground(Color.black);

    }

    public void initializeAssets() {

        ImageIcon iid = new ImageIcon(this.getClass().getResource("../images/snake.png"));
        dot = iid.getImage();

        ImageIcon iia = new ImageIcon(this.getClass().getResource("../images/apple.png"));
        apple = iia.getImage();

        ImageIcon iio = new ImageIcon(this.getClass().getResource("../images/wall.png"));
        obstacle = iio.getImage();
    }


    public void pauseGame(Graphics g) {
        String msgPaused = "Paused";
        String msgEscape = "Press ESCAPE to Continue";
        String msgEnter = "Press Enter to Restart";
        String msgSpace = "Press Space to Quit";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msgPaused, (WIDTH - metr.stringWidth(msgPaused)) / 2, HEIGHT / 2 - 40);
        g.drawString(msgEscape, (WIDTH - metr.stringWidth(msgEscape)) / 2, HEIGHT / 2 - 20);
        g.drawString(msgEnter, (WIDTH - metr.stringWidth(msgEnter)) / 2, HEIGHT / 2);
        g.drawString(msgSpace, (WIDTH - metr.stringWidth(msgSpace)) / 2, HEIGHT / 2 + 20);

    }

    public void gameOver(Graphics g) {

        // Print game over and center the text
        String msg = "Game Over";
        String replay = "Press Enter to Restart";
        String quitMsg = "Press Space to Quit";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metric.stringWidth(msg)) / 2, HEIGHT / 2 - 20);
        g.drawString(replay, (WIDTH - metric.stringWidth(replay)) / 2, HEIGHT / 2);
        g.drawString(quitMsg, (WIDTH - metric.stringWidth(quitMsg)) / 2, HEIGHT / 2 + 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0; i < snake_x.length; i++) {
                g.drawImage(dot, snake_x[i], snake_y[i], this);
            }


            for (int i = 0; i < obstacles_x.length; i++) {
                g.drawImage(obstacle, obstacles_x[i], obstacles_y[i], this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else if (pauseGame) {
            pauseGame(g);

        } else {
            gameOver(g);
        }


    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof String) {
            String stmt = (String)arg;

            if (stmt.equals("pause")) {
                inGame = false;
                pauseGame = true;
            } else if (stmt.equals("unpause")) {
                inGame = true;
                pauseGame = false;
            } else if (stmt.equals("ingame")) {
                inGame = true;
            } else if (stmt.equals("endgame")) {
                inGame = false;
            }

        } else if (arg instanceof int[][]) {

            int[][] coordinates = (int[][])arg;
            apple_x = coordinates[0][0];
            apple_y = coordinates[0][1];

            snake_x = coordinates[1];
            snake_y = coordinates[2];

            obstacles_x = coordinates[3];
            obstacles_y = coordinates[4];

        }

        repaint();
    }

}
