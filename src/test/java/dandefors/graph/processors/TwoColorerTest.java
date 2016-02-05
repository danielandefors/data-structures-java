package dandefors.graph.processors;

import dandefors.graph.AdjacencyList;
import dandefors.graph.DiGraph;
import dandefors.graph.processors.TwoColorer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class TwoColorerTest {

    @Test
    public void testComplement() {
        assertEquals(TwoColorer.UNCOLORED, TwoColorer.complement(TwoColorer.UNCOLORED));
        assertEquals(TwoColorer.WHITE, TwoColorer.complement(TwoColorer.BLACK));
        assertEquals(TwoColorer.BLACK, TwoColorer.complement(TwoColorer.WHITE));
    }

    @Test
    public void testColoring() {
        DiGraph g = AdjacencyList.createDiGraph(3);
        g.insert(0, 1);
        g.insert(1, 2);
        TwoColorer p = g.dfs(new TwoColorer(g.vertices()));
        assertEquals(TwoColorer.WHITE, p.getColor(0));
        assertEquals(TwoColorer.BLACK, p.getColor(1));
        assertEquals(TwoColorer.WHITE, p.getColor(2));
        assertTrue(p.bipartite());
    }

}
