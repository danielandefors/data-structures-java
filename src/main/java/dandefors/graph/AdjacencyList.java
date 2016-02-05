package dandefors.graph;

import dandefors.queue.IntQueue;
import dandefors.stack.IntStack;

import java.util.Arrays;

/**
 * Array-backed adjacency list.
 */
public abstract class AdjacencyList implements Graph {

    protected final boolean directed;
    protected final EdgeNode[] edges;
    protected final int[] degree;
    protected int edgeCount;

    private AdjacencyList(int vertices, boolean directed) {
        this.edges = new EdgeNode[vertices];
        this.degree = new int[vertices];
        this.directed = directed;
    }

    /**
     * Create an undirected adjacency list graph.
     *
     * @param vertices The number of vertices.
     * @return A new graph.
     */
    public static UnGraph createUnGraph(int vertices) {
        return new Undirected(vertices);
    }

    /**
     * Undirected adjacency list graph.
     */
    public static class Undirected extends AdjacencyList implements UnGraph {

        /**
         * @param vertices The number of vertices in the graph.
         */
        public Undirected(int vertices) {
            super(vertices, false);
        }
    }

    /**
     * Create a directed adjacency list graph.
     *
     * @param vertices The number of vertices.
     * @return A new graph.
     */
    public static DiGraph createDiGraph(int vertices) {
        return new Directed(vertices);
    }

    /**
     * Directed adjacency list graph.
     */
    public static class Directed extends AdjacencyList implements DiGraph {

        /**
         * @param vertices The number of vertices in the graph.
         */
        public Directed(int vertices) {
            super(vertices, true);
        }

        @Override
        public int degree(int x) {
            return degree[x];
        }

        @Override
        public DiGraph reversed() {
            int len = vertices();
            DiGraph r = new Directed(len);
            for (int x = 0; x < len; x++) {
                EdgeNode p = edges[x];
                while (p != null) {
                    r.insert(p.y, x);
                    p = p.next;
                }
            }
            return r;
        }
    }

    @Override
    public int vertices() {
        return edges.length;
    }

    @Override
    public int edges() {
        return edgeCount;
    }

    @Override
    public void insert(int x, int y) {
        insertEdge(x, y);
        if (!directed && x != y) {
            insertEdge(y, x);
        }
        edgeCount++;
    }

    private void insertEdge(int x, int y) {
        edges[x] = new EdgeNode(y, edges[x]);
        degree[x]++;
    }

    @Override
    public boolean connected(int x, int y) {
        EdgeNode n = edges[x];
        while (n != null) {
            if (n.y == y) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    /**
     * An edge between to vertices.
     */
    private static class EdgeNode {

        private final int y;
        private final EdgeNode next;

        public EdgeNode(int y, EdgeNode next) {
            this.y = y;
            this.next = next;
        }

    }

    /**
     * Keeps track of which vertices have been discovered and
     * processed during a graph search operation.
     */
    private static class SearchState {

        private final boolean[] processed;
        private final boolean[] discovered;

        public SearchState(int vertices) {
            processed = new boolean[vertices];
            discovered = new boolean[vertices];
        }

    }

    @Override
    public <T extends GraphSearchProcessor> T bfs(T processor) {
        SearchState s = new SearchState(edges.length);
        for (int i = 0; i < edges.length; i++) {
            if (s.processed[i]) continue;
            if (!bfs(s, i, processor)) break;
        }
        return processor;
    }

    @Override
    public <T extends GraphSearchProcessor> T bfs(int x, T processor) {
        bfs(new SearchState(edges.length), x, processor);
        return processor;
    }

    /**
     * Run a breadth-first search from the given vertex.
     *
     * @param s         The overall search state.
     * @param x         The start vertex.
     * @param processor The search processor.
     * @return True if the search may continue. False if the processor aborted the search.
     */
    private boolean bfs(SearchState s, int x, GraphSearchProcessor processor) {

        if (!processor.processStartVertex(x)) {
            return false;
        }

        IntQueue q = new IntQueue();
        s.discovered[x] = true;
        q.enqueue(x);

        while (!q.isEmpty()) {

            x = q.dequeue();

            if (!processor.processVertexEarly(x)) {
                return false;
            }

            s.processed[x] = true;

            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;

                if ((directed || !s.processed[y] || x == y) && !processor.processEdge(x, y)) {
                    return false;
                }

                if (!s.discovered[y]) {
                    q.enqueue(y);
                    s.discovered[y] = true;
                }

                edge = edge.next;
            }

            if (!processor.processVertexLate(x)) {
                return false;
            }

        }

        return true;

    }


    @Override
    public <T extends GraphSearchProcessor> T dfs(T processor) {
        SearchState s = new SearchState(edges.length);
        for (int i = 0; i < edges.length; i++) {
            if (s.processed[i]) continue;
            if (!dfs(s, i, processor)) break;
        }
        return processor;
    }

    @Override
    public <T extends GraphSearchProcessor> T dfs(int x, T processor) {
        dfs(new SearchState(edges.length), x, processor);
        return processor;
    }

    /**
     * Run a depth-first search from the given vertex.
     *
     * @param s         The overall search state.
     * @param x         The start vertex.
     * @param processor The search processor.
     * @return True if the search may continue. False if the processor aborted the search.
     */
    private boolean dfs(SearchState s, int x, GraphSearchProcessor processor) {

        if (!processor.processStartVertex(x)) {
            return false;
        }

        if (!processor.processVertexEarly(x)) {
            return false;
        }

        int[] parent = new int[edges.length];
        EdgeNode[] search = new EdgeNode[edges.length];

        Arrays.fill(parent, -1);


        IntStack stack = new IntStack();
        s.discovered[x] = true;
        search[x] = edges[x];
        stack.push(x);

        while (!stack.isEmpty()) {

            x = stack.peek();

            EdgeNode edge = search[x];
            if (edge == null) {
                if (!processor.processVertexLate(x)) {
                    return false;
                }
                s.processed[x] = true;
                stack.pop();
                continue;
            }

            search[x] = edge.next;

            int y = edge.y;

            if ((directed || (parent[x] != y && !s.processed[y])) && !processor.processEdge(x, y)) {
                return false;
            }

            if (!s.discovered[y]) {
                if (!processor.processVertexEarly(y)) {
                    return false;
                }
                s.discovered[y] = true;
                search[y] = edges[y];
                parent[y] = x;
                stack.push(y);
            }

        }

        return true;

    }
}
