/**
 * Created by moru on 5/26/2015.
 */
import processing.core.PVector;
import java.util.Random;

public class Agent {
    private Random random;
    private float secondsToGoalChange = 0.0f;
    private final int screenWidth, screenHeight;

    // controls whether the agent is in random mode (just wandering) or moving towards a goal
    private boolean idle = true;

    private float speed = 0.0f;
    private PVector position, goal;

    public Agent(long seed, int screenWidth, int screenHeight) {
        random = new Random(seed);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        PVector pos = randomGoal();

        // crosses table in 5 to 9 seconds if moving perfectly along axis
        float v = Math.min(screenHeight, screenWidth) /
                (5 + 4 * random.nextFloat());

        //System.out.println("With speed: " + v);

        this.position = pos;
        setSpeed(v);
    }

    public void update(float deltaSeconds) {
        secondsToGoalChange -= deltaSeconds;
        if(secondsToGoalChange < 0 && idle) {
            // run 3 to 11 seconds in the same direction for the next goal
            secondsToGoalChange = 3 + random.nextFloat() * 8;
            setGoal(randomGoal());
        }

        // move towards goal -----

        PVector path = goal.get();
        path.sub(position);

        PVector direction = path.get();
        direction.normalize();

        PVector move = direction.get();
        move.mult(deltaSeconds * speed);

        if(path.mag() < move.mag()) {
            // we've reached the goal
            this.position = goal.get();
            idle = true; //change into idle mode
            setGoal(randomGoal());
        } else {
            // move closer
            this.position.add(move);
        }
    }

    private PVector randomGoal() {
        int x = random.nextInt(screenWidth);
        int y = random.nextInt(screenHeight);
        return new PVector(x, y);
    }

    public Agent setGoal(PVector goal) {
        this.idle = false;
        this.goal = goal;
        return this;
    }

    /**
     * @param speed in units per second
     * @return
     */
    public Agent setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public PVector getPosition(){
        return position.get();
    }



}

