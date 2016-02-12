package dandefors.hash;

import dandefors.table.SymbolTable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Hash table with chaining.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class HashTable<K, V> implements SymbolTable<K, V> {

    /*
    Simple hash table with chaining.
    Not quite as sophisticated nor fast as java.util.HashMap.
     */

    private static final int INITIAL_CAPACITY = 1 << 4;
    private static final int MAX_CAPACITY = 1 << 30;

    private static final float MAX_LOAD_FACTOR = 0.75f;
    private static final float MIN_LOAD_FACTOR = 0.1875f;

    private int size;
    private int growSize;
    private int shrinkSize;
    private Node<K, V>[] table;

    /**
     * Create a new hash table.
     */
    public HashTable() {
        resize(INITIAL_CAPACITY);
    }

    /**
     * Create an array of nodes.
     *
     * @param size The array size.
     * @param <K>  The key type.
     * @param <V>  The value type.
     * @return The new array.
     */
    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] createArray(int size) {
        return (Node<K, V>[]) new Node[size];
    }

    /**
     * A node that's used to chain elements upon collision.
     *
     * @param <K> The key type.
     * @param <V> The value type.
     */
    private static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        boolean match(int h, K k) {
            return hash == h && (key == k || key.equals(k));
        }

    }

    /**
     * Compute a hash of the given key.
     *
     * @param k A key.
     * @return Its hash.
     */
    private static int hash(Object k) {

        // Shift the most significant bits up and xor with the original:
        // This is to increase the influence of the most significant bits,
        // which otherwise would be largely ignored, when calculating table
        // index for a given hash.
        int h = k.hashCode();
        return h ^ h >>> 16;

    }

    /**
     * Get the current load factor of the hash table.
     *
     * @return The current load factor.
     */
    public float getLoadFactor() {
        return (float) size / table.length;
    }

    /**
     * Resize the table to the given capacity.
     *
     * @param capacity The new capacity.
     */
    private void resize(int capacity) {
        Node<K, V>[] newTable = createArray(capacity);
        if (table != null) {
            // Rehash the contents in the table
            int mask = capacity - 1;
            int index;
            Node<K, V> next;
            for (Node<K, V> node : table) {
                while (node != null) {
                    next = node.next;
                    index = node.hash & mask;
                    node.next = newTable[index];
                    newTable[index] = node;
                    node = next;
                }
            }
        }
        table = newTable;
        growSize = (int) (capacity * MAX_LOAD_FACTOR);
        shrinkSize = (int) (capacity * MIN_LOAD_FACTOR);
    }


    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int h = hash(key);
        int index = h & table.length - 1;

        Node<K, V> head = table[index];
        Node<K, V> node = head;
        while (node != null) {
            if (node.match(h, key)) {
                // Found key. Update value.
                node.value = value;
                return;
            }
            node = node.next;
        }
        // Key not found. Add new node.
        table[index] = new Node<>(h, key, value, head);
        size++;
        // Resize table if at max load factor.
        if (size == growSize && table.length < MAX_CAPACITY) {
            resize(table.length << 1);
        }

    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int h = hash(key);
        int index = h & table.length - 1;
        Node<K, V> node = table[index];
        Node<K, V> pred = null;
        while (node != null) {
            if (node.match(h, key)) {
                // Found key. Delete the node.
                if (pred != null) {
                    pred.next = node.next;
                } else {
                    table[index] = node.next;
                }
                size--;
                // Resize table if at min load factor.
                if (size == shrinkSize && table.length > INITIAL_CAPACITY) {
                    resize(table.length >>> 1);
                }
                return;
            }
            pred = node;
            node = node.next;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int h = hash(key);
        int index = h & table.length - 1;
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.match(h, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return () -> new Iterator<K>() {

            private int index;
            private Node<K, ?> node;
            private int count;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (node == null) {
                    node = table[index++];
                }
                K k = node.key;
                node = node.next;
                count++;
                return k;
            }
        };
    }

}
