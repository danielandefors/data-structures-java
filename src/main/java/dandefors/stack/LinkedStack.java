package dandefors.stack;

import java.util.NoSuchElementException;

/**
 * A stack implemented as a singly-linked list.
 */
public class LinkedStack<T> implements Stack<T> {

    /**
     * The number of elements on the stack.
     */
    private int size;

    /**
     * The node that represents the top of the stack.
     */
    private Node<T> top;

    /**
     * A node on the stack.
     *
     * @param <T> The value type.
     */
    private static final class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void push(T element) {
        top = new Node<>(element, top);
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("pop on empty stack");
        }
        T element = top.element;
        top = top.next;
        size--;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("peek on empty stack");
        }
        return top.element;
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
     * Reverse the order of the elements on the stack.
     * I.e., the element currently at the top will sink to the bottom.
     * The element at the bottom will float to the top.
     */
    public void reverse() {
        Node<T> next = null;
        Node<T> node = top;
        while (node != null) {
            Node<T> t = node.next;
            node.next = next;
            next = node;
            node = t;
        }
        top = next;
    }


}
