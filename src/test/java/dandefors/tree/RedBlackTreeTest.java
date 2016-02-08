package dandefors.tree;

import dandefors.miscellanea.ArrayPermutations;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

    @FunctionalInterface
    interface InvariantTest {
        void test(RedBlackTreeNode<?, ?> node);
    }

    /**
     * Validate that delete-min works on all permutations of the input array.
     *
     * @param tester The invariant tester.
     * @param s      The input array.
     */
    private void validateDeleteMinAllPermutations(InvariantTest tester, String... s) {


        for (String[] p : new ArrayPermutations<>(s)) {
//            validateDeleteMin(tester, p);

            if (p[1].equals("D")) {
                break;
            }

            RedBlackTree<String, String> tree = createTree();
            for (String t : p) {
                tree.put(t, t);
            }
            String fp = fingerprint(tree);

            if (UNIQUE_TREE.add(fp)) {
                StringBuilder out = new StringBuilder();
                out.append("{");
                for (int i = 0; i < p.length; i++) {
                    if (i > 0) out.append(", ");
                    out.append('"').append(p[i]).append('"');
                }
                out.append("},");
                System.out.println(out);
            }

        }

    }

    private static final Set<String> UNIQUE_TREE = new HashSet<>();

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

        Integer[] order = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            order[i] = i;
        }

        ArrayPermutations<Integer> perms = ArrayPermutations.of(order);
        int tests = 0;

        for (Integer[] ix : perms) {

            // Cap number of tests at 100
            if (tests++ > 100) {
                break;
            }

            RedBlackTree<String, String> tree = createTree();
            for (String t : s) {
                tree.put(t, t);
            }

            int d = s.length / 4 + 1;
            for (int i = 0; i < s.length; i++) {
                String key = s[(ix[i] + d) % s.length];
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

    private static String fingerprint(RedBlackTree<String, String> tree) {
        StringBuilder s = new StringBuilder();
        fingerprint(tree.getRoot(), s);
        return s.toString();
    }

    private static void fingerprint(RedBlackTreeNode<String, String> node, StringBuilder s) {
        s.append("{");
        if (node != null) {
            fingerprint(node.getLeft(), s);
            s.append(node.key);
            if (node.isRed()) {
                s.append(":red");
            }
            fingerprint(node.getRight(), s);
        }
        s.append("}");
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
            validatePut(tester, input);
            validateDeleteMin(tester, input);
            validateDeleteMax(tester, input);
            validateDelete(tester, input);
        }

        // Testing all permutations is overkill
        // validateDeleteMinAllPermutations(tester, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O");

        String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        validatePut(tester, abc);
        validateDeleteMin(tester, abc);
        validateDeleteMax(tester, abc);
        validateDelete(tester, abc);

        String[] all = ALL.toArray(new String[ALL.size()]);
        validatePut(tester, all);
        validateDeleteMin(tester, all);
        validateDeleteMax(tester, all);
        validateDelete(tester, all);

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

    /**
     * Input arrays from which to build trees. Each array produces a unique red-black tree structure.
     */
    private static final String[][] TREES = {

            {"A"},

            {"A", "B"},

            {"A", "B", "C"},

            {"A", "B", "C", "D"},
            {"A", "C", "D", "B"},

            {"A", "B", "C", "D", "E"},
            {"A", "C", "D", "B", "E"},

            {"A", "B", "C", "D", "E", "F"},
            {"A", "B", "C", "E", "F", "D"},
            {"A", "C", "D", "B", "E", "F"},

            {"A", "B", "C", "D", "E", "F", "G"},
            {"A", "B", "C", "E", "F", "D", "G"},
            {"A", "C", "D", "B", "E", "F", "G"},
            {"A", "C", "D", "B", "F", "G", "E"},

            {"A", "B", "C", "D", "E", "F", "G", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F"},
            {"A", "B", "C", "E", "F", "D", "G", "H"},
            {"A", "C", "D", "B", "E", "F", "G", "H"},
            {"A", "C", "D", "B", "F", "G", "E", "H"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "L", "M", "K"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "L", "M", "I"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "L", "M", "K"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "L", "M", "K"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L", "M"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "M"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L", "M"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "M"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "M"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "M"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L", "M"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "M"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "L", "M", "K"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L", "M"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L", "M"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "L", "M", "K"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "M"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "L", "M", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "M"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "L", "M", "K"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "M"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L", "M"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "M"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "M"},
            {"A", "C", "D", "B", "I", "J", "F", "L", "M", "G", "E", "H", "K"},

            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "I", "J", "H", "L", "M", "K", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "L", "M", "I", "N"},
            {"A", "B", "C", "D", "E", "F", "G", "J", "K", "H", "M", "N", "I", "L"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "H", "M", "N", "I", "J"},
            {"A", "B", "C", "D", "E", "F", "G", "K", "L", "I", "M", "N", "J", "H"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "I", "J", "L", "M", "K", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "L", "M", "N"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "J", "K", "I", "M", "N", "L"},
            {"A", "B", "C", "D", "E", "G", "H", "F", "K", "L", "I", "M", "N", "J"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "I", "J", "L", "M", "K", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "J", "K", "I", "M", "N", "L"},
            {"A", "B", "C", "E", "F", "D", "G", "H", "K", "L", "I", "M", "N", "J"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "E", "F", "D", "H", "I", "G", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "L", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "J", "K", "M", "N", "L"},
            {"A", "B", "C", "F", "G", "D", "H", "I", "E", "K", "L", "J", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "K", "L", "M", "N"},
            {"A", "B", "C", "F", "G", "D", "I", "J", "E", "H", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "K", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "D", "I", "J", "E", "F", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "D", "J", "K", "E", "F", "I", "M", "N", "L"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "K", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "E", "I", "J", "F", "D", "L", "M", "K", "N"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "L", "M", "N"},
            {"A", "B", "C", "G", "H", "E", "J", "K", "F", "D", "I", "M", "N", "L"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "L", "M", "N"},
            {"A", "B", "C", "H", "I", "E", "J", "K", "F", "D", "G", "M", "N", "L"},
            {"A", "B", "C", "H", "I", "E", "K", "L", "F", "D", "G", "J", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "I", "J", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "J", "K", "I", "M", "N", "L"},
            {"A", "C", "D", "B", "E", "F", "G", "H", "K", "L", "I", "M", "N", "J"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "J", "K", "M", "N", "L"},
            {"A", "C", "D", "B", "E", "F", "H", "I", "G", "K", "L", "J", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "J", "K", "M", "N", "L"},
            {"A", "C", "D", "B", "F", "G", "E", "H", "I", "K", "L", "J", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "F", "G", "E", "I", "J", "H", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "K", "L", "M", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "I", "J", "F", "L", "M", "K", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "L", "M", "N"},
            {"A", "C", "D", "B", "G", "H", "E", "J", "K", "F", "I", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "L", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "E", "J", "K", "F", "G", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "E", "K", "L", "F", "G", "J", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "L", "M", "N"},
            {"A", "C", "D", "B", "H", "I", "F", "J", "K", "G", "E", "M", "N", "L"},
            {"A", "C", "D", "B", "H", "I", "F", "K", "L", "G", "E", "J", "M", "N"},
            {"A", "C", "D", "B", "I", "J", "F", "K", "L", "G", "E", "H", "M", "N"},
            {"A", "C", "D", "B", "I", "J", "F", "L", "M", "G", "E", "H", "K", "N"},

    };


}
