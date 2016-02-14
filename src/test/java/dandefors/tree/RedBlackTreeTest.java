package dandefors.tree;

import dandefors.miscellanea.LazyPermutations;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for red-black trees.
 */
public class RedBlackTreeTest
        extends AbstractTreeTest<
        RedBlackTreeNode<String, String>,
        RedBlackTree<String, String>> {

    @Override
    protected RedBlackTree<String, String> createTree() {
        return new RedBlackTree<>();
    }

    /*

    Additional tests to validate red-black tree invariants:

    - The root is always black
    - A red node is always a left child
    - There aren't two red nodes in a row
    - The black height is consistent across all branches

     */

    /**
     * Toggle this field to enable dumping trees before and after an error occurred.
     */
    private static boolean DUMP_TREES = false;

    @FunctionalInterface
    interface InvariantTest {
        void test(RedBlackTreeNode<?, ?> node);
    }

    /**
     * Validate that the tree responds correctly to put and delete operations.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validateTree(InvariantTest tester, String[] s) {
        validatePut(tester, s);
        validateDeleteMin(tester, s);
        validateDeleteMax(tester, s);
        validateDelete(tester, s);
    }

    /**
     * Validate that put works on the given array.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validatePut(InvariantTest tester, String... s) {
        RedBlackTree<String, String> tree = createTree();
        for (String t : s) {
            tree.put(t, t);
            tester.test(tree.getRoot());
            assertEquals(t, tree.get(t));
        }
    }

    /**
     * Validate that delete-min works on the given array.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validateDeleteMin(InvariantTest tester, String... s) {
        RedBlackTree<String, String> tree = createTree();
        for (String t : s) {
            tree.put(t, t);
        }
        while (!tree.isEmpty()) {
            String before = dumpTree(tree);
            tree.deleteMin();
            try {
                tester.test(tree.getRoot());
            } catch (AssertionError e) {
                String after = dumpTree(tree);
                System.err.printf("BEFORE:\n%s\nAFTER DELETE MIN:\n%s\n", before, after);
                throw e;
            }
        }
    }

    /**
     * Validate that delete-max works on the given array.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validateDeleteMax(InvariantTest tester, String... s) {
        RedBlackTree<String, String> tree = createTree();
        for (String t : s) {
            tree.put(t, t);
        }
        while (!tree.isEmpty()) {
            String before = dumpTree(tree);
            tree.deleteMax();
            try {
                tester.test(tree.getRoot());
            } catch (AssertionError e) {
                String after = dumpTree(tree);
                System.err.printf("BEFORE:\n%s\nAFTER DELETE MAX:\n%s\n", before, after);
                throw e;
            }
        }
    }

    /**
     * Validate that delete works on the given array.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validateDelete(InvariantTest tester, String... s) {

        Iterable<Integer[]> indices;
        if (s.length < 8) {
            Integer[] order = new Integer[s.length];
            for (int i = 0; i < s.length; i++) {
                order[i] = i;
            }
            indices = LazyPermutations.of(order);
        } else {
            List<Integer[]> l = new ArrayList<>();
            int ops = 4;
            int step = s.length / ops;
            for (int i = 0; i < s.length; i++) {
                Integer[] ix = new Integer[ops];
                for (int j = 0; j < ix.length; j++) {
                    ix[j] = (step * j + i) % s.length;
                }
                l.add(ix);
            }
            indices = l;
        }

        for (Integer[] ix : indices) {

            RedBlackTree<String, String> tree = createTree();
            for (String t : s) {
                tree.put(t, t);
            }

            for (int i = 0; i < ix.length; i++) {
                String key = s[ix[i]];
                String before = dumpTree(tree);
                tree.delete(key);
                assertFalse(tree.contains(key));
                try {
                    tester.test(tree.getRoot());
                } catch (AssertionError e) {
                    String after = dumpTree(tree);
                    System.err.printf("BEFORE:\n%s\nAFTER DELETE: %s:\n%s\n", before, key, after);
                    throw e;
                }
            }

        }
    }

    /**
     * Dump the tree's internal structure to a string.
     *
     * @param tree The tree.
     * @return Its structure as a human readable string.
     */
    private static String dumpTree(RedBlackTree<String, String> tree) {
        if (!DUMP_TREES) return "";
        StringWriter s = new StringWriter();
        dumpTree(tree.getRoot(), 0, new PrintWriter(s, true));
        return s.toString();
    }

    /**
     * Dump the tree's internal structure to the given writer.
     *
     * @param node  A node in the tree.
     * @param level The node's level (distance from the root).
     * @param out   The output writer.
     */
    private static void dumpTree(RedBlackTreeNode<String, String> node, int level, PrintWriter out) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < level; i++) {
            s.append("..");
        }
        if (node == null) {
            s.append("{}");
        } else {
            s.append(node.key).append(node.isRed() ? " [red]" : "");
        }
        out.println(s);
        if (node != null) {
            dumpTree(node.getLeft(), level + 1, out);
            dumpTree(node.getRight(), level + 1, out);
        }
    }

    /**
     * Modifies the tree in various ways and invokes the tester after each
     * modification to make sure a given invariant is maintained.
     *
     * @param tester Tests an invariant.
     */
    private void runInvariantTest(InvariantTest tester) {

        /*
        Probably testing more than necessary.
        If we consider the implementation we can probably reduce the test cases to only cover the necessary operations.
        Then again, if we do that we might miss bug because of a side effect that we didn't consider.
         */

        for (String[] input : TREES) {
            validateTree(tester, input);
        }

        validateTree(tester, LOREM);
        validateTree(tester, GETTY);
        validateTree(tester, CONST);

    }

    @Test
    public void testRedBlackTreeInvariants() {
        runInvariantTest((node) -> {
            // Make sure tha the root is always black
            assertFalse(isRed(node));
            // Make sure that every red node is a left child
            validateRedToLeft(node);
            // Make sure that we don't have two red nodes in a row
            validateNoTwoRedInRow(node);
            // Make sure that the black height is same for all branches of the tree
            validateBlackHeight(node);
        });
    }

    /**
     * Validate that all red nodes are left children of their parent.
     *
     * @param node A node.
     */
    private static void validateRedToLeft(RedBlackTreeNode<?, ?> node) {
        if (node == null) return;
        RedBlackTreeNode<?, ?> right = node.getRight();
        assertFalse(isRed(right));
        validateRedToLeft(node.getLeft());
        validateRedToLeft(right);
    }

    /**
     * Validate that there aren't two red nodes in a row.
     *
     * @param node A node.
     */
    private static void validateNoTwoRedInRow(RedBlackTreeNode<?, ?> node) {
        if (node == null) return;
        RedBlackTreeNode<?, ?> left = node.getLeft();
        assertFalse(isRed(left) && isRed(left.getLeft()));
        validateNoTwoRedInRow(left);
        validateNoTwoRedInRow(node.getRight());
    }

    /**
     * Validate the black height of the subtree.
     *
     * @param node A node.
     * @return The black height.
     */
    private static int validateBlackHeight(RedBlackTreeNode<?, ?> node) {
        if (node == null) return 1; // leafs are black
        int leftHeight = validateBlackHeight(node.getLeft());
        int rightHeight = validateBlackHeight(node.getRight());
        assertEquals(rightHeight, leftHeight);
        return isRed(node) ? rightHeight : 1 + rightHeight;
    }

    /**
     * Check if a node is red. Returns true if node is non-null and red.
     *
     * @param node A node.
     * @return True if red.
     */
    private static boolean isRed(RedBlackTreeNode<?, ?> node) {
        return node != null && node.isRed();
    }

}
