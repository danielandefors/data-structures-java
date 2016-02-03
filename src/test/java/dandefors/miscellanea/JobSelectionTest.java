package dandefors.miscellanea;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class JobSelectionTest {

    @Test
    public void testJob() {

        JobSelection.Job j = new JobSelection.Job(0, 1);
        assertEquals(0, j.getStart());
        assertEquals(1, j.getEnd());

        // 100% test code coverage on equals...
        JobSelection.Job k = new JobSelection.Job(0, 1);
        JobSelection.Job l = new JobSelection.Job(1, 2);
        JobSelection.Job m = new JobSelection.Job(2, 2);
        JobSelection.Job n = new JobSelection.Job(0, 0);
        assertTrue(j.equals(j));
        assertTrue(j.equals(k));
        assertFalse(j.equals(l));
        assertFalse(j.equals(m));
        assertFalse(j.equals(n));
        assertFalse(j.equals("Job"));
        assertFalse(j.equals(null));

    }

    @Test
    public void testOptimalSelection() {

        JobSelection.Job[] input = {
                new JobSelection.Job(13, 14),
                new JobSelection.Job(8, 9),
                new JobSelection.Job(0, 19),
                new JobSelection.Job(15, 16),
                new JobSelection.Job(4, 6),
                new JobSelection.Job(9, 16),
                new JobSelection.Job(11, 12),
                new JobSelection.Job(4, 9),
                new JobSelection.Job(2, 4)
        };

        JobSelection.Job[] optimal = {
                new JobSelection.Job(2, 4),
                new JobSelection.Job(4, 6),
                new JobSelection.Job(8, 9),
                new JobSelection.Job(11, 12),
                new JobSelection.Job(13, 14),
                new JobSelection.Job(15, 16)
        };

        JobSelection.Job[] r = new JobSelection().select(input);

        assertArrayEquals(optimal, r);

    }

}
