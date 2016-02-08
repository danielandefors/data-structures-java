package dandefors.table;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
public abstract class SymbolTableTest {

    protected abstract SymbolTable<String, String> createTable();

    @Test
    public void testEmptyTable() {
        SymbolTable<String, String> t = createTable();
        assertEquals(0, t.size());
        assertTrue(t.isEmpty());
        assertNull(t.get("TEST"));
        assertFalse(t.contains("TEST"));
        assertFalse(t.keys().iterator().hasNext());
    }

    @Test
    public void testPutGet() {
        SymbolTable<String, String> t = createTable();

        t.put("TEST", "HELLO WORLD");
        assertEquals(1, t.size());
        assertFalse(t.isEmpty());
        assertEquals("HELLO WORLD", t.get("TEST"));
        assertTrue(t.contains("TEST"));
        assertIterableHasItems(t.keys(), "TEST");

        t.put("ANOTHER", "NEW VALUE");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertEquals("NEW VALUE", t.get("ANOTHER"));
        assertTrue(t.contains("ANOTHER"));
        assertIterableHasItems(t.keys(), "TEST", "ANOTHER");

        t.put("TEST", "HELLO *NEW* WORLD");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertEquals("HELLO *NEW* WORLD", t.get("TEST"));
        assertTrue(t.contains("TEST"));
        assertIterableHasItems(t.keys(), "TEST", "ANOTHER");

        assertNull(t.get("AAA_NOT_IN_TABLE"));
        assertNull(t.get("ZZZ_NOT_IN_TABLE"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        SymbolTable<String, String> t = createTable();
        t.put(null, "TEST");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNullKey() {
        SymbolTable<String, String> t = createTable();
        t.get(null);
    }

    @Test
    public void testDelete() {
        SymbolTable<String, String> t = createTable();

        t.delete("X");

        String[] items = {"H", "D", "L", "B", "F", "J", "N", "A", "C", "E", "G", "I", "K", "M", "O"};

        for (String s : items) {
            t.put(s, s);
        }

        assertIterableHasItems(t.keys(), "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O");
        assertEquals(15, t.size());
        assertFalse(t.isEmpty());

        t.delete("0");
        assertFalse(t.contains("0"));
        assertNull(t.get("0"));

        t.delete("X");
        assertFalse(t.contains("X"));
        assertNull(t.get("X"));

        t.delete("D");
        assertEquals(14, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("D"));
        assertFalse(t.contains("D"));
        assertIterableHasItems(t.keys(), "A", "B", "C", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O");

        t.delete("H");
        assertEquals(13, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("H"));
        assertFalse(t.contains("H"));
        assertIterableHasItems(t.keys(), "A", "B", "C", "E", "F", "G", "I", "J", "K", "L", "M", "N", "O");

        t.delete("I");
        assertEquals(12, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("I"));
        assertFalse(t.contains("I"));
        assertIterableHasItems(t.keys(), "A", "B", "C", "E", "F", "G", "J", "K", "L", "M", "N", "O");

        t.delete("L");
        assertEquals(11, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("L"));
        assertFalse(t.contains("L"));
        assertIterableHasItems(t.keys(), "A", "B", "C", "E", "F", "G", "J", "K", "M", "N", "O");

        t.delete("B");
        assertEquals(10, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("B"));
        assertFalse(t.contains("B"));
        assertIterableHasItems(t.keys(), "A", "C", "E", "F", "G", "J", "K", "M", "N", "O");

        t.delete("G");
        assertEquals(9, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("G"));
        assertFalse(t.contains("G"));
        assertIterableHasItems(t.keys(), "A", "C", "E", "F", "J", "K", "M", "N", "O");

        t.delete("E");
        assertEquals(8, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("E"));
        assertFalse(t.contains("E"));
        assertIterableHasItems(t.keys(), "A", "C", "F", "J", "K", "M", "N", "O");

        t.delete("A");
        assertEquals(7, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("A"));
        assertFalse(t.contains("A"));
        assertIterableHasItems(t.keys(), "C", "F", "J", "K", "M", "N", "O");

        t.delete("F");
        assertEquals(6, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("F"));
        assertFalse(t.contains("F"));
        assertIterableHasItems(t.keys(), "C", "J", "K", "M", "N", "O");

        t.delete("C");
        assertEquals(5, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("C"));
        assertFalse(t.contains("C"));
        assertIterableHasItems(t.keys(), "J", "K", "M", "N", "O");

        t.delete("O");
        assertEquals(4, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("O"));
        assertFalse(t.contains("O"));
        assertIterableHasItems(t.keys(), "J", "K", "M", "N");

        t.delete("J");
        assertEquals(3, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("J"));
        assertFalse(t.contains("J"));
        assertIterableHasItems(t.keys(), "K", "M", "N");

        t.delete("M");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("M"));
        assertFalse(t.contains("M"));
        assertIterableHasItems(t.keys(), "K", "N");

        t.delete("K");
        assertEquals(1, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("K"));
        assertFalse(t.contains("K"));
        assertIterableHasItems(t.keys(), "N");

        t.delete("N");
        assertEquals(0, t.size());
        assertTrue(t.isEmpty());
        assertNull(t.get("N"));
        assertFalse(t.contains("N"));
        assertIterableHasItems(t.keys());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNull() {
        createTable().delete(null);
    }

    static void assertIterableHasItems(Iterable<String> c, String... values) {
        Set<String> items = new HashSet<>();
        c.forEach(items::add);
        Set<String> expected = new HashSet<>();
        Arrays.asList(values).forEach(expected::add);

        expected.removeAll(items);
        items.removeAll(Arrays.asList(values));

        if (!expected.isEmpty()) {
            fail(String.format("Expected %s but did not get: %s", Arrays.toString(values), expected));
        }
        if (!items.isEmpty()) {
            fail(String.format("Only expected %s but also got: %s", Arrays.toString(values), items));
        }
    }

}
