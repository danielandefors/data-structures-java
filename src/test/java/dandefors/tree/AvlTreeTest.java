package dandefors.tree;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.Assert.*;

/**
 *
 */
public class AvlTreeTest extends AbstractTreeTest<AvlTreeNode<String, String>, AvlTree<String, String>> {

    @Override
    protected AvlTree<String, String> createTree() {
        return new AvlTree<>();
    }

    @Test
    public void testAvlTreeInvariants() {
        for (String[] input : TREES) {
            validateAvlTree(input);
        }
        validateAvlTree(LOREM);
    }

    private void validateAvlTree(String... input) {
        AvlTree<String, String> tree = createTree();
        for (String s : input) {
            tree.put(s, s);
            validateAvlTreeInvariants(tree.getRoot());
        }
        for (String s : input) {
            tree.delete(s);
            validateAvlTreeInvariants(tree.getRoot());
        }
        assertTrue(tree.isEmpty());
    }

    private static int validateAvlTreeInvariants(AvlTreeNode<?, ?> node) {
        if (node == null) return -1;
        int lh = validateAvlTreeInvariants(node.getLeft());
        int rh = validateAvlTreeInvariants(node.getRight());
        int h = 1 + Math.max(lh, rh);
        int balance = rh - lh;
        assertThat(node.height, equalTo(h));
        assertThat(balance, allOf(lessThanOrEqualTo(1), greaterThanOrEqualTo(-1)));
        return h;
    }

}
