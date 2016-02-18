package dandefors.miscellanea;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class InterleaverTest {

    private static class ReversibleAdapter<T> implements ReversibleIterator<T> {

        private ListIterator<T> iterator;

        @SafeVarargs
        public ReversibleAdapter(T... values) {
            this(Arrays.asList(values));
        }

        public ReversibleAdapter(List<T> list) {
            this(list.listIterator());
        }

        public ReversibleAdapter(ListIterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        @Override
        public T previous() {
            return iterator.previous();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }
    }

    @Test
    public void testAdapter() {
        ReversibleAdapter<String> a = new ReversibleAdapter<>("A", "B", "C");
        assertEquals("A", a.next());
        assertEquals("A", a.previous());
        assertEquals("A", a.next());
        assertEquals("B", a.next());
    }

    @Test
    public void testEmpty() {
        Interleaver<String> interleaver = new Interleaver<>();
        assertFalse(interleaver.hasNext());
        assertFalse(interleaver.hasPrevious());
        interleaver = new Interleaver<>(new ReversibleAdapter<>());
        assertFalse(interleaver.hasNext());
        assertFalse(interleaver.hasPrevious());
    }

    @Test
    public void testInterleaver() {

        ReversibleAdapter<String> a = new ReversibleAdapter<>("1", "2", "3");
        ReversibleAdapter<String> b = new ReversibleAdapter<>("Z");
        ReversibleAdapter<String> c = new ReversibleAdapter<>("a", "b", "c");

        Interleaver<String> interleaver = new Interleaver<>(a, b, c);

        assertEquals("1", interleaver.next());
        assertEquals("Z", interleaver.next());
        assertEquals("a", interleaver.next());
        assertEquals("2", interleaver.next());
        assertEquals("b", interleaver.next());
        assertEquals("3", interleaver.next());
        assertEquals("c", interleaver.next());
        assertFalse(interleaver.hasNext());


        assertEquals("c", interleaver.previous());
        assertEquals("3", interleaver.previous());
        assertEquals("b", interleaver.previous());
        assertEquals("2", interleaver.previous());
        assertEquals("a", interleaver.previous());
        assertEquals("Z", interleaver.previous());
        assertEquals("1", interleaver.previous());
        assertFalse(interleaver.hasPrevious());

        assertEquals("1", interleaver.next());
        assertEquals("Z", interleaver.next());
        assertEquals("a", interleaver.next());
        assertEquals("2", interleaver.next());
        assertTrue(interleaver.hasNext());
        assertTrue(interleaver.hasPrevious());
        assertEquals("2", interleaver.previous());
        assertEquals("a", interleaver.previous());
        assertEquals("Z", interleaver.previous());
        assertEquals("1", interleaver.previous());
        assertFalse(interleaver.hasPrevious());


    }

    @Test(expected = NoSuchElementException.class)
    public void testHasNextError() {
        ReversibleAdapter<String> a = new ReversibleAdapter<>("1", "2", "3");
        ReversibleAdapter<String> b = new ReversibleAdapter<>("a");
        Interleaver<String> interleaver = new Interleaver<>(a, b);
        assertEquals("1", interleaver.next());
        assertEquals("a", interleaver.next());
        assertEquals("2", interleaver.next());
        assertEquals("3", interleaver.next());
        assertFalse(interleaver.hasNext());
        interleaver.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testHasPreviousError() {
        ReversibleAdapter<String> a = new ReversibleAdapter<>("1", "2", "3");
        ReversibleAdapter<String> b = new ReversibleAdapter<>("a");
        Interleaver<String> interleaver = new Interleaver<>(a, b);
        assertEquals("1", interleaver.next());
        assertEquals("a", interleaver.next());
        assertEquals("a", interleaver.previous());
        assertEquals("1", interleaver.previous());
        assertFalse(interleaver.hasPrevious());
        interleaver.previous();
    }

}
