package dandefors.tree;

import dandefors.stack.Stack;
import dandefors.stack.Stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates nodes in order from lo to hi (inclusive).
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
class TreeNodeIterator<K extends Comparable<K>, V> implements Iterator<TreeNode<K, V>> {

    private K lo;
    private K hi;
    private final Stack<TreeNode<K, V>> stack = Stacks.createLinkedStack();

    /**
     * Create a new node iterator from the given root.
     *
     * @param root The root node.
     * @param lo   The low key (inclusive).
     * @param hi   The high key (inclusive).
     */
    public TreeNodeIterator(TreeNode<K, V> root, K lo, K hi) {
        this.lo = lo;
        this.hi = hi;
        initStack(root);
    }

    /**
     * If the node is within range, push it onto the stack.
     *
     * @param node A node.
     */
    private void push(TreeNode<K, V> node) {
        if (node.getKey().compareTo(hi) <= 0) {
            stack.push(node);
        }
    }

    /**
     * Initialize the stack from the given root node.
     *
     * @param node A node.
     */
    private void initStack(TreeNode<K, V> node) {
        if (node == null) return;
        int cmp = lo.compareTo(node.getKey());
        if (cmp < 0) {
            push(node);
            initStack(node.getLeft());
        } else if (cmp > 0) {
            initStack(node.getRight());
        } else {
            push(node);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TreeNode<K, V> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Pop the next node from the stack.
        TreeNode<K, V> node = stack.pop();

        // If the node has a right subtree,
        // find the successor and push parents onto the stack.
        if (node.getRight() != null) {
            TreeNode<K, V> x = node.getRight();
            while (x.getLeft() != null) {
                push(x);
                x = x.getLeft();
            }
            push(x);
        }

        return node;
    }


}
