package dandefors.sorting;

/**
 *
 */
public class SelectionSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new SelectionSort();
    }
}
