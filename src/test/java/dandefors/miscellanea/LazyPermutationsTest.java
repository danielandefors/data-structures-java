package dandefors.miscellanea;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class LazyPermutationsTest {

    /*
    The timeout on each test is there because of the subtle logic
    in the permutation generator method. It's super easy to cause
    infinite loops if not careful when modifying the code.
     */

    @Test(timeout = 200)
    public void testArray0() {
        LazyPermutations<String> perm = new LazyPermutations<>();
        assertEquals(0, perm.size());
        assertFalse(perm.iterator().hasNext());
    }


    @Test(timeout = 200)
    public void testArray1() {
        LazyPermutations<String> perm = new LazyPermutations<>("A");
        Iterator<String[]> itr = perm.iterator();
        assertArrayEquals(array("A"), itr.next());
        assertFalse(itr.hasNext());
    }


    @Test(timeout = 200)
    public void testArray2() {
        LazyPermutations<String> perm = new LazyPermutations<>("A", "B");
        Iterator<String[]> itr = perm.iterator();
        assertArrayEquals(array("A", "B"), itr.next());
        assertArrayEquals(array("B", "A"), itr.next());
        assertFalse(itr.hasNext());
    }


    @Test(timeout = 200)
    public void testArray3() {
        LazyPermutations<String> perm = new LazyPermutations<>("A", "B", "C");
        assertEquals(6, perm.size());
        Iterator<String[]> itr = perm.iterator();
        assertArrayEquals(array("A", "B", "C"), itr.next());
        assertArrayEquals(array("A", "C", "B"), itr.next());
        assertArrayEquals(array("B", "A", "C"), itr.next());
        assertArrayEquals(array("B", "C", "A"), itr.next());
        assertArrayEquals(array("C", "A", "B"), itr.next());
        assertArrayEquals(array("C", "B", "A"), itr.next());
        assertFalse(itr.hasNext());

        assertEquals(6, perm.spliterator().estimateSize());
    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void testNextOnEmpty() {
        new LazyPermutations<>().iterator().next();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        String[] input = null;
        LazyPermutations<String> perm = new LazyPermutations<>(input);
        perm.size();
    }


    private static String[] array(String... s) {
        return s;
    }

}
