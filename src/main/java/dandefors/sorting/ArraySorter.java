package dandefors.sorting;

/**
 * Abstraction of a sorting algorithm.
 */
public interface ArraySorter {

    /**
     * Sorts the given array and returns it.
     *
     * @param a The array to sort.
     * @return The same array, but sorted.
     */
    int[] sort(int... a);

}
