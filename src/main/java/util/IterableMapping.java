package util;

import java.util.Iterator;

/**
 * Created by gabriel on 5/27/15.
 */
public class IterableMapping<T, K> implements Iterable<K> {
    private final Iterable<T> source;
    private final ListTools.Fun1<T, K> fun;

    public IterableMapping(Iterable<T> source, ListTools.Fun1<T, K> fun) {
        this.source = source;
        this.fun = fun;
    }
    @Override
    public Iterator<K> iterator() {
        return new MappingIterator();
    }

    private class MappingIterator implements Iterator<K> {
        private final Iterator<T> sourceIter;
        private MappingIterator() {
            sourceIter = source.iterator();
        }

        @Override
        public boolean hasNext() {
            return sourceIter.hasNext();
        }

        @Override
        public K next() {
            return fun.call(sourceIter.next());
        }

        @Override
        public void remove() {
            sourceIter.remove();
        }
    }
}
