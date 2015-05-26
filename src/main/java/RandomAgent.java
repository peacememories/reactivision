/**
 * Created by moru on 5/26/2015.
 */
import processing.core.PVector;
import java.util.Random;

public class RandomAgent {
    private Random random;
    private Agent agent;
    private float secondsToGoalChange = 0.0f;
    private int screenWidth, screenHeight;

    public RandomAgent(long seed, int screenWidth, int screenHeight) {
        random = new Random(seed);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        PVector pos = randomVec2(screenWidth, screenHeight);

        // crosses table in 5 to 9 seconds if moving perfectly along axis
        float v = Math.min(screenHeight, screenWidth) /
                (5 + 4 * random.nextFloat());

        //System.out.println("With speed: " + v);

        agent = new Agent(pos).setSpeed(v);
    }

    public void update(float deltaSeconds) {
        secondsToGoalChange -= deltaSeconds;
        if(secondsToGoalChange < 0) {
            // run 3 to 11 seconds in the same direction for the next goal
            secondsToGoalChange = 3 + random.nextFloat() * 8;
            agent.setGoal(randomVec2(screenWidth, screenHeight));
        }
        agent.update(deltaSeconds);

    }

    private PVector randomVec2(int xMax, int yMax) {
        int x = random.nextInt(xMax);
        int y = random.nextInt(yMax);
        return new PVector(x, y);
    }

    public PVector getPosition(){
        return agent.getPosition();
    }
}

