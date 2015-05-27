package widgets;

import processing.core.PVector;
import stores.HappinessStore;
import util.ListTools;
import util.Sampler;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by gabriel on 5/27/15.
 */
public class HappinessGraph extends Graph {
    public HappinessGraph(float width, float height) {
        super(width, height);
    }

    @Override
    public Collection<PVector> getPoints() {
        Sampler sampler = HappinessStore.getInstance().getLogs();
        Collection<PVector> vectors = new LinkedList<>();
        int steps = 20;
        long delta = 2000;
        long stepwidth = delta/steps;
        long now = sampler.last();
        sampler = sampler.cut(now-delta, now);
        float scale = 1.0f/Math.max(Math.abs(sampler.max()), Math.abs(sampler.min()));
        for(int i = 0; i < steps ;i++) {
            long t = i*stepwidth+(now-delta);
            vectors.add(new PVector((float)i/steps, sampler.sample(t)*scale));
        }
        return vectors;
    }
}
