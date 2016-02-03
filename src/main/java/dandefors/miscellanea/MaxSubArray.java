package dandefors.miscellanea;

/**
 * Algorithms to find maximum subarray.
 */
public class MaxSubArray {

    private int fromIndex;
    private int toIndex;
    private int sum;

    public MaxSubArray(int fromIndex, int toIndex, int sum) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.sum = sum;
    }

    /**
     * The index where the maximum subarray starts (inclusive).
     *
     * @return The low index.
     */
    public int getFromIndex() {
        return fromIndex;
    }

    /**
     * The index where the maximum subarray ends (inclusive).
     *
     * @return The high index.
     */
    public int getToIndex() {
        return toIndex;
    }

    /**
     * The sum of all elements in a[fromIndex..toIndex].
     *
     * @return The sum of all elements in the maximum subarray.
     */
    public int getSum() {
        return sum;
    }

    // linear time algorithm

    /**
     * Finds the maximum subarray in `a` using a linear scan.
     * Time complexity: bit-Theta of n
     *
     * @param a The array.
     * @return The maximum subarray.
     */
    public static MaxSubArray findMaxSubArray(int[] a) {

        MaxSubArray maxSoFar = new MaxSubArray(0, 0, a[0]);
        MaxSubArray maxEndingHere = new MaxSubArray(0, 0, a[0]);

        for (int i = 1; i < a.length; i++) {
            /*
            The maximum subarray ending here is either:
                - the sum of the maximum subarray ending at i-1 and the current value,
                - or the current value itself (if the maximum subarray at i-1 was negative)
             */
            if (maxEndingHere.sum < 0) {
                maxEndingHere.sum = a[i];
                maxEndingHere.fromIndex = i;
                maxEndingHere.toIndex = i;
            } else {
                maxEndingHere.sum += a[i];
                maxEndingHere.toIndex = i;
            }
            /*
            The maximum subarray so far is either:
                - the maximum subarray ending here,
                - or the maximum subarray seen so far
             */
            if (maxEndingHere.sum > maxSoFar.sum) {
                maxSoFar.sum = maxEndingHere.sum;
                maxSoFar.fromIndex = maxEndingHere.fromIndex;
                maxSoFar.toIndex = maxEndingHere.toIndex;
            }
        }
        return maxSoFar;
    }

    // linearithmic time divide and conquer algorithm

    /**
     * Finds the maximum subarray in `a` using a divide and conquer algorithm.
     * Time complexity: big-theta of n lg n
     *
     * @param a The array.
     * @return The maximum subarray.
     */
    public static MaxSubArray findMaxSubArrayDC(int[] a) {
        return findMaxSubArrayDC(a, 0, a.length - 1);
    }

    /**
     * Find the maximum subarray in a[low..high].
     *
     * @param a    The array.
     * @param low  The low index (inclusive).
     * @param high The high index (inclusive).
     * @return The maximum subarray.
     */
    private static MaxSubArray findMaxSubArrayDC(int[] a, int low, int high) {
        if (low == high) {
            // base case
            return new MaxSubArray(low, high, a[low]);
        }
        // recursive: divide and conquer
        int mid = low + (high - low) / 2;
        MaxSubArray l = findMaxSubArrayDC(a, low, mid);
        MaxSubArray r = findMaxSubArrayDC(a, mid + 1, high);
        MaxSubArray x = findMaxCrossingSubArray(a, low, mid, high);
        if (l.getSum() > r.getSum() && l.getSum() > x.getSum()) {
            return l;
        } else if (r.getSum() > l.getSum() && r.getSum() > x.getSum()) {
            return r;
        } else {
            return x;
        }

    }

    /**
     * Find the maximum subarray that crosses the subarrays a[low..mid] and a[mid+1..high]
     * (e.g., that contains the indices mid and mid+1).
     *
     * @param a    The array.
     * @param low  The low index .
     * @param mid  The middle index.
     * @param high The high index.
     * @return The maximum subarray that crosses the left and right subarrays.
     */
    private static MaxSubArray findMaxCrossingSubArray(int[] a, int low, int mid, int high) {

        // runs in linear time: big-Theta of n (where n is high-low+1)

        int sum = 0;
        int maxLeft = -1;
        int leftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= low; i--) {
            sum += a[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        sum = 0;
        int maxRight = -1;
        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= high; i++) {
            sum += a[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new MaxSubArray(maxLeft, maxRight, leftSum + rightSum);

    }

}
