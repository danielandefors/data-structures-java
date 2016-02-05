package dandefors.graph;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testColoring() {
        DiGraph g = AdjacencyList.createDiGraph(3);
        g.insert(0, 1);
        g.insert(1, 2);
        TwoColorProcessor p = g.dfs(new TwoColorProcessor(g.vertices()));
        assertEquals(TwoColorProcessor.WHITE, p.getColor(0));
        assertEquals(TwoColorProcessor.BLACK, p.getColor(1));
        assertEquals(TwoColorProcessor.WHITE, p.getColor(2));
        assertTrue(p.bipartite());
    }

}
