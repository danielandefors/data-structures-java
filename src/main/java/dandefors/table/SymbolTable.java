package dandefors.table;

/**
 * A symbol table.
 *
 * @see OrderedSymbolTable
 */
public interface SymbolTable<K, V> {

    /**
     * Insert a key-value pair into the table.
     *
     * @param key   The key.
     * @param value The value.
     */
    void put(K key, V value);

    /**
     * Search for a value by its key.
     *
     * @param key The key.
     * @return The value associated with the key, or null if the key doesn't exist in the table.
     */
    V get(K key);

    /**
     * Delete the key (and its value) from the table.
     *
     * @param key The key.
     */
    default void delete(K key) {
        put(key, null);
    }

    /**
     * Check if the given key exists in the table.
     *
     * @param key The key.
     * @return True if the key exists.
     */
    default boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Check if the table is empty.
     *
     * @return True if empty.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Get the number of key-value pairs in the table.
     *
     * @return The number of key-value pairs.
     */
    int size();

    /**
     * Get all keys in the table.
     *
     * @return All keys.
     */
    Iterable<K> keys();

}
