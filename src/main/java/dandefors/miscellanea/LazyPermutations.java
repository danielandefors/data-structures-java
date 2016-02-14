package dandefors.miscellanea;

import java.util.*;

/**
 * Lazy permutation generator.
 */
public class LazyPermutations<T> implements Iterable<T[]> {

    /**
     * Get the factorial of `n`.
     *
     * @param n A number.
     * @return Its factorial.
     */
    private static long factorial(long n) {
        if (n <= 1) return n;
        return factorial(n - 1) * n;
    }

    private final T[] input;
    private final long size;

    /**
     * Get the number of permutations that it can generate for the given input.
     *
     * @return The number of permutations.
     */
    public long size() {
        return size;
    }

    /**
     * Generates all permutations of the input array.
     *
     * @param input The input array.
     * @throws IllegalArgumentException If the input array is null.
     */
    @SafeVarargs
    public LazyPermutations(T... input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        this.input = input.clone();
        this.size = factorial(input.length);
    }

    /**
     * Generates all permutations of the input array.
     *
     * @param input The input array.
     * @param <T>   The element type.
     * @return A generator for all permutations of the input array.
     */
    @SafeVarargs
    public static <T> LazyPermutations<T> of(T... input) {
        return new LazyPermutations<>(input);
    }

    @Override
    public Spliterator<T[]> spliterator() {
        return Spliterators.spliterator(iterator(), size, Spliterator.IMMUTABLE | Spliterator.NONNULL | Spliterator.DISTINCT);
    }

    @Override
    public Iterator<T[]> iterator() {
        return new Iterator<T[]>() {

            private long n;
            private int[] indices = new int[input.length];
            private boolean[] used = new boolean[input.length];
            private T[] next = input.clone();
            private int d;

            {
                Arrays.fill(indices, -1);
            }

            @Override
            public boolean hasNext() {
                return n < size;
            }

            @Override
            public T[] next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                n++;
                nextPermutation();
                return next.clone();
            }

            /**
             * Compute the next permutation.
             */
            private void nextPermutation() {

                if (d == next.length) {
                    d--;
                }

                outer:
                while (true) {

                    // clear
                    int i = indices[d];
                    if (i >= 0) {
                        used[i] = false;
                    }

                    // dive deeper
                    for (int k = i + 1; k < input.length; k++) {
                        if (used[k]) continue;
                        used[k] = true;
                        indices[d] = k;
                        next[d++] = input[k];
                        if (d == next.length) {
                            return;
                        }
                        continue outer;
                    }

                    // backtrack
                    used[indices[d]] = false;
                    indices[d] = -1;
                    d--;

                }
            }

        };
    }

}
