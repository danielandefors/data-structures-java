package dandefors.table;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class MoveToFrontTableTest extends SymbolTableTest {

    @Override
    public MoveToFrontTable<String, String> createTable() {
        return new MoveToFrontTable<>();
    }

    @Test
    public void testMoveToFront() {

        MoveToFrontTable<Integer, Integer> t = new MoveToFrontTable<>();

        for (int i = 100; i > 0; i--) {
            t.put(i, i * i);
        }

        assertEquals(new Integer(1), t.keys().iterator().next());

        assertEquals(new Integer(49), t.get(7));
        assertEquals(new Integer(7), t.keys().iterator().next());

        assertEquals(new Integer(144), t.get(12));
        assertEquals(new Integer(12), t.keys().iterator().next());

        Iterator<Integer> itr = t.keys().iterator();
        assertEquals(new Integer(12), itr.next());
        assertEquals(new Integer(7), itr.next());
        assertEquals(new Integer(1), itr.next());
        assertEquals(new Integer(2), itr.next());
        assertEquals(new Integer(3), itr.next());
        assertEquals(new Integer(4), itr.next());
        assertEquals(new Integer(5), itr.next());
        assertEquals(new Integer(6), itr.next());
        assertEquals(new Integer(8), itr.next());

    }

}
