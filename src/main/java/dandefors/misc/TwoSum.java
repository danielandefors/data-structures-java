package dandefors.misc;

import java.util.Arrays;

/**
 * Solution to the classic two sum problem that relies on sorting and binary search to get a time complexity of
 * O(n lg n). This problem can be solved in other ways as well, e.g., using a data structure such as a hash table/set
 * or binary search tree.
 */
public class TwoSum {

    /**
     * Check if two integers in `a` sum to `t`.
     *
     * @param a An array of integers.
     * @param t The target value.
     * @return True if two integers in `a` sum to `t`.
     */
    public boolean compute(int[] a, int t) {
        Arrays.sort(a); // O(n lg n)
        for (int i = 0; i < a.length; i++) {
            int x = a[i], s = t - x;
            if (s >= x && Arrays.binarySearch(a, i + 1, a.length, s) > i) { // O(lg n)
                return true;
            }
        }
        return false;
    }

}
