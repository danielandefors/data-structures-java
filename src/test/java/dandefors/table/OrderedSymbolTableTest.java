package dandefors.table;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public abstract class OrderedSymbolTableTest extends SymbolTableTest {

    @Override
    protected abstract OrderedSymbolTable<String, String> createTable();

    @Test
    public void testMin() {

        OrderedSymbolTable<String, String> t = createTable();

        t.put("C", "TEST C");
        assertEquals("C", t.min());

        t.put("B", "TEST B");
        assertEquals("B", t.min());

        t.put("E", "TEST E");
        assertEquals("B", t.min());

        t.put("A", "TEST A");
        assertEquals("A", t.min());

        t.put("D", "TEST D");
        assertEquals("A", t.min());

        t.delete("A");
        assertEquals("B", t.min());

        t.delete("C");
        assertEquals("B", t.min());

        t.delete("E");
        assertEquals("B", t.min());

        t.delete("B");
        assertEquals("D", t.min());

    }

    @Test(expected = NoSuchElementException.class)
    public void testMinEmpty() {
        OrderedSymbolTable<String, String> t = createTable();
        t.min();
    }

    @Test
    public void testMax() {

        OrderedSymbolTable<String, String> t = createTable();

        t.put("C", "TEST C");
        assertEquals("C", t.max());

        t.put("B", "TEST B");
        assertEquals("C", t.max());

        t.put("E", "TEST E");
        assertEquals("E", t.max());

        t.put("A", "TEST A");
        assertEquals("E", t.max());

        t.put("D", "TEST D");
        assertEquals("E", t.max());

        t.delete("A");
        assertEquals("E", t.max());

        t.delete("C");
        assertEquals("E", t.max());

        t.delete("E");
        assertEquals("D", t.max());

        t.delete("B");
        assertEquals("D", t.max());

    }

    @Test(expected = NoSuchElementException.class)
    public void testMaxEmpty() {
        OrderedSymbolTable<String, String> t = createTable();
        t.max();
    }

    @Test
    public void testFloor() {

        OrderedSymbolTable<String, String> t = createTable();

        assertNull(t.floor("NOTHING"));

        t.put("E", "THIS");
        t.put("F", "IS A");
        t.put("T", "TEST");

        assertNull(t.floor("B"));
        assertEquals("E", t.floor("E"));
        assertEquals("E", t.floor("EE"));
        assertEquals("F", t.floor("F"));
        assertEquals("F", t.floor("FF"));
        assertEquals("F", t.floor("S"));
        assertEquals("T", t.floor("T"));
        assertEquals("T", t.floor("X"));

    }

    @Test
    public void testCeiling() {

        OrderedSymbolTable<String, String> t = createTable();

        assertNull(t.ceiling("NOTHING"));

        t.put("S", "IS A");
        t.put("E", "THIS");
        t.put("T", "TEST");

        assertEquals("E", t.ceiling("B"));
        assertEquals("E", t.ceiling("E"));
        assertEquals("S", t.ceiling("H"));
        assertEquals("S", t.ceiling("S"));
        assertEquals("T", t.ceiling("T"));
        assertNull(t.ceiling("X"));

    }

    @Test
    public void testSelect() {

        OrderedSymbolTable<String, String> t = createTable();

        t.put("789", "TEST 1");
        t.put("123", "TEST 2");
        t.put("456", "TEST 3");

        assertEquals(3, t.size());

        assertEquals("123", t.select(0));
        assertEquals("456", t.select(1));
        assertEquals("789", t.select(2));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectNegIndex() {

        OrderedSymbolTable<String, String> t = createTable();

        t.put("789", "TEST 1");
        t.put("123", "TEST 2");
        t.put("456", "TEST 3");

        t.select(-1);

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testSelectIndexTooLarge() {

        OrderedSymbolTable<String, String> t = createTable();

        t.put("789", "TEST 1");
        t.put("123", "TEST 2");
        t.put("456", "TEST 3");

        t.select(3);

    }

    @Test
    public void testRangeSizeAndIterator() {

        OrderedSymbolTable<String, String> t = createTable();
        t.put("A", "");
        t.put("AA", "");
        t.put("B", "");
        t.put("BB", "");
        t.put("BBB", "");
        t.put("C", "");
        t.put("D", "");
        t.put("DDD", "");
        t.put("EE", "");

        assertIterableEquals(t.keys("C", "C"), "C");
        assertEquals(1, t.size("C", "C"));
        assertIterableEquals(t.keys("B", "C"), "B", "BB", "BBB", "C");
        assertEquals(4, t.size("B", "C"));
        assertIterableEquals(t.keys("DD", "E"), "DDD");
        assertEquals(1, t.size("DD", "E"));
        assertIterableEquals(t.keys("0", "X"), "A", "AA", "B", "BB", "BBB", "C", "D", "DDD", "EE");
        assertEquals(9, t.size("0", "X"));
        assertIterableEquals(t.keys("E", "A"));
        assertEquals(0, t.size("E", "A"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyRangeIterator() {
        OrderedSymbolTable<String, String> t = createTable();
        Iterator<String> itr = t.keys("Z", "ZZ").iterator();
        assertFalse(itr.hasNext());
        itr.next();
    }

    @Test
    public void testDeleteMinMax() {
        OrderedSymbolTable<String, String> t = createTable();

        t.put("A", "");
        t.put("B", "");
        t.put("C", "");
        t.put("D", "");
        t.put("E", "");
        assertEquals("A", t.min());
        assertEquals("E", t.max());

        t.deleteMin();
        assertEquals("B", t.min());
        assertEquals("E", t.max());

        t.deleteMax();
        assertEquals("B", t.min());
        assertEquals("D", t.max());

        t.deleteMax();
        t.deleteMax();
        assertEquals("B", t.min());
        assertEquals("B", t.max());

        t.deleteMin();
        t.deleteMax();
        assertTrue(t.isEmpty());

        t.deleteMin();
        t.deleteMax();
        assertTrue(t.isEmpty());


    }

    static void assertIterableEquals(Iterable<String> c, String... values) {
        Iterator<String> itr = c.iterator();
        for (String s : values) {
            if (!itr.hasNext()) {
                fail(String.format("Expected \"%s\" but was empty", s));
            }
            assertEquals(s, itr.next());
        }
        if (itr.hasNext()) {
            fail(String.format("Expected empty but was \"%s\"", itr.next()));
        }
    }

}
