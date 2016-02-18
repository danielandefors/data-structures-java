package dandefors.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class SinglyLinkedListTest {

    @Test
    public void testEmpty() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        assertTrue(l.isEmpty());
        assertEquals(0, l.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFirstEmpty() {
        new SinglyLinkedList<>().getFirst();
    }

    @Test
    public void testGetFirst() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("A");
        assertEquals("A", l.getFirst());
        l.add("B");
        assertEquals("B", l.getFirst());
    }

    @Test
    public void testSize() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("A");
        assertEquals(1, l.size());
        assertFalse(l.isEmpty());
        l.add("B");
        assertEquals(2, l.size());
        assertFalse(l.isEmpty());
    }

    @Test
    public void testReverse() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("A");
        l.add("B");
        assertEquals("B", l.getFirst());
        assertElements(l.iterator(), "B", "A");
        l.reverse();
        assertEquals("A", l.getFirst());
        assertElements(l.iterator(), "A", "B");
    }

    @Test
    public void testSort() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("B");
        l.add("A");
        l.add("C");
        l.add("H");
        l.add("I");
        l.add("E");
        l.add("G");
        l.add("F");
        l.add("D");
        assertElements(l.iterator(), "D", "F", "G", "E", "I", "H", "C", "A", "B");
        l.sort();
        assertElements(l.iterator(), "A", "B", "C", "D", "E", "F", "G", "H", "I");
    }

    @Test
    public void testSortEmpty() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.sort();
        l.add("A");
        assertElements(l.iterator(), "A");
        l.sort();
        assertElements(l.iterator(), "A");
    }

    @Test
    public void testSortEqual() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("A");
        l.add("A");
        l.add("A");
        l.add("B");
        l.add("A");
        l.add("G");
        l.add("A");
        l.add("D");
        assertElements(l.iterator(), "D", "A", "G", "A", "B", "A", "A", "A");
        l.sort();
        assertElements(l.iterator(), "A", "A", "A", "A", "A", "B", "D", "G");
    }

    @Test
    public void testSortWithNull() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("B");
        l.add("A");
        l.add("C");
        l.add("H");
        l.add("I");
        l.add(null);
        l.add("G");
        l.add("F");
        l.add("D");
        assertElements(l.iterator(), "D", "F", "G", null, "I", "H", "C", "A", "B");
        l.sort();
        assertElements(l.iterator(), null, "A", "B", "C", "D", "F", "G", "H", "I");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSortNonComparable() {
        SinglyLinkedList<Object> l = new SinglyLinkedList<>();
        l.add(new Object());
        l.add(new Object());
        l.add(new Object());
        l.sort();
    }

    @Test
    public void testSortWithComparator() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("B");
        l.add("A");
        l.add("c");
        l.add("H");
        l.add("I");
        l.add("E");
        l.add("G");
        l.add("F");
        l.add("D");
        assertElements(l.iterator(), "D", "F", "G", "E", "I", "H", "c", "A", "B");
        l.sort(String.CASE_INSENSITIVE_ORDER);
        assertElements(l.iterator(), "A", "B", "c", "D", "E", "F", "G", "H", "I");
    }

    @SafeVarargs
    public static <T> void assertElements(Iterator<T> iterator, T... expected) {
        for (T x : expected) {
            assertEquals(x, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testToString() {
        SinglyLinkedList<String> l = new SinglyLinkedList<>();
        l.add("O");
        l.add("L");
        l.add("L");
        l.add("E");
        l.add("H");
        assertEquals("[H, E, L, L, O]", l.toString());
    }


}
