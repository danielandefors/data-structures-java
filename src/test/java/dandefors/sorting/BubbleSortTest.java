package dandefors.sorting;

/**
 *
 */
public class BubbleSortTest extends ArraySorterTest {
    @Override
    ArraySorter createSorter() {
        return new BubbleSort();
    }
}
