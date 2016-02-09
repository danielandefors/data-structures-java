package dandefors.tree;

import dandefors.table.OrderedSymbolTable;
import dandefors.table.OrderedSymbolTableTest;
import org.junit.Test;

import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.*;

/**
 *
 */
public abstract class AbstractTreeTest<
        NODE extends AbstractTreeNode<String, String, NODE>,
        TREE extends AbstractTree<String, String, NODE>
        > extends OrderedSymbolTableTest implements TreeTestData {

    @Override
    protected final OrderedSymbolTable<String, String> createTable() {
        return createTree();
    }

    /**
     * Create a new empty tree to use in the tests.
     */
    protected abstract TREE createTree();


    @Test
    public void testRootIsNullWhenEmpty() {
        TREE tree = createTree();
        NODE node = tree.getRoot();
        assertNull(node);
    }

    @Test
    public void testBinaryTreeStructure1() {
        TREE tree = createTree();
        for (String s : CONST) {
            tree.put(s, "TEST");
        }
        validateBinaryTreeStructure(tree.getRoot(), null, null);
    }

    @Test
    public void testBinaryTreeNodesIteration() {
        TREE tree = createTree();

        assertFalse(tree.nodes().iterator().hasNext());

        for (String s : LOREM) {
            tree.put(s, s);
        }

        String p = null;
        int length = 0;
        for (TreeNode<String, String> node : tree.nodes()) {
            String s = node.getKey();
            if (p != null) {
                assertThat(s, greaterThan(p));
            }
            assertEquals(s, node.getValue());
            p = s;
            length++;
        }

        assertEquals(tree.size(), length);

    }

    protected void validateBinaryTreeStructure(NODE node, String lo, String hi) {
        if (node == null) return;
        if (lo != null) {
            assertThat(node.getKey(), greaterThan(lo));
        }
        if (hi != null) {
            assertThat(node.getKey(), lessThan(hi));
        }
        validateBinaryTreeStructure(node.getLeft(), lo, node.getKey());
        validateBinaryTreeStructure(node.getRight(), node.getKey(), hi);
    }

}
