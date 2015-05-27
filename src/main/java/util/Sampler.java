package util;

import java.util.*;

/**
 * Created by gabriel on 28/05/2015.
 */
public class Sampler {
    private final SortedMap<Long, Float> values;

    public Sampler(SortedMap<Long, Float> values) {
        this.values = values;
    }

    public float sample(long position) {
        if(values.isEmpty())
            return 0.0f;
        SortedSet<Long> keys = new TreeSet<>(values.keySet());
        SortedSet<Long> lessSet = keys.headSet(position);
        SortedSet<Long> moreSet = keys.tailSet(position);
        if(lessSet.isEmpty()) return values.get(moreSet.first());
        if(moreSet.isEmpty()) return values.get(lessSet.last());
        float a = values.get(lessSet.last());
        float b = values.get(moreSet.first());
        float delta = moreSet.first() - lessSet.last();
        float t1 = (float)(position - lessSet.last())/delta;
        float t2 = (float)(moreSet.first() - position)/delta;
        return a*t1 + b*t2;
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

    public Sampler cut(long first, long last) {
        SortedMap<Long, Float> map = new TreeMap<>();
        for(long key : values.keySet()) {
            if(key >= first && key <= last)
                map.put(key, values.get(key));
        }
        return new Sampler(map);
    }
}
