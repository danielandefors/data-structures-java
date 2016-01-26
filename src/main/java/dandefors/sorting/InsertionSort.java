package dandefors.sorting;

/**
 * Insertion sort.
 * Best case: O(n). Worst/average case: O(n^2).
 */
public class InsertionSort implements ArraySorter {

    private void insert(int[] a, int rightIndex, int value) {
        int i = rightIndex;
        for (; i >= 0 && a[i] > value; i--) {
            a[i + 1] = a[i];
        }
        a[i + 1] = value;
    }

    @Override
    public int[] sort(int... a) {
        for (int i = 1; i < a.length; i++) {
            insert(a, i - 1, a[i]);
        }
        return a;
    }

}
