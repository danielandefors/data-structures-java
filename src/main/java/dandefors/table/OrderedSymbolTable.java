package dandefors.table;

import java.util.Collections;

/**
 * A symbol table with ordered keys.
 */
public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {

    /**
     * Get the smallest key in the table.
     *
     * @return The smallest key.
     */
    K min();

    /**
     * Get the largest key in the table.
     *
     * @return The largest key.
     */
    K max();

    /**
     * Get the largest key less than or equal to key.
     *
     * @param key A key.
     * @return The largest key less than or equal to key.
     */
    K floor(K key);

    /**
     * Get the smallest key greater than or equal to key.
     *
     * @param key A key.
     * @return The smallest key greater than or equal to key.
     */
    K ceiling(K key);

    /**
     * Get the number of keys less than key.
     *
     * @param key A key.
     * @return The number of keys less than key.
     */
    int rank(K key);

    /**
     * Get the key of the given rank.
     *
     * @param rank The rank.
     * @return The key of the given rank.
     */
    K select(int rank);

    /**
     * Delete the smallest key from the table.
     */
    default void deleteMin() {
        if (!isEmpty()) {
            delete(min());
        }
    }

    /**
     * Delete the largest key from the table.
     */
    default void deleteMax() {
        if (!isEmpty()) {
            delete(max());
        }
    }

    /**
     * Get number of keys between lo and hi (inclusive).
     *
     * @param lo The low key.
     * @param hi The high key.
     * @return The number of keys between lo and hi (inclusive).
     */
    default int size(K lo, K hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Get all keys between lo and hi (inclusive), in sorted order.
     *
     * @param lo The low key.
     * @param hi The high key.
     * @return All keys between lo an hi (inclusive).
     */
    Iterable<K> keys(K lo, K hi);

    /**
     * Get all keys in sorted order.
     *
     * @return All keys in sorted order.
     */
    @Override
    default Iterable<K> keys() {
        if (isEmpty()) {
            return Collections::<K>emptyIterator;
        }
        return keys(min(), max());
    }


}
