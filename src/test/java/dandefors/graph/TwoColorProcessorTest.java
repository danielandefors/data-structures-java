package dandefors.graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TwoColorProcessorTest {

    @Test
    public void testComplement() {
        assertEquals(TwoColorProcessor.UNCOLORED, TwoColorProcessor.complement(TwoColorProcessor.UNCOLORED));
        assertEquals(TwoColorProcessor.WHITE, TwoColorProcessor.complement(TwoColorProcessor.BLACK));
        assertEquals(TwoColorProcessor.BLACK, TwoColorProcessor.complement(TwoColorProcessor.WHITE));
    }

}
