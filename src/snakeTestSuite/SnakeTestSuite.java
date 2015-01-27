package snakeTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestSnake.class,
        TestBoard.class,
        TestApple.class,
        TestSnakeModel.class,
        TestObstacles.class
})
public class SnakeTestSuite {

}