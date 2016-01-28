package dandefors.sorting;

/**
 * Popular but inefficient sorting algorithm. Runs in quadratic time (always).
 */
public class BubbleSort implements ArraySorter {

    @Override
    public int[] sort(int... a) {
        int last = a.length - 1;
        for (int i = 0; i < last; i++) {
            for (int j = last; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                }
            }
        }
        return a;
    }

    private void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

}
