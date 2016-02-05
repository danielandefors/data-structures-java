package dandefors.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A binary heap. A.k.a., priority queue.
 *
 * @param <K> The value type.
 */
public abstract class ArrayHeap<K> implements Heap<K> {

    private int size;

    @SuppressWarnings("unchecked")
    private K[] elements = (K[]) new Object[2];

    /**
     * Create a min heap. I.e., {@link #remove()} removes the minimum element.
     *
     * @return A min heap.
     */
    public static <K extends Comparable<K>> ArrayHeap<K> createMinHeap() {
        return new Min<>();
    }

    /**
     * Create a min heap. I.e., {@link #remove()} removes the minimum element.
     *
     * @param comparator Used to compare if one value is less than another.
     * @return A min heap.
     */
    public static <K> ArrayHeap<K> createMinHeap(Comparator<K> comparator) {
        return new MinC<>(comparator);
    }

    /**
     * Create a max heap. I.e., {@link #remove()} removes the maximum element.
     *
     * @return A max heap.
     */
    public static <K extends Comparable<K>> ArrayHeap<K> createMaxHeap() {
        return new Max<>();
    }

    /**
     * Create a max heap. I.e., {@link #remove()} removes the maximum element.
     *
     * @param comparator Used to compare if one value is greater than another.
     * @return A max heap.
     */
    public static <K> ArrayHeap<K> createMaxHeap(Comparator<K> comparator) {
        return new MaxC<>(comparator);
    }

    /**
     * Min heap.
     */
    public static class Min<K extends Comparable<K>> extends ArrayHeap<K> {

        @Override
        protected boolean ordered(K i, K j) {
            return i.compareTo(j) <= 0;
        }
    }

    /**
     * Min heap with comparator.
     */
    public static class MinC<K> extends ArrayHeap<K> {

        private Comparator<K> comparator;

        public MinC(Comparator<K> c) {
            comparator = c;
        }

        @Override
        protected boolean ordered(K i, K j) {
            return comparator.compare(i, j) <= 0;
        }
    }

    /**
     * Max heap.
     */
    public static class Max<K extends Comparable<K>> extends ArrayHeap<K> {

        @Override
        protected boolean ordered(K i, K j) {
            return i.compareTo(j) >= 0;
        }
    }

    /**
     * Max heap with comparator.
     */
    public static class MaxC<K> extends ArrayHeap<K> {

        private Comparator<K> comparator;

        public MaxC(Comparator<K> c) {
            comparator = c;
        }

        @Override
        protected boolean ordered(K i, K j) {
            return comparator.compare(i, j) >= 0;
        }
    }

    @Override
    public void insert(K element) {
        if (elements.length == size + 1) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[++size] = element;
        swim(size);
    }

    @Override
    public K extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("extract on empty heap");
        }
        K element = elements[1];
        elements[1] = elements[size];
        sink(1);
        size--;
        if (size < elements.length / 4) {
            elements = Arrays.copyOf(elements, elements.length / 2);
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Heapify-up.
     *
     * @param i The index.
     */
    private void swim(int i) {
        int p;
        while (i > 1 && !orderedAt(p = i / 2, i)) {
            swap(p, i);
            i = p;
        }
    }

    /**
     * Heapify-down.
     *
     * @param i The index.
     */
    private void sink(int i) {
        int j;
        while ((j = i * 2) <= size) {
            if (j + 1 < size && !orderedAt(j, j + 1)) j++;
            if (orderedAt(i, j)) break;
            swap(i, j);
            i = j;
        }
    }

    /**
     * Check if the elements at the given indices are ordered.
     *
     * @param i First index.
     * @param j Second index.
     * @return True if they are ordered.
     */
    private boolean orderedAt(int i, int j) {
        return ordered(elements[i], elements[j]);
    }

    /**
     * Check if the elements are ordered.
     *
     * @param i First element.
     * @param j Second element.
     * @return True if they are ordered.
     */
    protected abstract boolean ordered(K i, K j);

    /**
     * Swap the elements.
     *
     * @param i An index.
     * @param j Another index.
     */
    private void swap(int i, int j) {
        K t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
    }
}
