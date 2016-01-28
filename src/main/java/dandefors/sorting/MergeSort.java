package dandefors.sorting;

/**
 * Merge sort algorithm.
 * Time complexity: O(n lg n).
 * Space complexity: 2n
 * The sort is stable.
 */
public class MergeSort implements ArraySorter {

    @Override
    public int[] sort(int... a) {
        mergeSort(a, 0, a.length - 1);
        return a;
    }

    protected void mergeSort(int[] a, int p, int r) {
        if (p < r) {
            int q = p + (r - p) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }

    protected void merge(int[] a, int p, int q, int r) {

        // Skip merge if the array is already sorted
        if (a[q] < a[q + 1]) {
            return;
        }

        // Copy this section of the array
        int[] b = new int[r - p + 1];
        System.arraycopy(a, p, b, 0, b.length);

        int low = q - p + 1;
        int i = 0;
        int j = low;
        int k = p;

        // While there are remaining elements in both arrays,
        // compare which element is lower and add it to the result
        while (i < low && j < b.length) {
            if (b[i] < b[j]) {
                a[k++] = b[i++];
            } else {
                a[k++] = b[j++];
            }
        }
        // Copy any remaining elements in either array to the result
        while (i < low) {
            a[k++] = b[i++];
        }
        while (j < b.length) {
            a[k++] = b[j++];
        }

    }

}
