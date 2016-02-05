package dandefors.heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A binary heap of ints.
 * Elements are stored as integer primitives.
 */
public abstract class IntHeap implements Heap<Integer> {

    private int size;
    private int[] elements = new int[2];

    /**
     * Create a min heap. I.e., {@link #extract()} removes the minimum element.
     *
     * @return A min heap.
     */
    public static IntHeap createMinHeap() {
        return new Min();
    }

    /**
     * Create a max heap. I.e., {@link #extract()} removes the maximum element.
     *
     * @return A max heap.
     */
    public static IntHeap createMaxHeap() {
        return new Max();
    }

    /**
     * Min heap.
     */
    public static class Min extends IntHeap {

        @Override
        protected boolean ordered(int i, int j) {
            return i <= j;
        }
    }

    /**
     * Max heap.
     */
    public static class Max extends IntHeap {

        @Override
        protected boolean ordered(int i, int j) {
            return i >= j;
        }
    }

    @Override
    public void insert(Integer element) {
        insert(element.intValue());
    }

    /**
     * Insert an element into the heap.
     *
     * @param element The element to insert.
     */
    public void insert(int element) {
        if (elements.length == size + 1) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[++size] = element;
        swim(size);
    }

    @Override
    public Integer remove() {
        return extract();
    }

    /**
     * Extract the element at the top of the heap.
     * This is either the minimum or the maximum element based on the implementation.
     *
     * @return The element at the top of the heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    public int extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("extract on empty heap");
        }
        int element = elements[1];
        elements[1] = elements[size];
        sink(1);
        size--;
        if (size < elements.length / 4) {
            elements = Arrays.copyOf(elements, elements.length / 2);
        }
        return element;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
    protected abstract boolean ordered(int i, int j);

    /**
     * Swap the elements.
     *
     * @param i An index.
     * @param j Another index.
     */
    private void swap(int i, int j) {
        int t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
    }

}
