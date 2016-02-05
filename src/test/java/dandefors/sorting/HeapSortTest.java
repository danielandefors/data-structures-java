package dandefors.sorting;

/**
 *
 */
public class HeapSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new HeapSort();
    }
}
