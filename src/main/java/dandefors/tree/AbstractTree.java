package dandefors.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract base class for binary search trees.
 */
abstract class AbstractTree<K extends Comparable<K>, V, NODE extends AbstractTreeNode<K, V, NODE>>
        implements Tree<K, V> {

    protected NODE root;

    @Override
    public NODE getRoot() {
        return root;
    }

    @Override
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Min on empty table");
        }
        return min(root).key;
    }

    protected NODE min(NODE node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Max on empty table");
        }
        return max(root).key;
    }

    protected NODE max(NODE node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    @Override
    public K floor(K key) {
        NODE x = floor(root, key);
        return x != null ? x.key : null;
    }

    protected NODE floor(NODE node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return floor(node.left, key);
        } else if (cmp > 0) {
            NODE x = floor(node.right, key);
            return x != null ? x : node;
        } else {
            return node;
        }
    }

    @Override
    public K ceiling(K key) {
        NODE x = ceiling(root, key);
        return x != null ? x.key : null;
    }

    protected NODE ceiling(NODE node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            NODE x = ceiling(node.left, key);
            return x != null ? x : node;
        } else if (cmp > 0) {
            return ceiling(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public int rank(K key) {
        return rank(root, key);
    }

    protected int rank(NODE node, K key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        } else if (cmp > 0) {
            return rank(node.right, key) + size(node.left) + 1;
        } else {
            return size(node.left);
        }
    }

    @Override
    public K select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return select(root, rank);
    }

    protected K select(NODE node, int rank) {
        int t = size(node.left);
        if (rank < t) {
            return select(node.left, rank);
        } else if (rank > t) {
            return select(node.right, rank - t - 1);
        } else {
            return node.key;
        }
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return () -> new Iterator<K>() {

            private final Iterator<TreeNode<K, V>> nodes = nodes(lo, hi).iterator();

            @Override
            public boolean hasNext() {
                return nodes.hasNext();
            }

            @Override
            public K next() {
                return nodes.next().getKey();
            }
        };
    }

    @Override
    public Iterable<TreeNode<K, V>> nodes(K lo, K hi) {
        return () -> new TreeNodeIterator<>(root, lo, hi);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, value);
    }

    /**
     * Create a new node for the tree.
     *
     * @param key   The key.
     * @param value The value.
     * @return The new node.
     */
    protected abstract NODE createNode(K key, V value);

    protected NODE put(NODE node, K key, V value) {
        if (node == null) {
            // We've reached a leaf. Create a new node to store the new value.
            return createNode(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // Key is less than the node. Update the left subtree.
            node.left = put(node.left, key, value);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        } else if (cmp > 0) {
            // Key is greater than the node. Update the right subtree.
            node.right = put(node.right, key, value);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        } else {
            // Update the value on the node.
            node.value = value;
            return node;
        }
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = delete(root, key);
    }

    protected NODE delete(NODE node, K key) {
        if (node == null) {
            // We've reached a leaf. Nothing to delete.
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // Key is less than the node. Delete in the left subtree.
            node.left = delete(node.left, key);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        } else if (cmp > 0) {
            // Key is greater than the node. Delete in the right subtree.
            node.right = delete(node.right, key);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        } else {
            // Delete the node.
            if (node.left == null) {
                // Replace node with its right subtree.
                return node.right;
            } else if (node.right == null) {
                // Replace node with its left subtree.
                return node.left;
            } else if (node.right.left == null) {
                // Replace node with its right subtree.
                // Move the left subtree to the right node.
                node.right.left = node.left;
                node.right.size += size(node.left);
                return node.right;
            } else {
                // Replace node with its successor (Hibbard).
                NODE p = node;
                NODE s = p.right;
                // Find min in the right subtree.
                // Decrease parent size by one along the way since we will remove a node from its subtree.
                while (s.left != null) {
                    p = s;
                    p.size--;
                    s = s.left;
                }
                // If the successor has a right subtree, replace it as the new left subtree of the parent.
                p.left = s.right;
                // Rewire left and right subtrees for the successor, and recompute its size.
                s.left = node.left;
                s.right = node.right;
                s.size = size(s.left) + size(s.right) + 1;
                return s;
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return get(root, key);
    }

    protected V get(NODE node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    protected int size(NODE node) {
        if (node == null) return 0;
        else return node.size;
    }

}
