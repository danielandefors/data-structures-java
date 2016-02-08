package dandefors.tree;

/**
 * A node in a {@link BinarySearchTree}.
 */
public class BinarySearchTreeNode<K, V> extends AbstractTreeNode<K, V, BinarySearchTreeNode<K, V>> {

    /**
     * Creates a new node.
     *
     * @param key   The key.
     * @param value The value.
     */
    public BinarySearchTreeNode(K key, V value) {
        super(key, value);
    }
}
