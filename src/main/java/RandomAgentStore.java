import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

/**
 * Created by moru on 5/26/2015.
 */
public class RandomAgentStore {
    //listens to tick event
    //updates agents

    private static RandomAgentStore store;
    public static RandomAgentStore getStore() {
        if(store == null) {
            store = new RandomAgentStore();
        }
        return store;
    }

    private Set<RandomAgent> agentCtrls;

    private RandomAgentStore() {
        agentCtrls = new HashSet<>();

        //TODO dummy data for now
        int screenWidth = 600;
        int screenHeight = 600;
        agentCtrls.add(new RandomAgent(1233, screenWidth, screenHeight));
        agentCtrls.add(new RandomAgent(5679, screenWidth, screenHeight));
        agentCtrls.add(new RandomAgent(9013, screenWidth, screenHeight));
    }

    public Set<RandomAgent> getAgents() {
        return Collections.unmodifiableSet(agentCtrls);
    }

    public int nrOfActiveAgents() {
        return agentCtrls.size();
    }

    public void update(float deltaSeconds) {
        for(RandomAgent rac : agentCtrls) {
            rac.update(deltaSeconds);
        }
    }
}
