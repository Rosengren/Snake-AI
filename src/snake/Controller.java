package snake;

import artificialIntelligence.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener {


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
        model.update();
    }


    private void playAI(AIContext ai) {
        Direction[] moves = model.runAI(ai);
        for (Direction dir : moves) {
            model.moveSnake(dir);
            model.update();
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

            model.update();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        model.update();
    }

}
