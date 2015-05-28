package stores;

import agents.Agent;

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

        for(int i = 0; i < 20; ++i) {
            // (1234 + i) <- random generator seed
            agentCtrls.add(new Agent(1234 + i));
        }
    }

    public Set<Agent> getAgents() {
        return Collections.unmodifiableSet(agentCtrls);
    }

    public float avgExcitation() {
        float happiness = 0;
        for(Agent a : agentCtrls) {
            happiness += a.getHappiness();
        }
        return happiness / agentCtrls.size();
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
