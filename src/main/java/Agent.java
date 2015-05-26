import processing.core.PVector;

import java.util.Date;
/**
 * Created by moru on 5/26/2015.
 */
public class Agent {

    private float speed = 0.0f;
    private PVector position, goal;

    /**
     * @param position
     */
    public Agent(PVector position) {
        this.position = position;
    }
    public Agent setGoal(PVector goal) {
        this.goal = goal;
        return this;
    }

    /**
     *
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

