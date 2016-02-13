package dandefors.miscellanea;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class CombinatorialSearchTest {

    @Test
    public void testPermutations() {

        String[] input = {"A", "B", "C"};
        List<String[]> perm = new LinkedList<>();
        CombinatorialSearch.permutations(input, perm::add);
        Iterator<String[]> itr = perm.iterator();

        assertArrayEquals(new String[]{"A", "B", "C"}, itr.next());
        assertArrayEquals(new String[]{"A", "C", "B"}, itr.next());
        assertArrayEquals(new String[]{"B", "A", "C"}, itr.next());
        assertArrayEquals(new String[]{"B", "C", "A"}, itr.next());
        assertArrayEquals(new String[]{"C", "A", "B"}, itr.next());
        assertArrayEquals(new String[]{"C", "B", "A"}, itr.next());
        assertFalse(itr.hasNext());

    }

    @Test
    public void testSubsets() {

        String[] input = {"A", "B", "C"};
        List<String[]> ss = new LinkedList<>();
        CombinatorialSearch.subsets(input, ss::add);

        Iterator<String[]> itr = ss.iterator();
        assertArrayEquals(new String[]{"A", "B", "C"}, itr.next());
        assertArrayEquals(new String[]{"A", "B"}, itr.next());
        assertArrayEquals(new String[]{"A", "C"}, itr.next());
        assertArrayEquals(new String[]{"A"}, itr.next());
        assertArrayEquals(new String[]{"B", "C"}, itr.next());
        assertArrayEquals(new String[]{"B"}, itr.next());
        assertArrayEquals(new String[]{"C"}, itr.next());
        assertArrayEquals(new String[]{}, itr.next());
        assertFalse(itr.hasNext());


    }

}
