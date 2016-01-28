package dandefors.misc;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TwoSumTest {

    private int[] array(int... a) {
        return a;
    }

    @Test
    public void testTwoSum() {
        TwoSum t = new TwoSum();
        assertFalse(t.compute(array(1, 4, 5, 8, 15), 10));
        assertTrue(t.compute(array(9, 1, 4, 5, 8, 15), 10));
        assertTrue(t.compute(array(5, 1, 4, 5, 8, 15), 10));
        assertTrue(t.compute(array(-2, -7, 1, 99, -87, 22, 52, 119), 12));
    }
}
