package stores;

import util.Dispatcher;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by gabriel on 26/05/2015.
 */
public class HappinessStore implements Dispatcher.DispatchHandler {
    private final static HappinessStore instance = new HappinessStore();

    public static HappinessStore getInstance() {
        return instance;
    }

    private final LinkedList<HistoryEntry> history = new LinkedList<>();
    private float lastHappiness;
    private long lastTime;

    private HappinessStore() {
        Dispatcher.getInstance().register(this);
    }

    public Iterable<HistoryEntry> getLogs() {
        return Collections.unmodifiableList(history);
    }

    public void update(long time) {
        long deltaTime = time-lastTime;
        if(deltaTime > 1000) {
            float newHappiness = AgentStore.getStore().avgHappiness();
            if (history.isEmpty()) {
                history.add(new HistoryEntry(time, 0));
            } else {
                history.add(new HistoryEntry(time, (newHappiness - lastHappiness) / deltaTime));
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
