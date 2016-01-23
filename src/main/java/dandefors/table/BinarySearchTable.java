package dandefors.table;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * Ordered symbol table that uses binary search, which gives us a logarithmic search time (O(lg N)).
 * </p>
 * <p>
 * Insert and delete operations will also require logarithmic time for finding the correct location to insert into or
 * delete from. However, these operations will incur addition cost for array resizing and to shift key-value pairs.
 * The array size is doubled (increased by a factor of two) every time an insert operation finds that the current
 * capacity is insufficient to hold the new key-value pair. The initial capacity is two (unless a different capacity
 * was given in the constructor) which means that the number of array resize operations will be ~ lg N. (More
 * precisely, if N is a power of two the number of resize operations will be lg N - 1.) As such, the cumulative
 * number of array access operations required to copy data upon resizing arrays is less than 4N (2N read and 2N write).
 * The number of array accesses to shift data in the worst case is 2N (N read and N write), with an average of half
 * that for random data. Overall, insert and delete operations run in linearithmic time worst case.
 * </p>
 * <p>
 * Estimated memory usage for a table of size N is between 88 + 16N bytes (for a load factor of 100%) and 88 +
 * 64N bytes (25% load factor). Actual memory usage is implementation dependent.
 * </p>
 */
public class BinarySearchTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    /*

    Memory usage analysis:

    object overhead : 16 bytes
    array references : 16 bytes
    integer : 4 bytes
    padding : 4 bytes

    Each array (of size C):

        array overhead : 24 bytes
        array elements : 8C bytes


    Load factor will be between 25% and 100%, therefore:

        N <= C <= 4N

    Worst case (25% lf):

        40 + 2(24 + 32N) = 88 + 64N

    Best case (100% lf):

        40 + 2(24 + 8N) = 88 + 16N

    If you don't do any deletes worst case load factor (for a non-empty table) will be 50%, thus:

        40 + 2(24 + 16N) = 88 + 32N

     */

    private K[] keys;
    private V[] values;
    private int size;

    public BinarySearchTable() {
        this(2);
    }

    public BinarySearchTable(int capacity) {
        resize(capacity);
    }

    /**
     * Get the current capacity. I.e., the size of the underlying arrays.
     *
     * @return The current capacity.
     */
    public int capacity() {
        return keys.length;
    }

    /**
     * Get the current load factor. I.e., the ratio between the size of the table and its capacity.
     *
     * @return The load factor.
     */
    public float getLoadFactor() {
        return (float) size / capacity();
    }

    private void resize(int capacity) {
        keys = resizeArray(keys, capacity, Comparable.class);
        values = resizeArray(values, capacity, Object.class);
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] resizeArray(T[] current, int capacity, Class<?> elementType) {
        T[] newArray = (T[]) Array.newInstance(elementType, capacity);
        if (current != null) {
            System.arraycopy(current, 0, newArray, 0, Math.min(capacity, current.length));
        }
        return newArray;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int i = rank(key);
        if (value == null) {
            // Delete the key at the given index
            if (i < size && keys[i].compareTo(key) == 0) {
                size--;
                if (size > 0 && size == keys.length / 4) {
                    resize(keys.length / 2);
                }
                System.arraycopy(keys, i + 1, keys, i, size - i);
                System.arraycopy(values, i + 1, values, i, size - i);
            }
        } else {
            // Insert the new value at the given index
            if (i < size && keys[i].compareTo(key) == 0) {
                // Key exists in table -- change the existing value
                values[i] = value;
            } else {
                // Table is at max capacity -- increase size by factor of 2
                if (size == keys.length) {
                    resize(size * 2);
                }
                // Move all key-value pairs after `i` 1 step
                if (i < size) {
                    System.arraycopy(keys, i, keys, i + 1, size - i);
                    System.arraycopy(values, i, values, i + 1, size - i);
                }
                keys[i] = key;
                values[i] = value;
                size++;
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Min on empty table");
        }
        return keys[0];
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Max on empty table");
        }
        return keys[size - 1];
    }

    @Override
    public K floor(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i == size || keys[i].compareTo(key) > 0) {
            if (i == 0) {
                // Key is less than min
                return null;
            }
            i--;
        }
        return keys[i];
    }

    @Override
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i == size) {
            // Key is greater than max
            return null;
        }
        return keys[i];
    }

    @Override
    public int rank(K key) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public K select(int rank) {
        if (rank < 0 || rank >= size) {
            throw new IndexOutOfBoundsException();
        }
        return keys[rank];
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return () -> new Iterator<K>() {

            private int l, h;

            {
                l = rank(lo);
                h = rank(hi);
                if (h < size && keys[h].compareTo(hi) == 0) {
                    h++;
                }
            }

            @Override
            public boolean hasNext() {
                return l < h;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return keys[l++];
            }
        };
    }
}
