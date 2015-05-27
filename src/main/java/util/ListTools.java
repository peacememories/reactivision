package util;

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
        T first = source.iterator().next();
        if(first != null) {
            return foldLeft(first, new Fun2<T, T, T>() {
                @Override
                public T call(T val1, T val2) {
                    return val1.compareTo(val2) == 1 ? val1 : val2;
                }
            }, source);
        } else {
            return null;
        }
    }

    public static <T extends Comparable<T>> T min(Iterable<T> source) {
        T first = source.iterator().next();
        if(first != null) {
            return foldLeft(first, new Fun2<T, T, T>() {
                @Override
                public T call(T val1, T val2) {
                    return val1.compareTo(val2) == -1 ? val1 : val2;
                }
            }, source);
        } else {
            return null;
        }
    }
}
