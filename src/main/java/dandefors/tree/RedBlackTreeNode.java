package dandefors.tree;

/**
 * A tree node in a {@link RedBlackTree}.
 */
public class RedBlackTreeNode<K extends Comparable<K>, V> extends AbstractTreeNode<K, V, RedBlackTreeNode<K, V>> {

    /**
     * Black.
     */
    public static final boolean BLACK = false;

    /**
     * Red.
     */
    public static final boolean RED = true;

    /**
     * The node's color.
     */
    protected boolean color;

    /**
     * Create a new node with the given properties.
     * The new node will be colored red.
     *
     * @param key   The key.
     * @param value The value.
     */
    public RedBlackTreeNode(K key, V value) {
        super(key, value);
        this.color = RedBlackTreeNode.RED;
    }

    /**
     * Return true if the node is red.
     *
     * @return True if red.
     */
    public boolean isRed() {
        return color;
    }

}
