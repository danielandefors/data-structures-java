package dandefors.stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class LinkedStackTest {

    @Test
    public void testLinkedStack() {

        Stack<String> s = new LinkedStack<>();

        assertEquals(0, s.size());
        assertTrue(s.isEmpty());

        s.push("A");

        assertEquals(1, s.size());
        assertFalse(s.isEmpty());
        assertEquals("A", s.peek());

        s.push("B");

        assertEquals(2, s.size());
        assertFalse(s.isEmpty());
        assertEquals("B", s.peek());

        assertEquals("B", s.pop());
        assertEquals("A", s.pop());

        assertEquals(0, s.size());
        assertTrue(s.isEmpty());

    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmpty() {
        new LinkedStack<>().pop();
    }

    @Test(expected = NoSuchElementException.class)
    public void testPeekEmpty() {
        new LinkedStack<>().peek();
    }


    @Test
    public void testReverse() {

        LinkedStack<String> s = new LinkedStack<>();

        s.push("A");
        s.push("B");
        s.push("C");
        s.push("D");

        s.reverse();

        assertEquals("A", s.pop());
        assertEquals("B", s.pop());
        assertEquals("C", s.pop());
        assertEquals("D", s.pop());

    }

}
