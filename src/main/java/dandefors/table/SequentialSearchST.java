package dandefors.table;

import java.util.Iterator;

/**
 * Linked list implementation of a symbol table. Hence, all search operations are sequential. I.e., worst case
 * performance on most operations to search and modify the table are linear (in the size of the table). As such it is
 * not suitable for large amounts of data.
 */
public class SequentialSearchST<K, V> implements SymbolTable<K, V> {

    private Node<K, V> head;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> tail;

        public Node(K key, V value, Node<K, V> tail) {
            this.key = key;
            this.value = value;
            this.tail = tail;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        for (Node<K, V> x = head, p = null; x != null; p = x, x = x.tail) {
            if (x.key.equals(key)) {
                if (value == null) {
                    // If value is null, remove the key
                    if (p == null) {
                        head = x.tail;
                    } else {
                        p.tail = x.tail;
                    }
                } else {
                    // Otherwise store the new value
                    x.value = value;
                }
                return;
            }
        }
        if (value != null) {
            head = new Node<>(key, value, head);
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        for (Node<K, V> x = head; x != null; x = x.tail) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (Node<K, V> x = head; x != null; x = x.tail) {
            size++;
        }
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return () -> new Iterator<K>() {

            private Node<K, V> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public K next() {
                K x = node.key;
                node = node.tail;
                return x;
            }
        };
    }
}
