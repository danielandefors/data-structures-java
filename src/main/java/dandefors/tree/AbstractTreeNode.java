package dandefors.tree;

/**
 * Abstract tree node.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
abstract class AbstractTreeNode<K, V, SELF extends AbstractTreeNode<K, V, SELF>> implements TreeNode<K, V> {

    protected K key;
    protected V value;
    protected SELF left;
    protected SELF right;

    /**
     * The size of the node. I.e., the number of keys in the subtree represented by this node (including its own key).
     */
    protected int size;

    /**
     * Create a new node with the given properties.
     * The new node will have a size of 1.
     *
     * @param key   The key.
     * @param value The value.
     */
    AbstractTreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.size = 1;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public SELF getLeft() {
        return left;
    }

    @Override
    public SELF getRight() {
        return right;
    }

}
