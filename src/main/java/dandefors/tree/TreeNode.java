package dandefors.tree;

import dandefors.tuple.KeyValuePair;

/**
 * A node in a binary search tree.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public interface TreeNode<K, V> extends KeyValuePair<K, V> {

    /**
     * Get the left tree branch.
     *
     * @return The left branch.
     */
    TreeNode<K, V> getLeft();

    /**
     * Get the right tree branch.
     *
     * @return The right branch.
     */
    TreeNode<K, V> getRight();

}
