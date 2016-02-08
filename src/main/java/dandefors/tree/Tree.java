package dandefors.tree;

import dandefors.table.OrderedSymbolTable;

import java.util.Collections;

/**
 * An ordered symbol table implemented as a binary search tree.
 */
public interface Tree<K extends Comparable<K>, V> extends OrderedSymbolTable<K, V> {

    /**
     * Get the root node.
     *
     * @return The root node.
     */
    TreeNode<K, V> getRoot();

    /**
     * Get all nodes in the tree, in sorted order.
     *
     * @return All nodes.
     */
    default Iterable<TreeNode<K, V>> nodes() {
        if (isEmpty()) {
            return Collections::<TreeNode<K, V>>emptyIterator;
        }
        return nodes(min(), max());
    }

    /**
     * Get all nodes between lo and hi (inclusive), in sorted order.
     *
     * @param lo The low key.
     * @param hi The high key.
     * @return All nodes between lo an hi (inclusive).
     */
    Iterable<TreeNode<K, V>> nodes(K lo, K hi);

}
