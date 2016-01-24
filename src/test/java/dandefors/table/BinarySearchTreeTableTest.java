package dandefors.table;

/**
 *
 */
public class BinarySearchTreeTableTest extends OrderedSymbolTableTest {
    @Override
    public BinarySearchTreeTable<String, String> createTable() {
        return new BinarySearchTreeTable<>();
    }

}
