package dandefors.table;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class BinarySearchTableTest extends OrderedSymbolTableTest {

    @Override
    public OrderedSymbolTable<String, String> createTable() {
        return new BinarySearchTable<>();
    }

    @Test
    public void testLoadFactor() {


        BinarySearchTable<Integer, String> t = new BinarySearchTable<>();

        for (int i = 0, len = 2 << 10; i < len; i++) {
            t.put(i, "TEST");
            assertLoadFactor(t);
        }

        while (!t.isEmpty()) {
            assertLoadFactor(t);
            t.deleteMax();
        }


    }

    private void assertLoadFactor(BinarySearchTable<Integer, String> t) {
        float f = t.getLoadFactor();
        assertTrue(f + " was not <= 1", f <= 1.00f);
        assertTrue(f + " was not >= .25", f >= 0.25f);
    }


}
