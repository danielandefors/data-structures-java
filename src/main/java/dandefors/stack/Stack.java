package dandefors.stack;

/**
 * A generic stack. I.e., a LIFO queue.
 */
public interface Stack<T> {

    /**
     * Push an element to the top of the stack.
     *
     * @param element The element.
     */
    void push(T element);

    /**
     * Pop the next element off the stack.
     *
     * @return The next element (that was on the top of the stack).
     * @throws java.util.NoSuchElementException If the stack is empty.
     */
    T pop();

    /**
     * Returns the top of the stack without removing it.
     *
     * @return The top of the stack.
     * @throws java.util.NoSuchElementException If the stack is empty.
     */
    T peek();

    /**
     * Get the number of elements on the stack.
     *
     * @return The number of elements on the stack.
     */
    int size();

    /**
     * Check if the stack is empty.
     *
     * @return True if the stack is empty.
     */
    boolean isEmpty();

}
