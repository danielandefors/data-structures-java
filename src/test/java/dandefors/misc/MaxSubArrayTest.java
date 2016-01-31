package dandefors.misc;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class MaxSubArrayTest {

    @Test
    public void testLinearSolution() {

        int[] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

        MaxSubArray m = MaxSubArray.findMaxSubArray(a);

        assertEquals(7, m.getFromIndex());
        assertEquals(10, m.getToIndex());
        assertEquals(43, m.getSum());

    }

    @Test
    public void testDivideAndConquerSolution() {

        int[] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

        MaxSubArray m = MaxSubArray.findMaxSubArrayDC(a);

        assertEquals(7, m.getFromIndex());
        assertEquals(10, m.getToIndex());
        assertEquals(43, m.getSum());

    }

}
