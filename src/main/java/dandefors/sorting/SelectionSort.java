package dandefors.sorting;

/**
 * Selection sort algorithm.
 * Time complexity: O(n^2).
 */
public class SelectionSort implements ArraySorter {

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private int indexOfMinimum(int[] a, int startIndex) {
        int minIndex = startIndex;
        int minValue = a[startIndex];
        for (int i = startIndex + 1; i < a.length; i++) {
            if (a[i] < minValue) {
                minIndex = i;
                minValue = a[i];
            }
        }
        return minIndex;
    }

    @Override
    public int[] sort(int... a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = indexOfMinimum(a, i);
            if (i != j) {
                swap(a, i, j);
            }
        }
        return a;
    }
}
