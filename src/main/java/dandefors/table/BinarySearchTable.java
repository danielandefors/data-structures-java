package dandefors.table;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ordered symbol table that uses binary search, which gives us a logarithmic time search time (O(lg N)). Insert and
 * delete operations will also require logarithmic time for finding the correct location to insert into or delete from.
 * However, these operations will incur addition cost for array resizing (size is increased by a factor of two) and
 * shifting elements (worst case N, average ~N/2 for random data).
 */
public class BinarySearchTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public BinarySearchTable() {
        this(1);
    }

    public BinarySearchTable(int capacity) {
        resize(capacity);
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
                if (size == keys.length / 4) {
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
