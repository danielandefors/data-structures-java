package dandefors.sorting;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 */
public abstract class ArraySorterTest {

    abstract ArraySorter createSorter();

    static int[] array(int... a) {
        return a;
    }

    private static IntTestData DATA_10K = new IntTestData("sort-10K.txt");

//    private static IntTestData DATA_100K = new IntTestData("sort-100K.txt");

//    private static IntTestData DATA_1M = new IntTestData("sort-1M.txt");

    @Test
    public void testSortSimple() {
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
    public void testSort10K() throws IOException {
        ArraySorter s = createSorter();
        testWithData(s, DATA_10K);
    }

//    @Test
//    public void testSort100K() throws IOException {
//        ArraySorter s = createSorter();
//        testWithData(s, DATA_100K);
//        // on my system:
//        // bubble sort ~ 20 seconds
//        // selection sort ~ 3 seconds
//        // insertion sort ~ 1 seconds
//        // merge sort ~ 0.02 seconds
//        // quick sort ~ 0.03 seconds
//    }

//    @Test
//    public void testSort1M() throws IOException {
//        ArraySorter s = createSorter();
//        testWithData(s, DATA_1M);
//        // on my system:
//        // bubble sort ~ 34 minutes
//        // selection sort ~ 6 minutes
//        // insertion sort ~ 2 minutes
//        // merge sort ~ 0.18 seconds
//        // quick sort ~ 0.14 seconds
//    }

    private static void testWithData(ArraySorter s, IntTestData data) {
        assertArrayEquals(data.getExpected(), s.sort(data.getData()));
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
