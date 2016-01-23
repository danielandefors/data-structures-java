package dandefors.table;

/**
 *
 */
public class SequentialSearchTableTest extends SymbolTableTest {

    @Override
    public SymbolTable<String, String> createTable() {
        return new SequentialSearchTable<>();
    }

}
