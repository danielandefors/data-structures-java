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

        // Copy sub-arrays to not overwrite them
        int[] lo = new int[q - p + 1];
        System.arraycopy(a, p, lo, 0, lo.length);
        int[] hi = new int[r - q];
        System.arraycopy(a, q + 1, hi, 0, hi.length);


        int i = 0;
        int j = 0;
        int k = p;

        // While there are remaining elements in both arrays,
        // compare which element is lower and add it to the result
        while (i < lo.length && j < hi.length) {
            if (lo[i] < hi[j]) {
                a[k++] = lo[i++];
            } else {
                a[k++] = hi[j++];
            }
        }
        // Copy any remaining elements in either array to the result
        while (i < lo.length) {
            a[k++] = lo[i++];
        }
        while (j < hi.length) {
            a[k++] = hi[j++];
        }

    }

}
