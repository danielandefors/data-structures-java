package dandefors.stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class IntStackTest {

    @Test
    public void testStack() {

        IntStack s = new IntStack();

        assertEquals(0, s.size());
        assertTrue(s.isEmpty());

        for (int i = 0; i < 100; i++) {
            s.push(i);
        }

        assertEquals(100, s.size());
        assertFalse(s.isEmpty());


        for (int i = 99; i >= 0; i--) {
            assertEquals(i, s.pop());
        }


        assertEquals(0, s.size());
        assertTrue(s.isEmpty());


    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmpty() {
        new IntStack().pop();
    }
}
