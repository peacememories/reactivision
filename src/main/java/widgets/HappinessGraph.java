package widgets;

import stores.HappinessStore;
import util.ListTools;

import java.util.LinkedList;

/**
 * Created by gabriel on 5/27/15.
 */
public class HappinessGraph extends Graph {
    public HappinessGraph(float width, float height) {
        super(width, height);
    }

    @Override
    public Iterable<Point> getPoints() {
        Iterable<HappinessStore.HistoryEntry> source = HappinessStore.getInstance().getLogs();
        Iterable<Float> values = ListTools.map(new ListTools.Fun1<HappinessStore.HistoryEntry, Float>() {
            @Override
            public Float call(HappinessStore.HistoryEntry val) {
                return val.getHappiness();
            }
        }, source);
        float max = ListTools.max(values);
        float min = ListTools.min(values);

        //Iterable<Float> times = ListTools.map()
        return null;
    }
}
