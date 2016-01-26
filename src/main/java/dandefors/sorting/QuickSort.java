package dandefors.sorting;

/**
 * Quick sort algorithm.
 * Average/best case: O(n lg n). Worst case: O(n^2).
 * Sorts the array in-place.
 * The sort is unstable.
 */
public class QuickSort implements ArraySorter {

    @Override
    public int[] sort(int... a) {
        quickSort(a, 0, a.length - 1);
        return a;
    }

    protected void quickSort(int[] a, int p, int r) {
        if (p < r) {
            // Partition the array around a pivot element
            int q = partition(a, p, r);
            // Recursively sort each partition
            quickSort(a, p, q - 1);
            quickSort(a, q + 1, r);
        }
    }

    private int partition(int[] a, int p, int r) {
        // Choose a random pivot element:
        // Which gives us a 50% chance of a 3-to-1 split
        // Note: it would be better to take the median of a few random elements
        int m = p + (int) (Math.random() * (r - p + 1));
        // Swap the median into the last position
        if (m != r) {
            swap(a, m, r);
        }
        // Partition the array around the pivot
        int q = p;
        for (int i = p; i < r; i++) {
            if (a[i] < a[r]) {
                swap(a, i, q++);
            }
        }
        // Put the pivot between the two partitions
        swap(a, q, r);
        return q;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
