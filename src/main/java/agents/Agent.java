package agents; /**
 * Created by moru on 5/26/2015.
 */
import facilities.Facility;
import facilities.FacilityType;
import processing.core.PVector;
import stores.FacilitiesStore;
import util.Conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Agent {
    private Random random;
    private float secondsToGoalChange = 0.0f;

    // controls whether the agent is in random mode (just wandering) or moving towards a goal
    private boolean idle = true;

    private float speed = 0.0f;
    private PVector position, goal;
    private float happiness = 0.0f;

    // likes each type of facility with a random score between -1 and 1
    public Map<FacilityType, Float> preferences;

    public Agent(long seed) {
        random = new Random(seed);

        PVector pos = randomGoal();

        // crosses table in 5 to 9 seconds if moving perfectly along axis
        float v = Math.min(Conf.SCREEN_HEIGHT, Conf.SCREEN_WIDTH) /
                (5 + 4 * random.nextFloat());

        //System.out.println("With speed: " + v);

        this.position = pos;
        setSpeed(v);

        preferences = new HashMap<>();

        float excitation = random.nextFloat();
        //System.out.println(excitation);
        preferences.put(FacilityType.SPORT, -excitation * 0.01f);
        preferences.put(FacilityType.CLUB, excitation * 0.01f);
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

        // make the move
        if(path.mag() < move.mag()) {
            // we've reached the goal
            this.position = goal.get();
            idle = true; //change into idle mode
            setGoal(randomGoal());
        } else {
            // move closer
            this.position.add(move);
        }

        // check if close to any facilities
        /*
         * TODO proper trajectory collision detection, e.g.:
         *  if (exists(pos) where (facilityPos - pos).mag() < (radius + facilityRadius)) {
         */
        for(Facility f : FacilitiesStore.getStore().getFacilities()) {

            PVector toFacility = f.position.get();
                toFacility.sub(position);

            if(toFacility.mag() < f.radius) {
                // in a facilities zone of influence
                //hasBeenInFacility = true;

                float deltaHappiness = preferences.get(f.type).floatValue();
                this.happiness += deltaHappiness;
                this.happiness = Math.min(1.0f, Math.max(-1.0f, this.happiness));

                /*
                System.out.println("In facility of type " + f.type + " which satisfies by "
                        + deltaHappiness + " (total: " + happiness + ")");
                System.out.println("Preferences: " + preferences);
                */

                //TODO less strong (bend current path by strength of delta
                if (deltaHappiness >= 0) {
                    // TODO if agent likes the facility: set goal to center
                    //setGoal(f.position);
                } else {
                    // TODO if negative: set goal away from center
                }
                //TODO if it hasn't been in a center, lessen excitation (change towards 0)
            }
        }

    }

    private PVector randomGoal() {
        int x = random.nextInt(Conf.SCREEN_WIDTH);
        int y = random.nextInt(Conf.SCREEN_HEIGHT);
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

    public float getHappiness() {
        return happiness;
    }


}

