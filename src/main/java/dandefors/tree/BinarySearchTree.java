package dandefors.tree;

/**
 * An ordered symbol table implemented as a binary search tree. The tree is not self-balancing. E.g., if the data is
 * inserted in sorted order, the tree will degrade to a linked list. The average cost of search and insert operations
 * after N random inserts should be close to 1.39 lg N.
 */
public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractTree<K, V, BinarySearchTreeNode<K, V>> {

    /*

    Memory usage approximation:

    object overhead : 16 bytes
    root node reference : 8 bytes

    Each node:

        object overhead : 16 bytes
        object references : 4x8 = 32 bytes
        integer : 4 bytes
        padding : 4 bytes

    Total memory usage:

        24 + 56N bytes

     */

    @Override
    protected BinarySearchTreeNode<K, V> createNode(K key, V value) {
        return new BinarySearchTreeNode<>(key, value);
    }

}
