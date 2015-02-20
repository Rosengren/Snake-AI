package snake;

import artificialIntelligence.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Controller implements ActionListener {

    private static final int DELAY = 50;
    private Queue<Direction> queue;

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
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }


    private void playAI(AIContext ai) {
        queue.addAll(Arrays.asList(model.runAI(ai)));
    }


    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch(key) {
                case KeyEvent.VK_UP:
                    model.moveSnake(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    model.moveSnake(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    model.moveSnake(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    model.moveSnake(Direction.RIGHT);
                    break;
                case KeyEvent.VK_ENTER:
                    model.restartGame();
                    return;
                case KeyEvent.VK_SPACE:
                    System.exit(0);
                    break;
                case KeyEvent.VK_ESCAPE:
                    model.pauseGame();
                    return;
                case KeyEvent.VK_1:
                    playAI(new AIContext(new BreadthFirstSearch()));
                    return;
                case KeyEvent.VK_2:
                    playAI(new AIContext(new DepthFirstSearch()));
                    return;
                case KeyEvent.VK_3:
                    playAI(new AIContext(new AStarTraversal()));
                    return;
                default:
                    break;
            }
            model.update();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (!queue.isEmpty()) {
            model.moveSnake(queue.remove());
            model.update();
        }
    }

}
