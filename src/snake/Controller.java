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
    private boolean calculateAverage;

    public Controller(Model model) {
        this.model = model;
        keyPress = new TAdapter();
        queue = new LinkedList<Direction>();
        calculateAverage = false;
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

    private void getAverage(AIContext ai) {

        double count = 0;
        for (int i = 0; i < Settings.NUMBER_OF_TRIAL_RUNS; i++) {
            long startTime = System.nanoTime();
            queue.addAll(Arrays.asList(model.runAI(ai)));
            long endTime = System.nanoTime();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000000000.0; // 10^9
            count += elapsedTimeInSeconds;
            System.out.printf("Runtime: %f seconds\n", elapsedTimeInSeconds);
            if (!queue.isEmpty()) {
                model.moveSnake(queue.remove());
            }
        }

        double average = count / Settings.NUMBER_OF_TRIAL_RUNS;
        System.out.printf("Average Time: %f\n", average);
    }

    private void playAI(AIContext ai) {

        if (calculateAverage)
            getAverage(ai);
        else {
            long startTime = System.nanoTime();
            queue.addAll(Arrays.asList(model.runAI(ai)));
            long endTime = System.nanoTime();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000000000.0; // 10^9
            System.out.printf("Runtime: %f seconds\n", elapsedTimeInSeconds);
        }
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
                    playAI(new AIContext(new AStarTraversal(Settings.MANHATTAN_DISTANCE)));
                    return;
                case KeyEvent.VK_4:
                    playAI(new AIContext(new AStarTraversal(Settings.TIE_BREAKER_MANHATTAN_DISTANCE)));
                    return;
                case KeyEvent.VK_5:
                    playAI(new AIContext(new AStarTraversal(Settings.DIAGONAL_DISTANCE_HEURISTIC)));
                    return;
                case KeyEvent.VK_6:
                    playAI(new AIContext(new AStarTraversal(Settings.AVERAGE_HEURISTIC)));
                    return;
                case KeyEvent.VK_7:
                    playAI(new AIContext(new AStarTraversal(Settings.AVERAGE_HEURISTIC_WITH_TIE_BREAKER)));
                    return;
                case KeyEvent.VK_8:
                    playAI(new AIContext(new AStarTraversal(Settings.EUCLIDEAN_DISTANCE)));
                    return;
                case KeyEvent.VK_A:
                    calculateAverage = !calculateAverage;
                    queue = new LinkedList<Direction>();
                    return;
                default:
                    break;
            }
            model.update();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (calculateAverage) return;

        if (!queue.isEmpty()) {
            model.moveSnake(queue.remove());
            model.update();
        }
    }

}
