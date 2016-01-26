package dandefors.sorting;

/**
 * Insertion sort algorithm. Best case is O(n). Worst case and average is O(n^2).
 */
public class InsertionSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new InsertionSort();
    }
}
