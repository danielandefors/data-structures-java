package dandefors.queue;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class IntQueueTest {

    @Test
    public void testQueue() {

        IntQueue q = new IntQueue();

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        for (int i = 0; i < 20; i++) {
            q.enqueue(i);
        }

        assertFalse(q.isEmpty());
        assertEquals(20, q.size());

        for (int i = 0; i < 5; i++) {
            assertEquals(i, q.dequeue());
        }

        assertFalse(q.isEmpty());
        assertEquals(15, q.size());

        for (int i = 20; i < 100; i++) {
            q.enqueue(i);
        }

        assertFalse(q.isEmpty());
        assertEquals(95, q.size());

        for (int i = 5; i < 100; i++) {
            assertEquals(i, q.dequeue());
        }

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

    }

    @Test
    public void testShrinkTwo() {
        // Test case that triggers a particular behavior in the shrink() function
        IntQueue q = new IntQueue();
        for (int i = 0; i < 8; i++) {
            q.enqueue(i);
        }
        assertEquals(0, q.dequeue());
        q.enqueue(8);
        for (int i = 1; i < 9; i++) {
            assertEquals(i, q.dequeue());
        }
    }


    @Test
    public void testNewQueueFromArray() {

        int[] input = {5, 7, 2, 8, 8, 4};

        IntQueue q = new IntQueue(input);

        assertFalse(q.isEmpty());
        assertEquals(input.length, q.size());

        for (int element : input) {
            assertEquals(element, q.dequeue());
        }

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        // make sure that the queue isn't wrapping the input array
        int[] x = {10};
        IntQueue queue = new IntQueue(x);
        x[0] = 1; // change the value of the input array
        // make sure that the queue returns the original value
        assertEquals(10, queue.dequeue());

    }


    @Test
    public void testNewQueueFromList() {

        int[] input = {5, 7, 2, 8, 8, 4};

        IntQueue q = new IntQueue(toList(input));

        assertFalse(q.isEmpty());
        assertEquals(input.length, q.size());

        for (int element : input) {
            assertEquals(element, q.dequeue());
        }

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

    }


    @Test
    public void testNewQueueFromIterable() {

        int[] input = {5, 7, 2, 8, 8, 4};

        IntQueue q = new IntQueue(new ArrayIterable(input));

        assertFalse(q.isEmpty());
        assertEquals(input.length, q.size());

        for (int element : input) {
            assertEquals(element, q.dequeue());
        }

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

    }


    @Test
    public void testNewQueueFromSpliterable() {

        int[] input = {5, 7, 2, 8, 8, 4};

        IntQueue q = new IntQueue(new ArraySpliterable(input));

        assertFalse(q.isEmpty());
        assertEquals(input.length, q.size());

        for (int element : input) {
            assertEquals(element, q.dequeue());
        }

        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueOnEmpty() {
        new IntQueue().dequeue();
    }

    @Test
    public void testToArray() {

        IntQueue q = new IntQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        assertArrayEquals(new int[]{1, 2, 3}, q.toArray());
        assertEquals(1, q.dequeue());

        q.enqueue(4);
        q.enqueue(5);

        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());

        assertArrayEquals(new int[]{4, 5}, q.toArray());

    }

    private static List<Integer> toList(int... a) {
        List<Integer> list = new ArrayList<>(a.length);
        for (int element : a) {
            list.add(element);
        }
        return list;
    }

    private static class ArrayIterable implements Iterable<Integer> {

        protected final int[] elements;

        public ArrayIterable(int[] elements) {
            this.elements = elements;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {

                private int index;

                @Override
                public boolean hasNext() {
                    return index < elements.length;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return elements[index++];
                }
            };
        }

    }


    private static class ArraySpliterable extends ArrayIterable {

        public ArraySpliterable(int[] elements) {
            super(elements);
        }

        @Override
        public Spliterator<Integer> spliterator() {
            return Spliterators.spliterator(elements, 0);
        }
    }


}
