package util;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeSet;

/**
 * Created by gabriel on 28/05/2015.
 */
public class Sampler {
    private final SortedMap<Long, Float> values;

    public Sampler(SortedMap<Long, Float> values) {
        this.values = values;
    }

    public float sample(long position) {
        long lastSample = values.firstKey();
        for(long currentSample: new TreeSet<>(values.keySet())) {
            if(lastSample == currentSample) continue;
            if(position < currentSample) {
                float a = values.get(lastSample);
                float b = values.get(currentSample);
                long dist = currentSample-lastSample;
                float t1 = (float)(position-lastSample)/dist;
                float t2 = (float)(currentSample-position)/dist;
                return a*t1 + b*t2;
            }
            lastSample = currentSample;
        }
        return values.get(values.lastKey());
    }

    public float max() {
        float max = Float.NEGATIVE_INFINITY;
        for(float i : values.values()) {
            if(i > max) max = i;
        }
        return max;
    }

    public float min() {
        float min = Float.POSITIVE_INFINITY;
        for(float i : values.values()) {
            if(i < min) min = i;
        }
        return min;
    }

    public long last() {
        return values.lastKey();
    }

    public long first() {
        return values.firstKey();
    }
}
