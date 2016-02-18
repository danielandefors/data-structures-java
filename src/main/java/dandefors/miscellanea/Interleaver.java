package dandefors.miscellanea;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Emits elements from the nested iterators in interleaved order.
 *
 * @param <T> The element type.
 */
public class Interleaver<T> implements ReversibleIterator<T> {

    /**
     * The index of the next element.
     */
    private int index;

    /**
     * The index when the last (as in most recent) iterator was retired.
     */
    private int lastRetired = -1;

    /**
     * Active iterators. The first iterator in the list contains the next element to be returned.
     */
    private LinkedList<ReversibleIterator<T>> iterators = new LinkedList<>();

    /**
     * Retired iterators. We keep track of these to support backtracking by calling previous.
     */
    private LinkedList<RetiredIterator<T>> retired = new LinkedList<>();

    /**
     * Keeps track of a retired iterator and the index when it was retired.
     *
     * @param <T> The element type.
     */
    private static class RetiredIterator<T> {
        private final ReversibleIterator<T> iterator;
        private final int retiredAtIndex;

        RetiredIterator(ReversibleIterator<T> iterator, int retiredAtIndex) {
            this.iterator = iterator;
            this.retiredAtIndex = retiredAtIndex;
        }

        public ReversibleIterator<T> getIterator() {
            return iterator;
        }

        public int getRetiredAtIndex() {
            return retiredAtIndex;
        }
    }

    @SafeVarargs
    public Interleaver(ReversibleIterator<T>... iterators) {
        for (ReversibleIterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                this.iterators.add(iterator);
            }
        }
    }

    public boolean hasNext() {
        // As long as we have active iterators we have more elements to return
        return !iterators.isEmpty();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        ReversibleIterator<T> nextIterator = iterators.removeFirst();
        T item = nextIterator.next();
        if (nextIterator.hasNext()) {
            // If the iterator has more element to emit, add it last
            iterators.addLast(nextIterator);
        } else {
            // Otherwise retire it
            retired.addFirst(new RetiredIterator<>(nextIterator, index));
            lastRetired = index;
        }
        index++;
        return item;
    }

    public boolean hasPrevious() {
        // As long as the next index is greater than zero we can backtrack.
        return index > 0;
    }

    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
        ReversibleIterator<T> prevIterator;

        // Check if we should revive a retired iterator
        if (lastRetired == index) {
            // Get the most recently retired iterator
            prevIterator = retired.removeFirst().getIterator();
            lastRetired = retired.isEmpty() ? -1 : retired.getFirst().getRetiredAtIndex();
        } else {
            // Otherwise grab the last iterator from the list of active iterators
            prevIterator = iterators.removeLast();
        }
        iterators.addFirst(prevIterator);
        return prevIterator.previous();
    }
}
