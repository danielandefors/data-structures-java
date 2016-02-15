package dandefors.miscellanea;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Sudoku puzzle.
 */
public class Sudoku {

    public static final int DIMENSION = 9;
    private static final int SECTOR = 3;

    private static final int ALL = 0x1FF;

    /**
     * The Sudoku matrix.
     */
    private int[][] matrix = new int[DIMENSION][DIMENSION];

    /**
     * Keeps track of which numbers are available in a given row.
     */
    private int[] rows = new int[DIMENSION];

    /**
     * Keeps track of which numbers are available in a given column.
     */
    private int[] columns = new int[DIMENSION];

    /**
     * Keeps track of which numbers are available in a given sector.
     */
    private int[] sectors = new int[DIMENSION];

    /**
     * Create an empty Sudoku game.
     */
    public Sudoku() {
        for (int x = 0; x < DIMENSION; x++) {
            rows[x] = ALL;
            columns[x] = ALL;
            sectors[x] = ALL;
        }
    }

    /**
     * Get the value for (x,y).
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The value. 1-9 if set, 0 if not set.
     */
    public int get(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Clear the value for (x,y).
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void clear(int x, int y) {
        int value = matrix[x][y];
        if (value != 0) {
            int k = 1 << (value - 1);
            rows[x] |= k;
            columns[y] |= k;
            sectors[sector(x, y)] |= k;
            matrix[x][y] = 0;
        }
    }

    /**
     * Set or change the value for (x,y).
     *
     * @param x     The x-coordinate.
     * @param y     The y-coordinate.
     * @param value The new value.
     * @throws IllegalArgumentException If the value is invalid for the given square.
     */
    public void set(int x, int y, int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("value must be: 1 <= x <= 9");
        }
        int k = 1 << (value - 1);
        if ((mask(x, y) & k) == 0) {
            throw new IllegalArgumentException("value not allowed");
        }
        int i = ~k;
        rows[x] &= i;
        columns[y] &= i;
        sectors[sector(x, y)] &= i;
        matrix[x][y] = value;
    }

    /**
     * Returns a bit-mask that contains the possible choices for a square.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The bit-mask.
     */
    private int mask(int x, int y) {
        return rows[x] & columns[y] & sectors[sector(x, y)];
    }

    /**
     * Map a square to an index in the sector array.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The corresponding index in the sector array.
     */
    private int sector(int x, int y) {
        return (x / SECTOR) * SECTOR + (y / SECTOR);
    }

    /**
     * Get all possible values for (x,y).
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param p The output array of possible values (must have length equal to {@link #DIMENSION}).
     * @return The number of possible values (in ascending order) that were copied to `p`.
     */
    public int possible(int x, int y, int[] p) {
        if (p.length != DIMENSION) {
            throw new IllegalArgumentException("array must have length: " + DIMENSION);
        }
        int k = 1;
        int m = mask(x, y);
        int c = 0;
        for (int i = 1; i <= DIMENSION; i++) {
            if ((m & k) != 0) p[c++] = i;
            k = k << 1;
        }
        return c;
    }

    /**
     * Get all possible values for (x,y).
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return All possible values.
     */
    public Iterable<Integer> possible(int x, int y) {
        final int[] p = new int[DIMENSION];
        final int length = possible(x, y, p);
        return () -> new Iterator<Integer>() {

            private int index;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return p[index++];
            }
        };
    }

    /**
     * Solve the puzzle.
     *
     * @return True if a solution was found.
     */
    public boolean solve() {
        return new Backtracker().backtrack(new Solver(this)).isSolved();
    }

    /**
     * Solves Sudoku puzzles by searching through all possible moves.
     */
    private static final class Solver implements BacktrackStrategy<Integer> {

        /**
         * A square on the board.
         */
        static class Square {

            final int x;
            final int y;

            public Square(int x, int y) {
                this.x = x;
                this.y = y;
            }

        }

        private Sudoku sudoku;
        private Square[] free;
        private int[] order;
        private boolean[] marked;
        private boolean solved;

        public Solver(Sudoku sudoku) {
            this.sudoku = sudoku;
            Square[] t = new Square[DIMENSION * DIMENSION];
            int count = 0;
            for (int x = 0; x < DIMENSION; x++) {
                for (int y = 0; y < DIMENSION; y++) {
                    if (sudoku.get(x, y) == 0) {
                        t[count++] = new Square(x, y);
                    }
                }
            }
            this.free = Arrays.copyOf(t, count);
            this.order = new int[count];
            this.marked = new boolean[count];
        }

        @Override
        public boolean isSolution(int k) {
            return k == free.length;
        }

        @Override
        public boolean processSolution(int k) {
            solved = true;
            return false;
        }

        @Override
        public Iterable<Integer> getCandidates(int k) {
            // Find the square with the least number of possible choices
            int min = 0, count = Integer.MAX_VALUE;
            for (int i = 0; i < free.length; i++) {
                if (marked[i]) continue;
                int m = sudoku.mask(free[i].x, free[i].y);
                if (m == 0) {
                    // Return early if we found a square without a legal move
                    return Collections::emptyIterator;
                }
                // Loop unrolled intentionally
                int c = 0;
                if ((m & 1) != 0) c++;
                if ((m & 2) != 0) c++;
                if ((m & 4) != 0) c++;
                if ((m & 8) != 0) c++;
                if ((m & 16) != 0) c++;
                if ((m & 32) != 0) c++;
                if ((m & 64) != 0) c++;
                if ((m & 128) != 0) c++;
                if ((m & 256) != 0) c++;
                if (c < count) {
                    count = c;
                    min = i;
                }
            }
            order[k] = min;
            Square s = free[min];
            return sudoku.possible(s.x, s.y);
        }

        @Override
        public void makeMove(Integer candidate, int k) {
            int i = order[k];
            Square s = free[i];
            sudoku.set(s.x, s.y, candidate);
            marked[i] = true;
        }

        @Override
        public void unmakeMove(Integer candidate, int k) {
            if (!solved) {
                int i = order[k];
                Square s = free[i];
                sudoku.clear(s.x, s.y);
                marked[i] = false;
            }
        }

        public boolean isSolved() {
            return solved;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                int value = get(x, y);
                if (value == 0) s.append("_ ");
                else s.append(value).append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }
}
