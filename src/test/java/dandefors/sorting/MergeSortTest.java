package dandefors.sorting;

/**
 *
 */
public class MergeSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new MergeSort();
    }
}
