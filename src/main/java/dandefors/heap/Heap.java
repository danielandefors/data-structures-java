package dandefors.heap;

import java.util.NoSuchElementException;

/**
 * A binary heap.
 */
public interface Heap<K extends Comparable<K>> {

    /**
     * Insert an element into the heap.
     *
     * @param element The element to insert.
     */
    void insert(K element);

    /**
     * Extract the element at the top of the heap.
     *
     * @return The element at the top of the heap.
     * @throws NoSuchElementException If the heap is empty.
     */
    K remove();

    /**
     * Get the size of the heap.
     *
     * @return The number of elements on the heap.
     */
    int size();

    /**
     * Chekc if the heap is empty.
     *
     * @return True its empty.
     */
    boolean isEmpty();

}
