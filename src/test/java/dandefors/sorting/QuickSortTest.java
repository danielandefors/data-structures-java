package dandefors.sorting;

/**
 *
 */
public class QuickSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new QuickSort();
    }
}
