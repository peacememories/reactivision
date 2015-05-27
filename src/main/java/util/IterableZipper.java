package util;

import java.util.Iterator;

/**
 * Created by gabriel on 5/27/15.
 */
public class IterableZipper<T1, T2, K> implements Iterable<K> {
    private final Iterable<T1> left;
    private final Iterable<T2> right;
    private final ListTools.Fun2<T1, T2, K> fun;

    public IterableZipper(ListTools.Fun2<T1, T2, K> fun, Iterable<T1> left, Iterable<T2> right) {
        this.left = left;
        this.right = right;
        this.fun = fun;
    }

    @Override
    public Iterator<K> iterator() {
        return new ZipIterator();
    }

    public class ZipIterator implements Iterator<K> {
        private final Iterator<T1> leftIterator;
        private final Iterator<T2> rightIterator;

        private ZipIterator() {
            leftIterator = left.iterator();
            rightIterator = right.iterator();
        }

        @Override
        public boolean hasNext() {
            return leftIterator.hasNext() && rightIterator.hasNext();
        }

        @Override
        public K next() {
            return fun.call(leftIterator.next(), rightIterator.next());
        }

        @Override
        public void remove() {
            leftIterator.remove();
            rightIterator.remove();
        }
    }
}
