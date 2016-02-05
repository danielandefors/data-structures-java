package dandefors.graph;

import dandefors.graph.processors.EdgeType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public abstract class DiGraphTest extends GraphTest {

    abstract DiGraph createDiGraph(int vertices);

    @Test
    public void testInsertDirected() {

        DiGraph g = createDiGraph(3);

        assertEquals(3, g.vertices());
        assertEquals(0, g.edges());

        assertFalse(g.connected(0, 1));
        assertFalse(g.connected(0, 2));
        assertFalse(g.connected(1, 2));
        assertFalse(g.connected(1, 0));
        assertFalse(g.connected(2, 0));
        assertFalse(g.connected(2, 1));

        g.insert(0, 2);

        assertEquals(3, g.vertices());
        assertEquals(1, g.edges());

        assertFalse(g.connected(0, 1));
        assertTrue(g.connected(0, 2));
        assertFalse(g.connected(1, 2));
        assertFalse(g.connected(1, 0));
        assertFalse(g.connected(2, 0));
        assertFalse(g.connected(2, 1));

        g.insert(1, 2);

        assertEquals(3, g.vertices());
        assertEquals(2, g.edges());

        assertFalse(g.connected(0, 1));
        assertTrue(g.connected(0, 2));
        assertTrue(g.connected(1, 2));
        assertFalse(g.connected(1, 0));
        assertFalse(g.connected(2, 0));
        assertFalse(g.connected(2, 1));

    }

    @Test
    public void testBfsDirected() {

        DiGraph g = createDiGraph(10);
        g.insert(0, 2);
        g.insert(0, 1);
        g.insert(1, 9);
        g.insert(1, 4);
        g.insert(2, 8);
        g.insert(2, 4);
        g.insert(4, 5);
        g.insert(8, 4);

        VertexOrder v = g.bfs(0, new VertexOrder(g.vertices()));

        assertArrayEquals(array(0, 1, 2, 4, 9, 8, 5), v.getEarlyVertexOrder());
        assertArrayEquals(array(0, 1, 2, 4, 9, 8, 5), v.getLateVertexOrder());

        assertEquals(8, g.bfs(0, new EdgeCounter()).getEdges());

        g.insert(8, 8);
        assertEquals(9, g.bfs(0, new EdgeCounter()).getEdges());

        g.bfs(0, new AbortStart());
        g.bfs(0, new AbortEdge(4));
        g.bfs(0, new AbortLate(4));

        VertexTimes t = g.bfs(0, new VertexTimes(g.vertices()));
        assertEquals(0, t.getEntryTime(0));
        assertEquals(1, t.getExitTime(0));
        assertEquals(2, t.getEntryTime(1));
        assertEquals(3, t.getExitTime(1));

    }

    @Test
    public void testShortestPathDirected() {


        DiGraph g = createDiGraph(10);
        g.insert(0, 2);
        g.insert(0, 1);
        g.insert(1, 9);
        g.insert(1, 4);
        g.insert(2, 8);
        g.insert(2, 4);
        g.insert(4, 5);
        g.insert(8, 4);

        assertArrayEquals(array(0, 2, 8), g.getShortestUnweightedPath(0, 8));
        assertArrayEquals(array(), g.getShortestUnweightedPath(4, 8));

        g.insert(4, 2);
        assertArrayEquals(array(4, 2, 8), g.getShortestUnweightedPath(4, 8));


    }

    @Test
    public void testDfsDirected() {

        DiGraph g = createDiGraph(10);

        g.insert(0, 1);
        g.insert(1, 4);
        g.insert(5, 4);
        g.insert(1, 2);
        g.insert(2, 8);
        g.insert(8, 9);

        assertEquals(5, g.dfs(0, new EdgeCounter()).getEdges());

        g.insert(9, 9);

        VertexOrder v = g.dfs(0, new VertexOrder(g.vertices()));

        assertArrayEquals(array(0, 1, 2, 8, 9, 4), v.getEarlyVertexOrder());
        assertArrayEquals(array(9, 8, 2, 4, 1, 0), v.getLateVertexOrder());

        assertEquals(6, g.dfs(0, new EdgeCounter()).getEdges());

        g.dfs(0, new AbortStart());
        g.dfs(0, new AbortEarly(0));
        g.dfs(0, new AbortEarly(4));
        g.dfs(0, new AbortEdge(4));
        g.dfs(0, new AbortLate(4));


        VertexTimes t = g.dfs(0, new VertexTimes(g.vertices()));
        assertEquals(0, t.getEntryTime(0));
        assertEquals(11, t.getExitTime(0));
        assertEquals(1, t.getEntryTime(1));
        assertEquals(10, t.getExitTime(1));

    }

    @Test
    public void testAcyclicDirected() {

        DiGraph g = createDiGraph(10);
        assertTrue(g.acyclic());

        g.insert(4, 5);
        assertTrue(g.acyclic());

        g.insert(4, 6);
        assertTrue(g.acyclic());

        g.insert(7, 5);
        assertTrue(g.acyclic());

        g.insert(6, 7);
        assertTrue(g.acyclic());

        g.insert(2, 1);
        assertTrue(g.acyclic());

        g.insert(5, 4);
        assertFalse(g.acyclic());

    }

    @Test
    public void testCountEdgesInCyclesDirected() {

        DiGraph g = createDiGraph(10);

        g.insert(2, 1);
        assertEquals(1, g.dfs(2, new EdgeCounter()).getEdges());

        g.insert(3, 1);
        assertEquals(1, g.dfs(2, new EdgeCounter()).getEdges());

        g.insert(1, 3);
        assertEquals(3, g.dfs(2, new EdgeCounter()).getEdges());

        g.insert(3, 2);
        assertEquals(4, g.dfs(2, new EdgeCounter()).getEdges());

    }


    @Test
    public void testTreeEdgeDirected() {

        DiGraph g = createDiGraph(10);
        g.insert(0, 1);
        g.insert(0, 2);
        g.insert(2, 3);
        g.insert(2, 4);

        EdgeTypes types = g.dfs(0, new EdgeTypes(g.vertices()));

        assertEquals(EdgeType.TREE, types.getType(0, 1));
        assertEquals(EdgeType.TREE, types.getType(0, 2));
        assertEquals(EdgeType.TREE, types.getType(2, 3));
        assertEquals(EdgeType.TREE, types.getType(2, 4));

    }

    @Test
    public void testForwardEdgeDirected() {


        DiGraph g = createDiGraph(10);
        g.insert(0, 4);
        g.insert(0, 1);
        g.insert(0, 2);
        g.insert(2, 3);
        g.insert(2, 4);

        EdgeTypes types = g.dfs(0, new EdgeTypes(g.vertices()));

        assertEquals(EdgeType.FORWARD, types.getType(0, 4));
        assertEquals(EdgeType.TREE, types.getType(0, 1));
        assertEquals(EdgeType.TREE, types.getType(0, 2));
        assertEquals(EdgeType.TREE, types.getType(2, 3));
        assertEquals(EdgeType.TREE, types.getType(2, 4));

    }

    @Test
    public void testBackEdgeDirected() {


        DiGraph g = createDiGraph(10);
        g.insert(0, 1);
        g.insert(0, 2);
        g.insert(2, 3);
        g.insert(2, 4);
        g.insert(4, 0);

        EdgeTypes types = g.dfs(0, new EdgeTypes(g.vertices()));

        assertEquals(EdgeType.BACK, types.getType(4, 0));
        assertEquals(EdgeType.TREE, types.getType(0, 1));
        assertEquals(EdgeType.TREE, types.getType(0, 2));
        assertEquals(EdgeType.TREE, types.getType(2, 3));
        assertEquals(EdgeType.TREE, types.getType(2, 4));

    }

    @Test
    public void testCrossEdgeDirected() {


        DiGraph g = createDiGraph(10);
        g.insert(0, 1);
        g.insert(0, 2);
        g.insert(2, 3);
        g.insert(2, 4);
        g.insert(1, 2);

        EdgeTypes types = g.dfs(0, new EdgeTypes(g.vertices()));

        assertEquals(EdgeType.CROSS, types.getType(1, 2));
        assertEquals(EdgeType.TREE, types.getType(0, 1));
        assertEquals(EdgeType.TREE, types.getType(0, 2));
        assertEquals(EdgeType.TREE, types.getType(2, 3));
        assertEquals(EdgeType.TREE, types.getType(2, 4));

    }

    @Test
    public void testTopologicalOrderDirected() {

        DiGraph g = createDiGraph(10);

        g.insert(1, 2);
        g.insert(1, 5);
        g.insert(2, 3);
        g.insert(2, 4);
        g.insert(3, 4);
        g.insert(3, 7);
        g.insert(4, 5);
        g.insert(4, 6);
        g.insert(5, 6);
        g.insert(6, 7);

        int[] order = g.getTopologicalOrder(1);
        assertArrayEquals(array(1, 2, 3, 4, 5, 6, 7), order);

        int[] all = g.getTopologicalOrder();
        assertArrayEquals(array(9, 8, 1, 2, 3, 4, 5, 6, 7, 0), all);

    }

    @Test(expected = IllegalStateException.class)
    public void testTopologicalOrderWithCycleDirected() {
        DiGraph g = createDiGraph(10);
        g.insert(1, 2);
        g.insert(2, 3);
        g.insert(3, 1);
        g.getTopologicalOrder(1);
    }

    @Test(expected = IllegalStateException.class)
    public void testTopologicalOrderWithCycleGraphDirected() {
        DiGraph g = createDiGraph(10);
        g.insert(1, 2);
        g.insert(2, 3);
        g.insert(3, 1);
        g.getTopologicalOrder();
    }

    @Test
    public void testReverseDirected() {
        DiGraph g = createDiGraph(5);

        g.insert(0, 2);
        g.insert(1, 0);
        g.insert(1, 2);
        g.insert(1, 3);
        g.insert(3, 2);
        g.insert(3, 4);

        assertArrayEquals(array(1, 3, 4, 0, 2), g.getTopologicalOrder());

        DiGraph r = g.reversed();

        assertEquals(g.vertices(), r.vertices());
        assertEquals(g.edges(), r.edges());

        assertTrue(r.connected(0, 1));
        assertTrue(r.connected(2, 0));
        assertTrue(r.connected(2, 1));
        assertTrue(r.connected(2, 3));
        assertTrue(r.connected(3, 1));
        assertTrue(r.connected(4, 3));

        assertArrayEquals(array(4, 2, 3, 0, 1), r.getTopologicalOrder());

    }

    @Test
    public void testStronglyConnectedComponentsDirected() {
        DiGraph g = createDiGraph(8);
        g.insert(0, 1);
        g.insert(1, 2);
        g.insert(1, 3);
        g.insert(1, 4);
        g.insert(2, 1);
        g.insert(3, 0);
        g.insert(3, 5);
        g.insert(3, 7);
        g.insert(4, 5);
        g.insert(5, 6);
        g.insert(6, 4);
        g.insert(7, 5);
        assertEquals(3, g.getStronglyConnectedComponents());
    }

    @Test
    public void testStronglyConnectedComponents2Directed() {
        DiGraph g = createDiGraph(4);
        assertEquals(4, g.getStronglyConnectedComponents());
        g.insert(1, 2);
        assertEquals(4, g.getStronglyConnectedComponents());
        g.insert(3, 1);
        assertEquals(4, g.getStronglyConnectedComponents());
        g.insert(2, 3);
        assertEquals(2, g.getStronglyConnectedComponents());
        g.insert(0, 1);
        assertEquals(2, g.getStronglyConnectedComponents());
        g.insert(2, 0);
        assertEquals(1, g.getStronglyConnectedComponents());
    }


}
