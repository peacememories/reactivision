import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

/**
 * Created by moru on 5/26/2015.
 */
public class AgentStore {
    //listens to tick event
    //updates agents

    private static AgentStore store;
    public static AgentStore getStore() {
        if(store == null) {
            store = new AgentStore();
        }
        return store;
    }

    private Set<Agent> agentCtrls;

    private AgentStore() {
        agentCtrls = new HashSet<>();

        //TODO dummy data for now
        for(int i = 0; i < 50; ++i) {
            // (1234 + i) ← random generator seed
            agentCtrls.add(new Agent(1234 + i));
        }
    }

    public Set<Agent> getAgents() {
        return Collections.unmodifiableSet(agentCtrls);
    }

    public int nrOfActiveAgents() {
        return agentCtrls.size();
    }

    public void update(float deltaSeconds) {
        for(Agent rac : agentCtrls) {
            rac.update(deltaSeconds);
        }
    }
}
