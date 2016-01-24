package dandefors.table;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A symbol table implemented as a binary search tree. The tree is not self-balancing. E.g., if the data is inserted
 * in sorted order, the tree will degrade to a linked list. The average cost of search and insert operations after N
 * random inserts should be close to 1.39 lg N.
 */
public class BinarySearchTreeTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    /*

    Memory usage analysis:

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

    private Node<K, V> root;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Min on empty table");
        }
        return min(root).key;
    }

    private Node<K, V> min(Node<K, V> node) {
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

    private Node<K, V> max(Node<K, V> node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    @Override
    public K floor(K key) {
        Node<K, V> x = floor(root, key);
        return x != null ? x.key : null;
    }

    private Node<K, V> floor(Node<K, V> node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return floor(node.left, key);
        } else if (cmp > 0) {
            Node<K, V> x = floor(node.right, key);
            return x != null ? x : node;
        } else {
            return node;
        }
    }

    @Override
    public K ceiling(K key) {
        Node<K, V> x = ceiling(root, key);
        return x != null ? x.key : null;
    }

    private Node<K, V> ceiling(Node<K, V> node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            Node<K, V> x = ceiling(node.left, key);
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

    private int rank(Node<K, V> node, K key) {
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

    private K select(Node<K, V> node, int rank) {
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
        return () -> new NodeIterator<>(root, lo, hi);
    }

    /**
     * Iterates keys in order from lo to hi (inclusive).
     *
     * @param <K> The key type.
     */
    private static class NodeIterator<K extends Comparable<K>> implements Iterator<K> {

        private static class NodeStack<K> {
            private Node<K, ?> node;
            private NodeStack<K> tail;

            public NodeStack(Node<K, ?> node, NodeStack<K> tail) {
                this.node = node;
                this.tail = tail;
            }
        }

        private K lo;
        private K hi;
        private NodeStack<K> head;

        public NodeIterator(Node<K, ?> root, K lo, K hi) {
            this.lo = lo;
            this.hi = hi;
            initStack(root);
        }

        /**
         * Pop the next node from the stack.
         *
         * @return The next node.
         */
        private Node<K, ?> pop() {
            Node<K, ?> node = head.node;
            head = head.tail;
            return node;
        }

        /**
         * If the node is within range, push it onto the stack.
         *
         * @param node A node.
         */
        private void push(Node<K, ?> node) {
            if (node.key.compareTo(hi) <= 0) {
                head = new NodeStack<>(node, head);
            }
        }

        /**
         * Initialize the stack from the given root node.
         *
         * @param node A node.
         */
        private void initStack(Node<K, ?> node) {
            if (node == null) return;
            int cmp = lo.compareTo(node.key);
            if (cmp < 0) {
                push(node);
                initStack(node.left);
            } else if (cmp > 0) {
                initStack(node.right);
            } else {
                push(node);
            }
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            // Pop the next node from the stack.
            Node<K, ?> node = pop();

            // If the node has a right subtree,
            // find the successor and push parents onto the stack.
            if (node.right != null) {
                Node<K, ?> x = node.right;
                while (x.left != null) {
                    push(x);
                    x = x.left;
                }
                push(x);
            }

            return node.key;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            // We've reached a leaf. Create a new node to store the new value, if any.
            return value == null ? null : new Node<>(key, value, 1);
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
        } else if (value == null) {
            // Value is null. Delete the node.
            if (node.left == null) {
                // Replace node with its right subtree.
                return node.right;
            } else if (node.right == null) {
                // Replace node with its left subtree.
                return node.left;
            } else if (node.right.left == null) {
                // Replace node with its right subtree.
                // Set the left subtree as the left subtree of the right node.
                node.right.left = node.left;
                node.right.size += size(node.left);
                return node.right;
            } else {
                // Replace node with its successor (Hibbard).
                Node<K, V> p = node;
                Node<K, V> s = p.right;
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
        } else {
            // Update the value on the node.
            node.value = value;
            return node;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
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

    private static int size(Node<?, ?> node) {
        if (node == null) return 0;
        else return node.size;
    }
}
