package dandefors.tree;

/**
 * An ordered symbol table implemented as an AVL tree.
 */
public class AvlTree<K extends Comparable<K>, V> extends AbstractTree<K, V, AvlTreeNode<K, V>> {

    @Override
    protected AvlTreeNode<K, V> createNode(K key, V value) {
        return new AvlTreeNode<>(key, value);
    }

    @Override
    protected AvlTreeNode<K, V> rotateLeft(AvlTreeNode<K, V> h) {
        h = super.rotateLeft(h);
        // Update node heights after left rotation
        updateHeight(h.left);
        updateHeight(h);
        return h;
    }

    @Override
    protected AvlTreeNode<K, V> rotateRight(AvlTreeNode<K, V> h) {
        h = super.rotateRight(h);
        // Update node heights after right rotation
        updateHeight(h.right);
        updateHeight(h);
        return h;
    }

    /**
     * Update the height of the node.
     */
    private void updateHeight(AvlTreeNode<?, ?> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Get the height of the node.
     *
     * @param n A node.
     * @return Its reported height.
     */
    private int height(AvlTreeNode<?, ?> n) {
        if (n == null) return -1;
        return n.height;
    }

    /**
     * Check if the node is height balanced. If it returns zero the node is balanced.
     * If the value is negative the node is left-heavy.
     * Otherwise if it is positive the node is right-heavy.
     *
     * @param h A node.
     * @return The height balance.
     */
    private int balance(AvlTreeNode<K, V> h) {
        return height(h.right) - height(h.left);
    }

    /**
     * Restore height balance in the AVL tree.
     * If the delta between the height of the left and right nodes is greater than one, or less than one, we rotate
     * the tree to restore the height balance.
     *
     * @param h A node.
     * @return The node.
     */
    private AvlTreeNode<K, V> restoreBalance(AvlTreeNode<K, V> h) {
        if (h == null) return null;
        updateHeight(h);
        int x = balance(h);
        if (x == 2) {
            // 2x right-heavy
            if (balance(h.right) < 0) {
                h.right = rotateRight(h.right);
            }
            h = rotateLeft(h);
        } else if (x == -2) {
            // 2x left-heavy
            if (balance(h.left) > 0) {
                h.left = rotateLeft(h.left);
            }
            h = rotateRight(h);
        }
        return h;
    }

    @Override
    protected AvlTreeNode<K, V> put(AvlTreeNode<K, V> node, K key, V value) {
        return restoreBalance(super.put(node, key, value));
    }

    @Override
    protected AvlTreeNode<K, V> delete(AvlTreeNode<K, V> node, K key) {
        return restoreBalance(super.delete(node, key));
    }

}
