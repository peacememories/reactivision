package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by gabriel on 5/27/15.
 */
public class ListTools {
    public static <T, K> K foldLeft(K first, Fun2<K, T, K> fun, Iterable<T> source) {
        for(T item : source) {
            first = fun.call(first, item);
        }
        return first;
    }

    public static <T, K> Iterable<K> map(Fun1<T, K> fun, Iterable<T> source) {
        return new IterableMapping<>(source, fun);
    }

    public static <T> Iterable<T> filter(Fun1<T, Boolean> fun, Iterable<T> source) {
        return new IterableFilter<>(source, fun);
    }

    public static interface Fun1<T, K> {
        public K call(T val);
    }

    public static interface Fun2<T1, T2, K> {
        public K call(T1 val1, T2 val2);
    }

    public static <T extends Comparable<T>> T max(Iterable<T> source) {
        Iterator<T> it = source.iterator();
        T first = it.hasNext() ? it.next() : null;
        if(first != null) {
            return max(source, first);
        } else {
            return null;
        }
    }

    public static <T extends Comparable<T>> T max(Iterable<T> source, T neutral) {
        return foldLeft(neutral, new Fun2<T, T, T>() {
            @Override
            public T call(T val1, T val2) {
                return val1.compareTo(val2) == 1 ? val1 : val2;
            }
        }, source);
    }

    public static <T extends Comparable<T>> T min(Iterable<T> source) {
        Iterator<T> it = source.iterator();
        T first = it.hasNext() ? it.next() : null;
        if(first != null)
            return min(source, first);
        else
            return null;
    }

    public static <T extends Comparable<T>> T min(Iterable<T> source, T neutral) {
        return foldLeft(neutral, new Fun2<T, T, T>() {
            @Override
            public T call(T val1, T val2) {
                return val1.compareTo(val2) == -1 ? val1 : val2;
            }
        }, source);
    }

    public static <T, K> Iterable<Pair<T, K>> zip(Iterable<T> left, Iterable<K> right) {
        return zipWith(new Fun2<T, K, Pair<T, K>>() {
            @Override
            public Pair<T, K> call(T l, K r) {
                return new Pair<T, K>(l, r);
            }
        }, left, right);
    }

    public static <T1, T2, K> Iterable<K> zipWith(Fun2<T1, T2, K> fun, Iterable<T1> left, Iterable<T2> right) {
        return new IterableZipper<>(fun, left, right);
    }

    public static <T> Collection<T> fromIterable(Iterable<T> source) {
        LinkedList<T> list = new LinkedList<>();
        for(T item : source) {
            list.add(item);
        }
        return list;
    }
}
