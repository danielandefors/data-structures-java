package dandefors.tuple;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 *
 */
public class IntTupleTest {

    @Test
    public void testIntTuple() {

        IntTuple t = new IntTuple(1, 2);
        assertEquals(1, t.getFirst());
        assertEquals(2, t.getSecond());

        IntTuple r = new IntTuple(1, 2);
        assertEquals(r, t);
        assertEquals(t, r);
        assertTrue(r.equals(t));
        assertTrue(t.equals(r));
        assertTrue(t.equals(t));
        assertEquals(r.hashCode(), t.hashCode());

        IntTuple x = new IntTuple(4, 5);
        IntTuple y = new IntTuple(1, 3);
        IntTuple z = new IntTuple(6, 2);
        assertFalse(t.equals(null));
        assertFalse(t.equals(x));
        assertFalse(t.equals(y));
        assertFalse(t.equals(z));
        assertFalse(t.equals(BigDecimal.ONE));
        assertNotEquals(x, t);
        assertNotEquals(y, t);
        assertNotEquals(z, t);

    }
}
