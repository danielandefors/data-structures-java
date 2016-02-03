package dandefors.graph;

import org.junit.Test;

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

        g.bfs(0, new AbortEdge(4));
        g.bfs(0, new AbortLate(4));

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

        g.bfs(0, new AbortEdge(4));
        g.bfs(0, new AbortLate(4));

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

        g.dfs(0, new AbortEarly(0));
        g.dfs(0, new AbortEarly(4));
        g.dfs(0, new AbortEdge(4));
        g.dfs(0, new AbortLate(4));


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

        g.dfs(0, new AbortEarly(0));
        g.dfs(0, new AbortEarly(4));
        g.dfs(0, new AbortEdge(4));
        g.dfs(0, new AbortLate(4));

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


}
