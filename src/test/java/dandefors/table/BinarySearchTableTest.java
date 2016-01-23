package dandefors.table;

/**
 *
 */
public class BinarySearchTableTest extends OrderedSymbolTableTest {

    @Override
    public OrderedSymbolTable<String, String> createTable() {
        return new BinarySearchTable<>();
    }

}
