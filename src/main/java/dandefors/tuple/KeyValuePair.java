package dandefors.tuple;

/**
 * A key-value pair.
 */
public interface KeyValuePair<K, V> {

    /**
     * Get the key.
     *
     * @return The key.
     */
    K getKey();

    /**
     * Get the value.
     *
     * @return The value.
     */
    V getValue();

}
