package dandefors.table;

/**
 * A sequential search table that implements the move-to-front heuristic on {@link #get(Object) search operations}.
 * That is, each search hit is moved to the front of the list, which optimizes search performance for applications that
 * search for the same key (or small subset of keys) over and over.
 */
public class MoveToFrontTable<K, V> extends SequentialSearchTable<K, V> {

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        for (Node<K, V> x = head, y = null; x != null; y = x, x = x.tail) {
            if (x.key.equals(key)) {

                // move search hit to front
                if (y != null) {
                    y.tail = x.tail;
                    x.tail = head;
                    head = x;
                }

                return x.value;
            }
        }
        return null;
    }

}
