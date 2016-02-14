package dandefors.miscellanea;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Basic combinatorial search stuff.
 */
public final class CombinatorialSearch {

    /**
     * Iterate through all possible configurations of a search space,
     * using the general backtracking algorithm.
     *
     * @param strategy The backtracking strategy.
     * @param <T>      The input type.
     * @param <BT>     The strategy type.
     * @return The strategy, after the algorithm is complete.
     */
    public static <T, BT extends BacktrackStrategy<T>> BT backtrack(BT strategy) {
        backtrack(0, strategy);
        return strategy;
    }

    /**
     * Recursive method for backtracking a search space.
     *
     * @param k        The current element index.
     * @param strategy The strategy to employ.
     * @param <T>      The input type.
     * @return True if the search should continue.
     */
    private static <T> boolean backtrack(int k, BacktrackStrategy<T> strategy) {
        if (strategy.isSolution(k)) {
            return strategy.processSolution(k);
        } else {
            boolean x;
            for (T t : strategy.getCandidates(k)) {
                strategy.makeMove(t, k);
                x = backtrack(k + 1, strategy);
                strategy.unmakeMove(t, k);
                if (!x) return false;
            }
        }
        return true;
    }

    /**
     * Generates all <code>n!</code> permutations of the input array.
     *
     * @param input    An array of elements.
     * @param consumer Receives all permutations of the array.
     * @param <T>      The element type.
     */
    public static <T> void permutations(T[] input, Consumer<T[]> consumer) {
        backtrack(new Permutations<>(input, consumer)).getPermutations();
    }

    /**
     * A backtracking strategy that generates all permutations of an input array.
     *
     * @param <T> The element type.
     */
    private static final class Permutations<T> implements BacktrackStrategy<Integer> {

        private T[] input;
        private Consumer<T[]> consumer;
        private T[] perm;
        private boolean[] marked;
        private List<T[]> out = new LinkedList<>();

        public Permutations(T[] input, Consumer<T[]> consumer) {
            this.input = input;
            this.consumer = consumer;
            this.perm = input.clone();
            this.marked = new boolean[input.length];
        }

        @Override
        public boolean isSolution(int k) {
            return k == input.length;
        }

        @Override
        public boolean processSolution(int k) {
            consumer.accept(perm.clone());
            return true;
        }

        @Override
        public Iterable<Integer> getCandidates(int k) {
            return () -> new AvailableIndexIterator(marked);
        }

        @Override
        public void makeMove(Integer i, int k) {
            perm[k] = input[i];
            marked[i] = true;
        }

        @Override
        public void unmakeMove(Integer i, int k) {
            marked[i] = false;
        }

        public List<T[]> getPermutations() {
            return out;
        }
    }

    /**
     * Iterates over all available indices.
     */
    private static final class AvailableIndexIterator implements Iterator<Integer> {

        private boolean[] marked;
        private int index;
        private int next;

        public AvailableIndexIterator(boolean[] marked) {
            this.marked = marked;
            this.index = 0;
            this.next = -1;
        }

        @Override
        public boolean hasNext() {
            if (next < index) {
                while (index < marked.length) {
                    if (!marked[index]) {
                        next = index;
                        break;
                    }
                    index++;
                }
            }
            return next == index;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return index++;
        }
    }

    /**
     * Generates all <code>2^n</code> subsets of the input array, including the empty set.
     *
     * @param input    The input array.
     * @param consumer Receives all subsets.
     * @param <T>      The element type.
     */
    public static <T> void subsets(T[] input, Consumer<T[]> consumer) {
        backtrack(new Subsets<>(input, consumer));
    }

    /**
     * A backtracking strategy that generates all subsets of an input array.
     *
     * @param <T> The element type.
     */
    private static final class Subsets<T> implements BacktrackStrategy<Boolean> {

        private static final List<Boolean> CANDIDATES = Arrays.asList(Boolean.TRUE, Boolean.FALSE);

        private T[] input;
        private Consumer<T[]> consumer;

        private T[] subset;
        private int n;

        public Subsets(T[] input, Consumer<T[]> consumer) {
            this.input = input;
            this.consumer = consumer;
            this.subset = input.clone();
        }

        @Override
        public boolean isSolution(int k) {
            return k == input.length;
        }

        @Override
        public boolean processSolution(int k) {
            consumer.accept(Arrays.copyOf(subset, n));
            return true;
        }

        @Override
        public Iterable<Boolean> getCandidates(int k) {
            return CANDIDATES;
        }

        @Override
        public void makeMove(Boolean include, int k) {
            if (include) {
                subset[n] = input[k];
                n = n + 1;
            }
        }

        @Override
        public void unmakeMove(Boolean include, int k) {
            if (include) {
                n = n - 1;
            }
        }
    }

}
