package util;

import java.util.Iterator;

/**
 * Created by gabriel on 5/27/15.
 */
public class IterableZipper<T, K> implements Iterable<Pair<T, K>> {
    private final Iterable<T> left;
    private final Iterable<K> right;

    public IterableZipper(Iterable<T> left, Iterable<K> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Iterator<Pair<T, K>> iterator() {
        return null;
    }

    public class ZipIterator implements Iterator<Pair<T, K>> {
        private final Iterator<T> leftIterator;
        private final Iterator<K> rightIterator;

        private ZipIterator() {
            leftIterator = left.iterator();
            rightIterator = right.iterator();
        }

        @Override
        public boolean hasNext() {
            return leftIterator.hasNext() && rightIterator.hasNext();
        }

        @Override
        public Pair<T, K> next() {
            return new Pair<>(leftIterator.next(), rightIterator.next());
        }

        @Override
        public void remove() {
            leftIterator.remove();
            rightIterator.remove();
        }
    }
}
