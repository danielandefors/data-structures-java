package dandefors.heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class IntHeapTest {

    @Test
    public void testMinHeap() {

        IntHeap h = IntHeap.createMinHeap();

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert(4);
        h.insert(2);
        h.insert(12);
        h.insert(99);
        h.insert(52);
        h.insert(1);
        h.insert(12);

        assertEquals(7, h.size());
        assertFalse(h.isEmpty());

        assertEquals(1, h.extract());
        assertEquals(2, h.extract());
        assertEquals(4, h.extract());
        assertEquals(12, h.extract());
        assertEquals(12, h.extract());
        assertEquals(52, h.extract());
        assertEquals(99, h.extract());

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test
    public void testMaxHeap() {

        IntHeap h = IntHeap.createMaxHeap();

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

        h.insert(4);
        h.insert(2);
        h.insert(12);
        h.insert(99);
        h.insert(52);
        h.insert(1);
        h.insert(12);

        assertEquals(7, h.size());
        assertFalse(h.isEmpty());

        assertEquals(99, h.extract());
        assertEquals(52, h.extract());
        assertEquals(12, h.extract());
        assertEquals(12, h.extract());
        assertEquals(4, h.extract());
        assertEquals(2, h.extract());
        assertEquals(1, h.extract());

        assertEquals(0, h.size());
        assertTrue(h.isEmpty());

    }

    @Test
    public void testMinHeapLarge() {

        IntHeap h = IntHeap.createMinHeap();

        int[] input = {73, 95, 84, 10, 94, 26, 78, 13, 89, 88, 17, 64, 16, 11, 64, 30, 7, 29, 59, 50};

        for (int a : input) {
            h.insert(a);
        }

        int[] expected = input.clone();
        Arrays.sort(expected);

        for (int a : expected) {
            assertEquals(a, h.extract());
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void extractOnEmptyHeap() {
        IntHeap.createMaxHeap().extract();
    }

    @Test
    public void testWithBoxing() {
        IntHeap h = IntHeap.createMinHeap();
        h.insert(5);
        h.insert(Integer.valueOf(1));
        h.insert(Integer.valueOf(3));
        h.insert(9);
        h.insert(Integer.valueOf(100));
        assertEquals(1, h.remove().intValue());
        assertEquals(Integer.valueOf(3), h.remove());
        assertEquals(5, h.remove().intValue());
        assertEquals(9, h.remove().intValue());
        assertEquals(100, h.remove().intValue());
    }

}
