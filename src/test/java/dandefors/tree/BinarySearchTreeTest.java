package dandefors.tree;

/**
 *
 */
public class BinarySearchTreeTest
        extends AbstractTreeTest<
                BinarySearchTreeNode<String, String>,
        BinarySearchTree<String, String>> {

    @Override
    protected BinarySearchTree<String, String> createTree() {
        return new BinarySearchTree<>();
    }


}
