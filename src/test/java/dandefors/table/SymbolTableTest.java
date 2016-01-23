package dandefors.table;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

/**
 *
 */
public abstract class SymbolTableTest {

    public abstract SymbolTable<String, String> createTable();

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
        assertThat(t.keys(), hasItems("TEST"));

        t.put("ANOTHER", "NEW VALUE");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertEquals("NEW VALUE", t.get("ANOTHER"));
        assertTrue(t.contains("ANOTHER"));
        assertThat(t.keys(), hasItems("TEST", "ANOTHER"));

        t.put("TEST", "HELLO *NEW* WORLD");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertEquals("HELLO *NEW* WORLD", t.get("TEST"));
        assertTrue(t.contains("TEST"));
        assertThat(t.keys(), hasItems("TEST", "ANOTHER"));

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

        t.delete("NOT_IN_TABLE");

        t.put("TEST", "HELLO WORLD");
        t.put("ANOTHER", "NEW VALUE");
        t.put("FOO", "BAR");
        t.put("BAR", "FOO");

        t.delete("AAA_NOT_IN_TABLE");
        t.delete("ZZZ_NOT_IN_TABLE");

        t.delete("FOO");
        assertEquals(3, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("FOO"));
        assertFalse(t.contains("FOO"));
        assertThat(t.keys(), hasItems("TEST", "ANOTHER", "BAR"));

        t.delete("BAR");
        assertEquals(2, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("BAR"));
        assertFalse(t.contains("BAR"));
        assertThat(t.keys(), hasItems("TEST", "ANOTHER"));

        t.delete("ANOTHER");
        assertEquals(1, t.size());
        assertFalse(t.isEmpty());
        assertNull(t.get("ANOTHER"));
        assertFalse(t.contains("ANOTHER"));
        assertThat(t.keys(), hasItems("TEST"));

        t.delete("TEST");
        assertEquals(0, t.size());
        assertTrue(t.isEmpty());
        assertNull(t.get("TEST"));
        assertFalse(t.contains("TEST"));
        assertFalse(t.keys().iterator().hasNext());
    }

}
