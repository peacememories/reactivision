package stores;

import util.Dispatcher;
import util.Sampler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by gabriel on 26/05/2015.
 */
public class HappinessStore implements Dispatcher.DispatchHandler {
    private final static HappinessStore instance = new HappinessStore();

    public static HappinessStore getInstance() {
        return instance;
    }

    private final SortedMap<Long, Float> history = new TreeMap<>();
    private float lastHappiness;
    private long lastTime;

    private HappinessStore() {
        Dispatcher.getInstance().register(this);
    }

    public Sampler getLogs() {
        return new Sampler(history);
    }

    public void update(long time) {
        long deltaTime = time-lastTime;
        if(deltaTime > 1000) {
            float newHappiness = AgentStore.getStore().avgHappiness();
            if (history.isEmpty()) {
                history.put(time, 0.0f);
            } else {
                history.put(time, (newHappiness - lastHappiness) / deltaTime);
            }
            lastHappiness = newHappiness;
            lastTime = time;
        }
    }

    @Override
    public void handle(Object payload) {
        //TODO delete this
    }

    public static class HistoryEntry {
        private final long time;
        private final float happiness;

        private HistoryEntry(long time, float happiness) {
            this.time = time;
            this.happiness = happiness;
        }

        public long getTime() {
            return time;
        }

        public float getHappiness() {
            return happiness;
        }
    }
}
