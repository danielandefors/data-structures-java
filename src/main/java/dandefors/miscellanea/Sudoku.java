package dandefors.miscellanea;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Sudoku puzzle.
 */
public class Sudoku {

    public static final int DIMENSION = 9;
    private static final int SECTOR = 3;

    private int[][] matrix = new int[DIMENSION][DIMENSION];

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
        matrix[x][y] = 0;
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
        int[] p = new int[DIMENSION];
        int c = possible(x, y, p);
        if (Arrays.binarySearch(p, 0, c, value) < 0) {
            throw new IllegalArgumentException("value not possible");
        }
        matrix[x][y] = value;
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
        boolean[] set = new boolean[DIMENSION + 1];
        for (int i = 0; i < DIMENSION; i++) {
            if (i != x) set[matrix[i][y]] = true;
            if (i != y) set[matrix[x][i]] = true;
        }
        int sx = (x / SECTOR) * SECTOR;
        int sy = (y / SECTOR) * SECTOR;
        for (int i = 0; i < SECTOR; i++) {
            for (int j = 0; j < SECTOR; j++) {
                if (sx + i != x && sy + j != y) set[matrix[sx + i][sy + j]] = true;
            }
        }
        int c = 0;
        for (int i = 1; i <= DIMENSION; i++) {
            if (!set[i]) p[c++] = i;
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
        return CombinatorialSearch.backtrack(new Solver(this)).isSolved();
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
            return sudoku.possible(free[k].x, free[k].y);
        }

        @Override
        public void makeMove(Integer candidate, int k) {
            sudoku.set(free[k].x, free[k].y, candidate);
        }

        @Override
        public void unmakeMove(Integer candidate, int k) {
            if (!solved) sudoku.clear(free[k].x, free[k].y);
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
