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
        final float max = ListTools.max(ListTools.map(new ListTools.Fun1<Float, Float>() {
            @Override
            public Float call(Float val) {
                return Math.abs(val);
            }
        }, values), 0.0f);
        values = ListTools.map(new ListTools.Fun1<Float, Float>() {
            @Override
            public Float call(Float val) {
                return val / max;
            }
        }, values);
        Iterable<Long> times = ListTools.map(new ListTools.Fun1<HappinessStore.HistoryEntry, Long>() {
            @Override
            public Long call(HappinessStore.HistoryEntry val) {
                return val.getTime();
            }
        }, source);
        final long longest = ListTools.max(times, 0l);
        final long earliest = longest - (60*1000); //15 minutes;
        Iterable<Float> newTimes = ListTools.map(new ListTools.Fun1<Long, Float>() {
            @Override
            public Float call(Long val) {
                return (float)(val - earliest) / ((float)longest - earliest);
            }
        }, times);
        return ListTools.zipWith(new ListTools.Fun2<Float, Float, Point>() {
            @Override
            public Point call(Float left, Float right) {
                return new Point(left, right);
            }
        }, newTimes, values);
    }
}
