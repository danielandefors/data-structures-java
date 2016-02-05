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

        assertEquals("BERT", h.extract());
        assertEquals("BIG BIRD", h.extract());
        assertEquals("COOKIE MONSTER", h.extract());
        assertEquals("COUNT VON COUNT", h.extract());
        assertEquals("ELMO", h.extract());
        assertEquals("ERNIE", h.extract());
        assertEquals("GROVER", h.extract());
        assertEquals("KERMIT THE FROG", h.extract());
        assertEquals("OSCAR THE GROUCH", h.extract());

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

        assertEquals("OSCAR THE GROUCH", h.extract());
        assertEquals("KERMIT THE FROG", h.extract());
        assertEquals("GROVER", h.extract());
        assertEquals("ERNIE", h.extract());
        assertEquals("ELMO", h.extract());
        assertEquals("COUNT VON COUNT", h.extract());
        assertEquals("COOKIE MONSTER", h.extract());
        assertEquals("BIG BIRD", h.extract());
        assertEquals("BERT", h.extract());

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

        assertEquals(4, h.extract().value);
        assertEquals(5, h.extract().value);
        assertEquals(6, h.extract().value);
        assertEquals(13, h.extract().value);
        assertEquals(19, h.extract().value);
        assertEquals(22, h.extract().value);
        assertEquals(64, h.extract().value);
        assertEquals(74, h.extract().value);
        assertEquals(88, h.extract().value);
        assertEquals(162, h.extract().value);
        assertEquals(653, h.extract().value);
        assertEquals(672, h.extract().value);

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

        assertEquals(672, h.extract().value);
        assertEquals(653, h.extract().value);
        assertEquals(162, h.extract().value);
        assertEquals(88, h.extract().value);
        assertEquals(74, h.extract().value);
        assertEquals(64, h.extract().value);
        assertEquals(22, h.extract().value);
        assertEquals(19, h.extract().value);
        assertEquals(13, h.extract().value);
        assertEquals(6, h.extract().value);
        assertEquals(5, h.extract().value);
        assertEquals(4, h.extract().value);

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test(expected = NoSuchElementException.class)
    public void extractOnEmptyHeap() {
        ArrayHeap.<String>createMaxHeap().extract();
    }


    private static final class Int {
        public final int value;

        public Int(int value) {
            this.value = value;
        }

    }

}
