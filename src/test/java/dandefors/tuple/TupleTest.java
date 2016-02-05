package dandefors.tuple;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 *
 */
public class TupleTest {

    Tuple<String, String> r = new Tuple<>("BERT", "ERNIE");
    Tuple<String, String> s = new Tuple<>("BERT", "ERNIE");
    Tuple<String, String> t = new Tuple<>("BERT", "ERNIE");

    Tuple<String, String> u = new Tuple<>(null, "ERNIE");
    Tuple<String, String> v = new Tuple<>(null, null);
    Tuple<String, String> w = new Tuple<>("BERT", null);

    Tuple<String, String> x = new Tuple<>("ELMO", "GROVER");
    Tuple<String, String> y = new Tuple<>("BERT", "ROLF");
    Tuple<String, String> z = new Tuple<>("COOKIE MONSTER", "ERNIE");

    @Test
    public void testGetFirst() {
        assertEquals("BERT", r.getFirst());
        assertEquals("BERT", s.getFirst());
        assertEquals("BERT", t.getFirst());
        assertNull(u.getFirst());
        assertNull(v.getFirst());
        assertEquals("BERT", w.getFirst());
        assertEquals("ELMO", x.getFirst());
        assertEquals("BERT", y.getFirst());
        assertEquals("COOKIE MONSTER", z.getFirst());
    }

    @Test
    public void testGetSecond() {
        assertEquals("ERNIE", r.getSecond());
        assertEquals("ERNIE", s.getSecond());
        assertEquals("ERNIE", t.getSecond());
        assertEquals("ERNIE", u.getSecond());
        assertNull(v.getSecond());
        assertNull(w.getSecond());
        assertEquals("GROVER", x.getSecond());
        assertEquals("ROLF", y.getSecond());
        assertEquals("ERNIE", z.getSecond());
    }

    @Test
    public void testEqualsAndHashCode() {

        assertEquals(r, t);
        assertEquals(t, r);
        assertTrue(r.equals(t));
        assertTrue(t.equals(r));
        assertTrue(t.equals(t));
        assertEquals(r.hashCode(), t.hashCode());


        assertFalse(t.equals(u));
        assertFalse(t.equals(v));
        assertFalse(t.equals(w));

        assertEquals(new Tuple<>(null, "ERNIE"), u);
        assertEquals(new Tuple<>(null, null), v);
        assertEquals(new Tuple<>("BERT", null), w);
        assertEquals(new Tuple<>(null, "ERNIE").hashCode(), u.hashCode());
        assertEquals(new Tuple<>(null, null).hashCode(), v.hashCode());
        assertEquals(new Tuple<>("BERT", null).hashCode(), w.hashCode());

        assertFalse(t.equals(null));
        assertFalse(t.equals(x));
        assertFalse(t.equals(y));
        assertFalse(t.equals(z));
        assertFalse(t.equals(BigDecimal.ONE));
        assertFalse(u.equals(t));
        assertFalse(v.equals(t));
        assertFalse(w.equals(t));
        assertNotEquals(x, t);
        assertNotEquals(y, t);
        assertNotEquals(z, t);

    }
}
