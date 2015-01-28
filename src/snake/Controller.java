package snake;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;


public class Controller implements ActionListener {

    private static final int DELAY = 50;

    private Timer timer;

    private Model model;
    private TAdapter keyPress;

    public Controller(Model model) {
        this.model = model;

        keyPress = new TAdapter();


    }

    public KeyAdapter getKeyListener() {
        return keyPress;
    }

    public void initGame() {
        model.initGame();
        timer = new Timer(DELAY, this);
        timer.start();
        model.update();
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch(key) {
                case KeyEvent.VK_UP:
                    model.moveSnake(Direction.UP);
                    model.update();
                    break;
                case KeyEvent.VK_DOWN:
                    model.moveSnake(Direction.DOWN);
                    model.update();
                    break;
                case KeyEvent.VK_LEFT:
                    model.moveSnake(Direction.LEFT);
                    model.update();
                    break;
                case KeyEvent.VK_RIGHT:
                    model.moveSnake(Direction.RIGHT);
                    model.update();
                    break;
                case KeyEvent.VK_ENTER:
                    model.restartGame();
                    break;
                case KeyEvent.VK_SPACE:
                    model.exitGame();
                    System.exit(0);
                    break;
                case KeyEvent.VK_ESCAPE:
                    model.pauseGame();
                    break;
                default:
                    break;

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //model.update();
    }

}