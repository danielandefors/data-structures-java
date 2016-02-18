package dandefors.list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A singly linked list. Adds elements to the front of the list.
 */
public class SinglyLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        private final T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

    }

    private int size;
    private Node<T> head;

    /**
     * Adds a new element to the front of the list.
     *
     * @param element The element.
     */
    public void add(T element) {
        head = new Node<>(element, head);
        size++;
    }

    /**
     * Get the first element in the list.
     *
     * @return The first element in the list.
     * @throws NoSuchElementException If the list is empty.
     */
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    /**
     * Get the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list is empty.
     *
     * @return True if the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get an iterator over all the list elements.
     *
     * @return An iterator.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = node.element;
                node = node.next;
                return element;
            }
        };
    }

    /**
     * Reverse the list. I.e., the first element in the list will be come the last element.
     */
    public void reverse() {
        Node<T> node = head;
        Node<T> prev = null;
        Node<T> next;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        head = prev;
    }

    /**
     * Sort the list in ascending order.
     *
     * @throws UnsupportedOperationException If the elements are not comparable.
     */
    public void sort() {
        sort((a, b) -> {
            if (a == null) return -1;
            if (b == null) return 1;
            if (a instanceof Comparable<?>) {
                @SuppressWarnings("unchecked")
                Comparable<T> aComp = (Comparable<T>) a;
                return aComp.compareTo(b);
            }
            throw new UnsupportedOperationException("Type is not comparable: " + a.getClass());
        });
    }


    /**
     * Sort the list in ascending order using the given comparator.
     *
     * @param comparator The comparator to use when comparing elements.
     */
    public void sort(Comparator<T> comparator) {
        head = mergeSort(head, comparator);
    }

    /**
     * Merge sort implementation for singly linked list.
     *
     * @param h   The head of the list to be sorted.
     * @param c   The comparator to use when sorting.
     * @param <T> The element type.
     * @return The new head of the sorted list.
     */
    private static <T> Node<T> mergeSort(Node<T> h, Comparator<T> c) {
        // Base case: list is empty or has only one element
        if (h == null || h.next == null) {
            return h;
        }
        // Divide and conquer: list has two or more elements
        Node<T> t = divide(h);
        h = mergeSort(h, c);
        t = mergeSort(t, c);
        return merge(h, t, c);
    }

    /**
     * Split the list using slow/fast pointers.
     * This operation requires linear time and constant space.
     *
     * @param h   The head of a list.
     * @param <T> The element type.
     * @return A pointer to the second half of the list.
     */
    private static <T> Node<T> divide(Node<T> h) {
        Node<T> slow = h;
        Node<T> fast = h;
        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        Node<T> head = slow.next;
        slow.next = null;
        return head;
    }

    /**
     * Merge the lists into one, in linear time and constant space.
     *
     * @param a   A sorted list.
     * @param b   A sorted list.
     * @param c   To compare the elements while merging.
     * @param <T> The element type.
     * @return The two sorted lists merged into a single sorted list.
     */
    private static <T> Node<T> merge(Node<T> a, Node<T> b, Comparator<T> c) {
        // a recursive solution is arguably prettier but may blow up the stack
        Node<T> n, p = null, h = null;
        while (a != null && b != null) {
            if (Objects.compare(a.element, b.element, c) < 0) {
                n = a;
                a = a.next;
            } else {
                n = b;
                b = b.next;
            }
            if (p != null) {
                p.next = n;
            } else {
                h = n;
            }
            p = n;
        }
        if (a != null) {
            p.next = a;
        } else {
            p.next = b;
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        Node<T> n = head;
        for (int i = 0; n != null; i++) {
            if (i > 0) s.append(", ");
            s.append(n.element);
            n = n.next;
        }
        s.append(']');
        return s.toString();
    }

}
