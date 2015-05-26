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

    private HappinessStore() {
        Dispatcher.getInstance().register(this);
    }

    public Iterable<HistoryEntry> getLogs() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void handle(Object payload) {
        if(payload instanceof HistoryEntry) {
            HistoryEntry entry = (HistoryEntry) payload;
            history.add(entry);
        }
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
