package dandefors.tree;

/**
 * A node in an AVL tree.
 */
public class AvlTreeNode<K, V> extends AbstractTreeNode<K, V, AvlTreeNode<K, V>> {

    /**
     * The height of the node. I.e., the maximum leaf path of the node.
     */
    protected int height;

    /**
     * Create a new node with the given properties.
     * The new node will have a size of 1.
     *
     * @param key   The key.
     * @param value The value.
     */
    AvlTreeNode(K key, V value) {
        super(key, value);
    }

}
