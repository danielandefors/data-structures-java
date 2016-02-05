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

    @Test(expected = NoSuchElementException.class)
    public void extractOnEmptyHeap() {
        ArrayHeap.<String>createMaxHeap().remove();
    }

}
