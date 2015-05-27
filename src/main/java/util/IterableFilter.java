package util;

import java.util.Iterator;

/**
 * Created by gabriel on 5/27/15.
 */
public class IterableFilter<T> implements Iterable<T> {
    private final Iterable<T> source;
    private final ListTools.Fun1<T, Boolean> fun;

    public IterableFilter(Iterable<T> source, ListTools.Fun1<T, Boolean> fun) {
        this.source = source;
        this.fun = fun;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    public class FilterIterator implements Iterator<T> {
        private final Iterator<T> sourceIter;
        private T next;

        private FilterIterator() {
            sourceIter = source.iterator();
            next = genNext();
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            T n = next;
            next = genNext();
            return n;
        }

        @Override
        public void remove() {

        }

        private T genNext() {
            while(sourceIter.hasNext()) {
                T n = sourceIter.next();
                if(fun.call(n))
                    return n;
            }
            return null;
        }
    }
}
