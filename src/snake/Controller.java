package snake;

import artificialIntelligence.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

public class Controller implements ActionListener {

    private static final int DELAY = 50;
    private Queue<Direction> queue;

    private Timer timer;
    private Model model;
    private TAdapter keyPress;

    public Controller(Model model) {
        this.model = model;
        keyPress = new TAdapter();
        queue = new LinkedList<Direction>();
    }


    public KeyAdapter getKeyListener() {
        return keyPress;
    }


    public void initGame() {
        model.initGame();
        model.update();
        setTimer();
    }

    private void setTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }


    private void playAI(AIContext ai) {
        Direction[] moves = model.runAI(ai);
        for (Direction dir : moves) {
            queue.add(dir);
        }
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
                    System.exit(0);
                    break;
                case KeyEvent.VK_ESCAPE:
                    model.pauseGame();
                    break;
                case KeyEvent.VK_1:
                    playAI(new AIContext(new BreadthFirstSearch()));
                    break;
                case KeyEvent.VK_2:
                    playAI(new AIContext(new DepthFirstSearch()));
                    break;
                case KeyEvent.VK_3:
                    playAI(new AIContext(new AStarTraversal()));
                    break;
                default:
                    break;
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (!queue.isEmpty()) {
            model.moveSnake(queue.remove());
            model.update();
        }
    }

}
