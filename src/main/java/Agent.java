import processing.core.PVector;

import java.util.Date;
/**
 * Created by moru on 5/26/2015.
 */
public class Agent {

    private final float speed;
    private PVector position, goal;

    /**
     * @param position
     * @param speed in units per second
     */
    public Agent(PVector position, float speed) {
        this.position = position;
        this.speed = speed;
    }
    public void setGoal(PVector goal) {
        this.goal = goal;
    }

    public void update(float deltaSeconds) {
        PVector path = goal.get();
            path.sub(position);

        PVector direction = path.get();
            direction.normalize();

        PVector move = direction.get();
            move.mult(deltaSeconds * speed);

        if(path.mag() < move.mag()) {
            // we've reached the goal
            this.position = goal.get();
        } else {
            // move closer
            this.position.add(move);
        }
    }

    // subclass with random agent
    //moves toward a goal, every second reset goal
}

