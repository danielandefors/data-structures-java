package dandefors.miscellanea;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 *
 */
public class SudokuTest {

    @Test
    public void testEmpty() {
        Sudoku s = new Sudoku();
        for (int x = 0; x < Sudoku.DIMENSION; x++) {
            for (int y = 0; y < Sudoku.DIMENSION; y++) {
                assertEquals(0, s.get(x, y));
            }
        }

        s.clear(0, 0);
        assertEquals(0, s.get(0, 0));
    }

    @Test
    public void testGetSet() {

        int c;
        int[] p = new int[Sudoku.DIMENSION];
        Sudoku s = new Sudoku();

        s.set(0, 3, 1);
        assertEquals(1, s.get(0, 3));

        s.set(1, 1, 1);
        assertEquals(1, s.get(1, 1));
        c = s.possible(1, 2, p);
        assertEquals(8, c);
        assertArrayEquals(new int[]{2, 3, 4, 5, 6, 7, 8, 9}, Arrays.copyOf(p, c));

        s.set(1, 2, 6);
        assertEquals(6, s.get(1, 2));
        c = s.possible(1, 0, p);
        assertEquals(7, c);
        assertArrayEquals(new int[]{2, 3, 4, 5, 7, 8, 9}, Arrays.copyOf(p, c));

        s.set(1, 7, 7);
        assertEquals(7, s.get(1, 7));
        c = s.possible(1, 0, p);
        assertEquals(6, c);
        assertArrayEquals(new int[]{2, 3, 4, 5, 8, 9}, Arrays.copyOf(p, c));

        s.set(1, 8, 4);
        assertEquals(4, s.get(1, 8));
        c = s.possible(1, 0, p);
        assertEquals(5, c);
        assertArrayEquals(new int[]{2, 3, 5, 8, 9}, Arrays.copyOf(p, c));

        s.set(6, 2, 9);
        assertEquals(9, s.get(6, 2));
        c = s.possible(2, 2, p);
        assertEquals(6, c);
        assertArrayEquals(new int[]{2, 3, 4, 5, 7, 8}, Arrays.copyOf(p, c));

        s.clear(6, 2);
        assertEquals(0, s.get(6, 2));
        c = s.possible(2, 2, p);
        assertEquals(7, c);
        assertArrayEquals(new int[]{2, 3, 4, 5, 7, 8, 9}, Arrays.copyOf(p, c));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalRow() {
        Sudoku s = new Sudoku();
        s.set(0, 3, 1);
        s.set(0, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalColumn() {
        Sudoku s = new Sudoku();
        s.set(0, 3, 1);
        s.set(7, 3, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalSector() {
        Sudoku s = new Sudoku();
        s.set(0, 3, 1);
        s.set(2, 4, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalValue() {
        Sudoku s = new Sudoku();
        s.set(0, 0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalValueLow() {
        Sudoku s = new Sudoku();
        s.set(0, 0, 0);
    }

    @Test
    public void testPossible() {
        Sudoku s = new Sudoku();
        Iterator<Integer> x = s.possible(0, 0).iterator();
        assertEquals(1, x.next().intValue());
        assertEquals(2, x.next().intValue());
        assertEquals(3, x.next().intValue());
        assertEquals(4, x.next().intValue());
        assertEquals(5, x.next().intValue());
        assertEquals(6, x.next().intValue());
        assertEquals(7, x.next().intValue());
        assertEquals(8, x.next().intValue());
        assertEquals(9, x.next().intValue());
        assertFalse(x.hasNext());
    }

    @Test(expected = NoSuchElementException.class, timeout = 1000)
    public void testPossibleIterator() {
        Iterator<Integer> itr = new Sudoku().possible(0, 0).iterator();
        while (true) {
            itr.next();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPossibleIncorrectArrayLength() {
        new Sudoku().possible(0, 0, new int[11]);
    }

    @Test
    public void testSolveEasy() {

        Sudoku s = new Sudoku();
        s.set(0, 0, 1);

        System.out.println("Before:\n" + s);

        assertTrue(s.solve());

        System.out.println("Solved:\n" + s);

        String answer =
                "1 2 3 4 5 6 7 8 9 \n" +
                "4 5 6 7 8 9 1 2 3 \n" +
                "7 8 9 1 2 3 4 5 6 \n" +
                "2 3 1 6 7 4 8 9 5 \n" +
                "8 7 5 9 1 2 3 6 4 \n" +
                "6 9 4 5 3 8 2 1 7 \n" +
                "3 1 7 2 6 5 9 4 8 \n" +
                "5 4 2 8 9 7 6 3 1 \n" +
                "9 6 8 3 4 1 5 7 2 \n";

        assertEquals(answer, s.toString());

    }

    @Test
    public void testSolveHard() {

        Sudoku s = new Sudoku();
        s.set(0, 7, 1);
        s.set(0, 8, 2);
        s.set(1, 4, 3);
        s.set(1, 5, 5);
        s.set(2, 3, 6);
        s.set(2, 7, 7);
        s.set(3, 0, 7);
        s.set(3, 6, 3);
        s.set(4, 3, 4);
        s.set(4, 6, 8);
        s.set(5, 0, 1);
        s.set(6, 3, 1);
        s.set(6, 4, 2);
        s.set(7, 1, 8);
        s.set(7, 7, 4);
        s.set(8, 1, 5);
        s.set(8, 6, 6);

        System.out.println("Before:\n" + s);

        assertTrue(s.solve());

        System.out.println("Solved:\n" + s);

        String answer =
                "6 7 3 8 9 4 5 1 2 \n" +
                "9 1 2 7 3 5 4 8 6 \n" +
                "8 4 5 6 1 2 9 7 3 \n" +
                "7 9 8 2 6 1 3 5 4 \n" +
                "5 2 6 4 7 3 8 9 1 \n" +
                "1 3 4 5 8 9 2 6 7 \n" +
                "4 6 9 1 2 8 7 3 5 \n" +
                "2 8 7 3 5 6 1 4 9 \n" +
                "3 5 1 9 4 7 6 2 8 \n";

        assertEquals(answer, s.toString());

    }

}
