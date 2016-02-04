package dandefors.graph;

import dandefors.tuple.IntTuple;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 */
public class AdjacencyListTest {


    @Test
    public void testInsertUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(3);

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
        assertTrue(g.connected(2, 0));
        assertFalse(g.connected(2, 1));

        g.insert(1, 2);

        assertEquals(3, g.vertices());
        assertEquals(2, g.edges());

        assertFalse(g.connected(0, 1));
        assertTrue(g.connected(0, 2));
        assertTrue(g.connected(1, 2));
        assertFalse(g.connected(1, 0));
        assertTrue(g.connected(2, 0));
        assertTrue(g.connected(2, 1));

    }

    @Test
    public void testInsertDirected() {

        AdjacencyList g = new AdjacencyList(3, true);

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
    public void testBfsUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(10);
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
    public void testBfsDirected() {

        AdjacencyList g = new AdjacencyList(10, true);
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
    public void testShortestPathUndirected() {


        AdjacencyList g = AdjacencyList.createUndirected(10);
        g.insert(0, 2);
        g.insert(0, 1);
        g.insert(1, 9);
        g.insert(1, 4);
        g.insert(2, 8);
        g.insert(2, 4);
        g.insert(4, 5);
        g.insert(8, 4);

        assertArrayEquals(array(0, 2, 8), g.getShortestUnweightedPath(0, 8));
        assertArrayEquals(array(4, 8), g.getShortestUnweightedPath(4, 8));

    }

    @Test
    public void testShortestPathDirected() {


        AdjacencyList g = new AdjacencyList(10, true);
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
    public void testConnectedComponentsUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(5);

        assertEquals(5, g.getConnectedComponents());

        g.insert(1, 0);
        assertEquals(4, g.getConnectedComponents());

        g.insert(2, 3);
        assertEquals(3, g.getConnectedComponents());

        g.insert(4, 2);
        assertEquals(2, g.getConnectedComponents());

        g.insert(4, 3);
        assertEquals(2, g.getConnectedComponents());

        g.insert(4, 1);
        assertEquals(1, g.getConnectedComponents());

    }


    @Test(expected = UnsupportedOperationException.class)
    public void testConnectedComponentsDirected() {
        AdjacencyList g = AdjacencyList.createDirected(2);
        g.getConnectedComponents();
    }

    @Test
    public void testDfsUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(10);

        g.insert(0, 1);
        g.insert(1, 4);
        g.insert(5, 4);
        g.insert(1, 2);
        g.insert(2, 8);
        g.insert(8, 9);

        assertEquals(6, g.dfs(0, new EdgeCounter()).getEdges());

        g.insert(9, 9);

        VertexOrder v = g.dfs(0, new VertexOrder(g.vertices()));

        assertArrayEquals(array(0, 1, 2, 8, 9, 4, 5), v.getEarlyVertexOrder());
        assertArrayEquals(array(9, 8, 2, 5, 4, 1, 0), v.getLateVertexOrder());

        assertEquals(7, g.dfs(0, new EdgeCounter()).getEdges());

        g.dfs(0, new AbortStart());
        g.dfs(0, new AbortEarly(0));
        g.dfs(0, new AbortEarly(4));
        g.dfs(0, new AbortEdge(4));
        g.dfs(0, new AbortLate(4));


        VertexTimes t = g.dfs(0, new VertexTimes(g.vertices()));
        assertEquals(0, t.getEntryTime(0));
        assertEquals(13, t.getExitTime(0));
        assertEquals(1, t.getEntryTime(1));
        assertEquals(12, t.getExitTime(1));

    }

    @Test
    public void testDfsDirected() {

        AdjacencyList g = AdjacencyList.createDirected(10);

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
    public void testAcyclicUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(10);
        assertTrue(g.acyclic());

        g.insert(4, 5);
        assertTrue(g.acyclic());

        g.insert(4, 6);
        assertTrue(g.acyclic());

        g.insert(7, 5);
        assertTrue(g.acyclic());

        g.insert(6, 7);
        assertFalse(g.acyclic());

        g.insert(2, 1);
        assertFalse(g.acyclic());

    }

    @Test
    public void testAcyclicDirected() {

        AdjacencyList g = AdjacencyList.createDirected(10);
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
    public void testCountEdgesInCyclesUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(10);

        g.insert(2, 1);
        assertEquals(1, g.dfs(2, new EdgeCounter()).getEdges());

        g.insert(3, 1);
        assertEquals(2, g.dfs(2, new EdgeCounter()).getEdges());

        g.insert(3, 2);
        assertEquals(3, g.dfs(2, new EdgeCounter()).getEdges());

    }

    @Test
    public void testCountEdgesInCyclesDirected() {

        AdjacencyList g = AdjacencyList.createDirected(5);

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
    public void testTreeEdgeUndirected() {


        AdjacencyList g = AdjacencyList.createUndirected(5);
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
    public void testBackEdgeUndirected() {


        AdjacencyList g = AdjacencyList.createUndirected(5);
        g.insert(4, 0);
        g.insert(0, 1);
        g.insert(0, 2);
        g.insert(2, 3);
        g.insert(2, 4);

        EdgeTypes types = g.dfs(0, new EdgeTypes(g.vertices()));

        assertEquals(EdgeType.BACK, types.getType(4, 0));
        assertEquals(EdgeType.TREE, types.getType(0, 1));
        assertEquals(EdgeType.TREE, types.getType(0, 2));
        assertEquals(EdgeType.TREE, types.getType(2, 3));
        assertEquals(EdgeType.TREE, types.getType(2, 4));


    }

    @Test
    public void testTreeEdgeDirected() {


        AdjacencyList g = AdjacencyList.createDirected(5);
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


        AdjacencyList g = AdjacencyList.createDirected(5);
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


        AdjacencyList g = AdjacencyList.createDirected(5);
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


        AdjacencyList g = AdjacencyList.createDirected(5);
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
    public void testArticulationVertexUndirected() {

        AdjacencyList g = AdjacencyList.createUndirected(50);

        g.insert(1, 2);
        g.insert(2, 3);
        g.insert(1, 3);
        g.insert(3, 4);
        g.insert(4, 5);
        g.insert(4, 6);
        g.insert(5, 6);
        g.insert(1, 7);
        g.insert(7, 8);
        g.insert(1, 8);
        g.insert(8, 9);
        g.insert(8, 10);
        g.insert(9, 10);
        g.insert(9, 11);
        g.insert(10, 11);

        assertArrayEquals(array(1, 3, 4, 8),  g.getArticulationVertices(1));


        assertArrayEquals(array(),  g.getArticulationVertices(20));

        g.insert(20, 21);
        assertArrayEquals(array(),  g.getArticulationVertices(20));

        g.insert(21, 22);
        assertArrayEquals(array(21),  g.getArticulationVertices(20));
        assertArrayEquals(array(21),  g.getArticulationVertices(21));
        assertArrayEquals(array(21),  g.getArticulationVertices(22));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testArticulationVertexDirected() {

        AdjacencyList g = AdjacencyList.createDirected(12);

//        g.insert(1, 2);
//        g.insert(2, 3);
//        g.insert(1, 3);
//        g.insert(3, 4);
//        g.insert(4, 5);
//        g.insert(4, 6);
//        g.insert(5, 6);
//        g.insert(1, 7);
//        g.insert(7, 8);
//        g.insert(1, 8);
//        g.insert(8, 9);
//        g.insert(8, 10);
//        g.insert(9, 10);
//        g.insert(9, 11);
//        g.insert(10, 11);

        g.getArticulationVertices(1);

    }

    private static int[] array(int... a) {
        return a;
    }

    private static final class VertexOrder implements GraphSearchProcessor {

        private int[] earlyVertexOrder;
        private int earlyCounter;

        private int[] lateVertexOrder;
        private int lateCounter;

        private VertexOrder(int vertices) {
            this.earlyVertexOrder = new int[vertices];
            this.lateVertexOrder = new int[vertices];
        }

        @Override
        public boolean processVertexEarly(int x) {
            earlyVertexOrder[earlyCounter++] = x;
            return true;
        }

        @Override
        public boolean processVertexLate(int x) {
            lateVertexOrder[lateCounter++] = x;
            return true;
        }

        public int[] getEarlyVertexOrder() {
            int[] x = new int[earlyCounter];
            System.arraycopy(earlyVertexOrder, 0, x, 0, x.length);
            return x;
        }

        public int[] getLateVertexOrder() {
            int[] x = new int[lateCounter];
            System.arraycopy(lateVertexOrder, 0, x, 0, x.length);
            return x;
        }
    }

    private static final class EdgeCounter implements GraphSearchProcessor {
        private int edges;

        @Override
        public boolean processEdge(int x, int y) {
            edges++;
            return true;
        }

        public int getEdges() {
            return edges;
        }
    }

    private static final class AbortStart implements GraphSearchProcessor {

        @Override
        public boolean processStartVertex(int x) {
            return false;
        }

        @Override
        public boolean processVertexEarly(int x) {
            throw new IllegalStateException();
        }

        @Override
        public boolean processEdge(int x, int y) {
            throw new IllegalStateException();
        }

        @Override
        public boolean processVertexLate(int x) {
            throw new IllegalStateException();
        }
    }

    private static final class AbortEarly implements GraphSearchProcessor {

        private int target;
        private boolean aborted;

        public AbortEarly(int target) {
            this.target = target;
        }

        @Override
        public boolean processVertexEarly(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            if (x == target) {
                aborted = true;
                return false;
            }
            return true;
        }

        @Override
        public boolean processEdge(int x, int y) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }

        @Override
        public boolean processVertexLate(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }
    }

    private static final class AbortEdge implements GraphSearchProcessor {

        private int target;
        private boolean aborted;

        public AbortEdge(int target) {
            this.target = target;
        }

        @Override
        public boolean processVertexEarly(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }

        @Override
        public boolean processEdge(int x, int y) {
            if (aborted) {
                throw new IllegalStateException();
            }
            if (y == target) {
                aborted = true;
                return false;
            }
            return true;
        }

        @Override
        public boolean processVertexLate(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }
    }


    private static final class AbortLate implements GraphSearchProcessor {

        private int target;
        private boolean aborted;

        public AbortLate(int target) {
            this.target = target;
        }

        @Override
        public boolean processVertexEarly(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }

        @Override
        public boolean processEdge(int x, int y) {
            if (aborted) {
                throw new IllegalStateException();
            }
            return true;
        }

        @Override
        public boolean processVertexLate(int x) {
            if (aborted) {
                throw new IllegalStateException();
            }
            if (x == target) {
                aborted = true;
                return false;
            }
            return true;
        }
    }

    public static final class EdgeTypes extends EdgeClassificationProcessor {

        private final Map<IntTuple, EdgeType> types = new HashMap<>();

        public EdgeTypes(int vertices) {
            super(vertices);
        }

        @Override
        public boolean processEdge(int x, int y, EdgeType type) {
            types.put(new IntTuple(x, y), type);
            return true;
        }

        public EdgeType getType(int x, int y) {
            return types.get(new IntTuple(x, y));
        }

    }

    public static final class VertexTimes extends EdgeClassificationProcessor {

        public VertexTimes(int vertices) {
            super(vertices);
        }

        @Override
        public boolean processEdge(int x, int y, EdgeType type) {
            return true;
        }

    }


}
