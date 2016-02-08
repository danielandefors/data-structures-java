package dandefors.tree;

import dandefors.stack.Stack;
import dandefors.stack.Stacks;

/**
 * An ordered symbol table implemented as a red-black tree.
 */
public class RedBlackTree<K extends Comparable<K>, V> extends AbstractTree<K, V, RedBlackTreeNode<K, V>> {

    @Override
    protected RedBlackTreeNode<K, V> createNode(K key, V value) {
        return new RedBlackTreeNode<>(key, value);
    }

    /**
     * Rotate the tree left at `h`.
     *
     * @param h A node.
     * @return The rotated node that replaces `h`.
     */
    private RedBlackTreeNode<K, V> rotateLeft(RedBlackTreeNode<K, V> h) {
        RedBlackTreeNode<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * Rotate the tree right at `h`.
     *
     * @param h A node.
     * @return The rotated node that replaces `h`.
     */
    private RedBlackTreeNode<K, V> rotateRight(RedBlackTreeNode<K, V> h) {
        RedBlackTreeNode<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * Is the node red?
     *
     * @param h A node.
     * @return True if red.
     */
    private boolean isRed(RedBlackTreeNode<K, V> h) {
        return h != null && h.isRed();
    }

    /**
     * Checks if the given node is part of a 3-node (if we visualize the tree as such).
     *
     * @param node A node.
     * @return True if it is part of a 3-node.
     */
    private boolean isThreeNode(RedBlackTreeNode<K, V> node) {
        return node.isRed() || isRed(node.left);
    }

    /**
     * Swaps colors between `a` and `b`.
     *
     * @param a A node.
     * @param b Another node.
     */
    private void swapColors(RedBlackTreeNode<K, V> a, RedBlackTreeNode<K, V> b) {
        boolean c = a.color;
        a.color = b.color;
        b.color = c;
    }

    /**
     * Restores red-black tree invariants at the given node.
     *
     * @param node A node.
     * @return The same node or a replacement.
     */
    private RedBlackTreeNode<K, V> restore(RedBlackTreeNode<K, V> node) {
        if (node != null) {
            if (isRed(node.right) && !isRed(node.left)) {
                // Rotate to fix right-leaning red node
                swapColors(node, node.right);
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                // Rotate to fix two red nodes in a row
                swapColors(node, node.left);
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                // Restore a temporary 4-node
                node.color = RedBlackTreeNode.RED;
                node.left.color = RedBlackTreeNode.BLACK;
                node.right.color = RedBlackTreeNode.BLACK;
            }
        }
        return node;
    }

    /**
     * Restores the red-black tree invariant that the root should always be black.
     */
    private void restoreRootIsBlack() {
        if (root != null) {
            root.color = RedBlackTreeNode.BLACK;
        }
    }

    /**
     * Prepare to delete a node in the left subtree.
     *
     * @param h A node.
     * @return Node or a replacement.
     */
    private RedBlackTreeNode<K, V> prepareDeleteInLeft(RedBlackTreeNode<K, V> h) {
        if (h.left == null) {
            return h;
        }
        if (h.right != null && !isThreeNode(h.left)) {
            if (isRed(h.right.left)) {
                // Left sibling is a 3-node
                // Borrow a node from the right sibling
                h.left.color = RedBlackTreeNode.RED;
                h.right.left.color = h.color;
                h.color = RedBlackTreeNode.BLACK;
                h.right = rotateRight(h.right);
                h = rotateLeft(h);
            } else {
                // Transform into a temporary 4-node
                h.color = RedBlackTreeNode.BLACK;
                h.left.color = RedBlackTreeNode.RED;
                h.right.color = RedBlackTreeNode.RED;
            }
        }
        return h;
    }

    /**
     * Prepare to delete a node in the right subtree.
     *
     * @param h A node.
     * @return Node or a replacement.
     */
    private RedBlackTreeNode<K, V> prepareDeleteInRight(RedBlackTreeNode<K, V> h) {
        if (h.right == null) {
            return h;
        }
        if (!isThreeNode(h.right)) {
            if (isRed(h.left)) {
                // Parent is a 3-node
                // Borrow a node from the left sibling
                if (isThreeNode(h.left.right)) {
                    // Left sibling is a 3-node
                    h.left.right.color = h.color;
                    h.color = RedBlackTreeNode.RED;
                    h.left.right.left.color = RedBlackTreeNode.BLACK;
                    h.left = rotateLeft(h.left);
                    h = rotateRight(h);
                    h.right = rotateLeft(h.right);
                } else {
                    // Left sibling is a 2-node
                    h.left.color = h.color;
                    h.color = RedBlackTreeNode.BLACK;
                    h.right.color = RedBlackTreeNode.RED;
                    h.left.right.color = RedBlackTreeNode.RED;
                    h = rotateRight(h);
                }
            } else if (isRed(h.left.left)) {
                // Parent is a 2-node
                // Borrow a node from the left sibling
                h.left.color = h.color;
                h.left.left.color = RedBlackTreeNode.BLACK;
                h.color = RedBlackTreeNode.RED;
                h = rotateRight(h);
                h.right = rotateLeft(h.right);
            } else {
                // Transform into a temporary 4-node
                h.color = RedBlackTreeNode.BLACK;
                h.left.color = RedBlackTreeNode.RED;
                h.right.color = RedBlackTreeNode.RED;
            }
        }
        return h;
    }

    @Override
    public void put(K key, V value) {
        super.put(key, value);
        restoreRootIsBlack();
    }

    @Override
    protected RedBlackTreeNode<K, V> put(RedBlackTreeNode<K, V> node, K key, V value) {
        return restore(super.put(node, key, value));
    }

    @Override
    public void delete(K key) {
        super.delete(key);
        restoreRootIsBlack();
    }

    @Override
    protected RedBlackTreeNode<K, V> delete(RedBlackTreeNode<K, V> node, K key) {
        if (node == null) {
            // Key not found in tree.
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // Key is less than the node. Delete in the left subtree.
            // Transform on the way down
            node = prepareDeleteInLeft(node);
            node.left = delete(node.left, key);
            node.size = size(node.left) + size(node.right) + 1;
            return restore(node);
        } else if (cmp > 0) {
            // Key is greater than the node. Delete in the right subtree.
            // Transform on the way down
            node = prepareDeleteInRight(node);
            node.right = delete(node.right, key);
            node.size = size(node.left) + size(node.right) + 1;
            return restore(node);
        } else {
            if (node.left == null) {
                // Replace with right subtree
                return node.right;
            } else if (node.right == null) {
                // Replace with left subtree
                node.left.color = RedBlackTreeNode.BLACK;
                return node.left;
            } else {
                // Delete successor. Copy its value into current node.
                RedBlackTreeNode<K, V> s = min(node.right);
                RedBlackTreeNode<K, V> x = delete(node, s.key);
                node.key = s.key;
                node.value = s.value;
                return x;
            }

        }

    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
        restoreRootIsBlack();
    }

    /**
     * Delete the smallest key from the subtree at `h`.
     *
     * @param h A node.
     * @return The node that should replace `h`.
     */
    protected RedBlackTreeNode<K, V> deleteMin(RedBlackTreeNode<K, V> h) {

        // Check if the tree is empty
        if (h == null) {
            return null;
        }

        RedBlackTreeNode<K, V> node = h;
        Stack<RedBlackTreeNode<K, V>> stack = Stacks.createLinkedStack();

        // Traverse down the left subtree
        while (node.left != null) {
            node = prepareDeleteInLeft(node);
            stack.push(node);
            node = node.left;
        }

        // Delete the node:
        // Replace it with its right subtree
        node = node.right;

        // Backtrack up the tree and restore red-black invariants
        RedBlackTreeNode<K, V> parent;
        while (!stack.isEmpty()) {
            parent = stack.pop();
            parent.left = restore(node);
            parent.size -= 1;
            node = parent;
        }

        // Restore invariants at the root and return it
        return restore(node);

    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
        restoreRootIsBlack();
    }

    /**
     * Delete the greatest key from the subtree at `h`.
     *
     * @param h A node.
     * @return The node that should replace `h`.
     */
    protected RedBlackTreeNode<K, V> deleteMax(RedBlackTreeNode<K, V> h) {

        // Check if the tree is empty
        if (h == null) {
            return null;
        }

        RedBlackTreeNode<K, V> node = h;
        Stack<RedBlackTreeNode<K, V>> stack = Stacks.createLinkedStack();

        // Traverse down the right subtree
        while (node.right != null) {
            node = prepareDeleteInRight(node);
            stack.push(node);
            node = node.right;

        }

        // Delete the node:
        // Replace it with its left subtree
        node = node.left;

        // If the node was a 3-node:
        // Color it black to transform it into a 2-node
        if (isRed(node)) {
            node.color = RedBlackTreeNode.BLACK;
        }

        // Backtrack up the tree and restore red-black invariants
        RedBlackTreeNode<K, V> parent;
        while (!stack.isEmpty()) {
            parent = stack.pop();
            parent.right = restore(node);
            parent.size -= 1;
            node = parent;
        }

        // Restore invariants at the root and return it
        return restore(node);

    }

}
