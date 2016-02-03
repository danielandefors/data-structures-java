package dandefors.queue;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * A queue of ints.
 * Dynamically sized array storage.
 * Enqueue and dequeue run in (amortized) constant time.
 */
public class IntQueue {

    /**
     * Array that contains the elements in the queue.
     * Will be grow or shrink as needed.
     */
    private int[] elements;

    /**
     * The size of the queue. I.e., the number of elements currently in the queue.
     */
    private int size;

    /**
     * Points to the first element in the queue. I.e., the next element that will be returned by dequeue.
     */
    private int head;

    /**
     * Create an empty queue.
     */
    public IntQueue() {
        elements = new int[2];
    }

    /**
     * Create a new queue and initialize it with the values from the int array.
     * The first element in the array will be the first element returned from the queue.
     *
     * @param initialValues Initial values of the queue.
     */
    public IntQueue(int... initialValues) {
        elements = new int[minCapacity(initialValues.length)];
        System.arraycopy(initialValues, 0, elements, 0, initialValues.length);
        size = initialValues.length;
    }

    /**
     * Create a new queue and initialize it with the values from the iterable.
     * The first element in the collection will be the first element returned from the queue.
     *
     * @param initialValues Initial values of the queue.
     */
    public IntQueue(Iterable<? extends Number> initialValues) {
        elements = new int[minCapacity(guessSize(initialValues))];
        for (Number element : initialValues) {
            enqueue(element.intValue());
        }
    }

    /**
     * Guess the size of the iterable.
     *
     * @param i An iterable.
     * @return The assumed size, or zero if unknown.
     */
    private static int guessSize(Iterable<?> i) {
        if (i instanceof Collection<?>) {
            return ((Collection<?>) i).size();
        }
        long estimate = i.spliterator().estimateSize();
        if (estimate <= Integer.MAX_VALUE) {
            return (int) estimate;
        }
        return 0;
    }

    /**
     * Get the minimum capacity that's needed to store a given number of elements. Returns the smallest power to two
     * that's equal to or greater than size.
     *
     * @param size The size.
     * @return The minimum capacity.
     */
    private static int minCapacity(int size) {
        int l = 2;
        while (l < size) {
            l <<= 1;
        }
        return l;
    }

    /**
     * Get the size of the queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Check if the queue is empty.
     *
     * @return True if the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Enqueue an element.
     *
     * @param element The element.
     */
    public void enqueue(int element) {
        if (size == elements.length) {
            grow();
        }
        elements[(head + size) % elements.length] = element;
        size++;
    }

    /**
     * Doubles the size of the storage array.
     */
    private void grow() {
        int[] x = new int[elements.length * 2];
        System.arraycopy(elements, head, x, 0, elements.length - head);
        System.arraycopy(elements, 0, x, elements.length - head, head);
        this.elements = x;
        this.head = 0;
    }

    /**
     * Dequeue the next element.
     *
     * @return The next element.
     * @throws NoSuchElementException If the queue is empty.
     */
    public int dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("dequeue on empty queue");
        }
        int element = elements[head];
        head = (head + 1) % elements.length;
        size--;

        if (elements.length > 2 && size <= elements.length / 4) {
            shrink();
        }

        return element;
    }

    /**
     * Halves the size of the storage array.
     */
    private void shrink() {
        int[] x = new int[elements.length / 2];
        int tail = elements.length - head;
        if (tail < size) {
            System.arraycopy(elements, head, x, 0, tail);
            System.arraycopy(elements, 0, x, size - tail, tail);
        } else {
            System.arraycopy(elements, head, x, 0, size);
        }
        this.elements = x;
        this.head = 0;
    }

}
