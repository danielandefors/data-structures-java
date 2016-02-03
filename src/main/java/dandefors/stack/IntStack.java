package dandefors.stack;

import java.util.NoSuchElementException;

/**
 * A stack of ints.
 */
public class IntStack {

    private int[] elements;

    private int size;

    /**
     * Create an empty stack.
     */
    public IntStack() {
        elements = new int[2];
    }

    /**
     * Get the size of the stack.
     *
     * @return The size of the stack.
     */
    public int size() {
        return size;
    }

    /**
     * Check if the stack is empty.
     *
     * @return True if the stack is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Push an element.
     *
     * @param element The element.
     */
    public void push(int element) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = element;
    }

    /**
     * Doubles the size of the storage array.
     */
    private void grow() {
        int[] x = new int[elements.length * 2];
        System.arraycopy(elements, 0, x, 0, elements.length);
        this.elements = x;
    }

    /**
     * Pops the next element off the stack.
     *
     * @return The next element.
     * @throws NoSuchElementException If the stack is empty.
     */
    public int pop() {
        if (size == 0) {
            throw new NoSuchElementException("pop on empty stack");
        }
        int element = elements[--size];
        if (elements.length > 2 && size <= elements.length / 4) {
            shrink();
        }
        return element;
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("peek on empty stack");
        }
        return elements[size - 1];
    }

    /**
     * Halves the size of the storage array.
     */
    private void shrink() {
        int[] x = new int[elements.length / 2];
        System.arraycopy(elements, 0, x, 0, size);
        this.elements = x;
    }


}
