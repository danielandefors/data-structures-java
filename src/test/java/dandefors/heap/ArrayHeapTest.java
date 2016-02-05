package dandefors.heap;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class ArrayHeapTest {

    @Test
    public void testMinHeap() {

        ArrayHeap<String> h = ArrayHeap.createMinHeap();

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert("ELMO");
        h.insert("COOKIE MONSTER");
        h.insert("BIG BIRD");
        h.insert("GROVER");
        h.insert("ERNIE");
        h.insert("BERT");
        h.insert("OSCAR THE GROUCH");
        h.insert("COUNT VON COUNT");
        h.insert("KERMIT THE FROG");

        assertEquals(9, h.size());
        assertFalse(h.isEmpty());

        assertEquals("BERT", h.remove());
        assertEquals("BIG BIRD", h.remove());
        assertEquals("COOKIE MONSTER", h.remove());
        assertEquals("COUNT VON COUNT", h.remove());
        assertEquals("ELMO", h.remove());
        assertEquals("ERNIE", h.remove());
        assertEquals("GROVER", h.remove());
        assertEquals("KERMIT THE FROG", h.remove());
        assertEquals("OSCAR THE GROUCH", h.remove());

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test
    public void testMaxHeap() {

        ArrayHeap<String> h = ArrayHeap.createMaxHeap();

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert("ELMO");
        h.insert("COOKIE MONSTER");
        h.insert("BIG BIRD");
        h.insert("GROVER");
        h.insert("ERNIE");
        h.insert("BERT");
        h.insert("OSCAR THE GROUCH");
        h.insert("COUNT VON COUNT");
        h.insert("KERMIT THE FROG");

        assertEquals(9, h.size());
        assertFalse(h.isEmpty());

        assertEquals("OSCAR THE GROUCH", h.remove());
        assertEquals("KERMIT THE FROG", h.remove());
        assertEquals("GROVER", h.remove());
        assertEquals("ERNIE", h.remove());
        assertEquals("ELMO", h.remove());
        assertEquals("COUNT VON COUNT", h.remove());
        assertEquals("COOKIE MONSTER", h.remove());
        assertEquals("BIG BIRD", h.remove());
        assertEquals("BERT", h.remove());

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test
    public void testMinHeapWithComparator() {

        ArrayHeap<Int> h = ArrayHeap.createMinHeap((a, b) -> a.value - b.value);

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert(new Int(4));
        h.insert(new Int(6));
        h.insert(new Int(13));
        h.insert(new Int(162));
        h.insert(new Int(88));
        h.insert(new Int(672));
        h.insert(new Int(64));
        h.insert(new Int(5));
        h.insert(new Int(74));
        h.insert(new Int(653));
        h.insert(new Int(22));
        h.insert(new Int(19));

        assertEquals(12, h.size());
        assertFalse(h.isEmpty());

        assertEquals(4, h.remove().value);
        assertEquals(5, h.remove().value);
        assertEquals(6, h.remove().value);
        assertEquals(13, h.remove().value);
        assertEquals(19, h.remove().value);
        assertEquals(22, h.remove().value);
        assertEquals(64, h.remove().value);
        assertEquals(74, h.remove().value);
        assertEquals(88, h.remove().value);
        assertEquals(162, h.remove().value);
        assertEquals(653, h.remove().value);
        assertEquals(672, h.remove().value);

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test
    public void testMaxHeapWithComparator() {

        ArrayHeap<Int> h = ArrayHeap.createMaxHeap((a, b) -> a.value - b.value);

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert(new Int(4));
        h.insert(new Int(6));
        h.insert(new Int(13));
        h.insert(new Int(162));
        h.insert(new Int(88));
        h.insert(new Int(672));
        h.insert(new Int(64));
        h.insert(new Int(5));
        h.insert(new Int(74));
        h.insert(new Int(653));
        h.insert(new Int(22));
        h.insert(new Int(19));

        assertEquals(12, h.size());
        assertFalse(h.isEmpty());

        assertEquals(672, h.remove().value);
        assertEquals(653, h.remove().value);
        assertEquals(162, h.remove().value);
        assertEquals(88, h.remove().value);
        assertEquals(74, h.remove().value);
        assertEquals(64, h.remove().value);
        assertEquals(22, h.remove().value);
        assertEquals(19, h.remove().value);
        assertEquals(13, h.remove().value);
        assertEquals(6, h.remove().value);
        assertEquals(5, h.remove().value);
        assertEquals(4, h.remove().value);

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test(expected = NoSuchElementException.class)
    public void extractOnEmptyHeap() {
        ArrayHeap.<String>createMaxHeap().remove();
    }


    private static final class Int {
        public final int value;

        public Int(int value) {
            this.value = value;
        }

    }

}
