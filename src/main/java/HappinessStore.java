import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by gabriel on 26/05/2015.
 */
public class HappinessStore {
    private static final LinkedList<HistoryEntry> history = new LinkedList<>();

    private HappinessStore() {}

    public static void logEntry(long time, float happiness) {
        history.add(new HistoryEntry(time, happiness));
    }

    public static Iterable<HistoryEntry> getLogs() {
        return Collections.unmodifiableList(history);
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
