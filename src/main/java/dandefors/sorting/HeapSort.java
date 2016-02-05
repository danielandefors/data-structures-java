package dandefors.sorting;

import dandefors.heap.IntHeap;

/**
 * Sorts the array using a heap data structure.
 * Time complexity: big-Theta n lg n
 */
public class HeapSort implements ArraySorter {

    @Override
    public int[] sort(int... a) {
        IntHeap h = IntHeap.createMinHeap();
        for (int x : a) {
            h.insert(x);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = h.extract();
        }
        return a;
    }

}
