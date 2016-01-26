package dandefors.sorting;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 */
public abstract class ArraySorterTest {

    abstract ArraySorter createSorter();

    static int[] array(int... a) {
        return a;
    }

    @Test
    public void testSortRandomized() {
        ArraySorter s = createSorter();
        assertArrayEquals(
                array(1, 2, 3, 4, 5, 6, 7, 8, 9),
                s.sort(9, 5, 6, 2, 4, 7, 8, 3, 1));
        assertArrayEquals(
                array(-3, 0, 0, 0, 1, 2, 3, 4, 5, 12, 12, 44, 99, 99),
                s.sort(5, 0, 4, 99, -3, 12, 12, 0, 1, 0, 44, 99, 3, 2));
        assertArrayEquals(
                array(1),
                s.sort(1));
        assertArrayEquals(
                array(),
                s.sort());
        assertArrayEquals(
                array(-999, 22, 55, 213, 999, 999),
                s.sort(55, 999, 999, 22, 213, -999));
    }

    @Test
    public void testAlreadySorted() {
        ArraySorter s = createSorter();
        assertArrayEquals(array(1, 2, 3, 99), s.sort(1, 2, 3, 99));
    }

    @Test
    public void testReverseOrder() {
        ArraySorter s = createSorter();
        assertArrayEquals(array(4, 27, 87, 92), s.sort(92, 87, 27, 4));
    }

}
