package dandefors.graph;

import dandefors.queue.IntQueue;
import dandefors.stack.IntStack;

import java.util.Arrays;

/**
 * Array-backed adjacency list.
 */
public class AdjacencyList implements DiGraph {

    private final boolean directed;
    private final EdgeNode[] edges;
    private final int[] degree;
    private int edgeCount;

    public AdjacencyList(int vertices, boolean directed) {
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
    public static AdjacencyList createUndirected(int vertices) {
        return new AdjacencyList(vertices, false);
    }

    /**
     * Create a directed adjacency list graph.
     *
     * @param vertices The number of vertices.
     * @return A new graph.
     */
    public static AdjacencyList createDirected(int vertices) {
        return new AdjacencyList(vertices, true);
    }

    @Override
    public boolean directed() {
        return directed;
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

    private static class EdgeNode {

        private int y;
        private EdgeNode next;

        public EdgeNode(int y, EdgeNode next) {
            this.y = y;
            this.next = next;
        }

    }

    @Override
    public <T extends GraphSearchProcessor> T bfs(int x, T processor) {

        if (!processor.processStartVertex(x)) {
            return processor;
        }

        boolean[] processed = new boolean[edges.length];
        boolean[] discovered = new boolean[edges.length];

        IntQueue q = new IntQueue();
        discovered[x] = true;
        q.enqueue(x);

        while (!q.isEmpty()) {

            x = q.dequeue();

            if (!processor.processVertexEarly(x)) {
                return processor;
            }

            processed[x] = true;

            EdgeNode edge = edges[x];
            while (edge != null) {
                int y = edge.y;

                if ((directed || !processed[y] || x == y) && !processor.processEdge(x, y)) {
                    return processor;
                }

                if (!discovered[y]) {
                    q.enqueue(y);
                    discovered[y] = true;
                }

                edge = edge.next;
            }

            if (!processor.processVertexLate(x)) {
                return processor;
            }

        }
        return processor;

    }

    @Override
    public <T extends GraphSearchProcessor> T dfs(int x, T processor) {

        if (!processor.processStartVertex(x)) {
            return processor;
        }

        if (!processor.processVertexEarly(x)) {
            return processor;
        }

        int[] parent = new int[edges.length];
        EdgeNode[] search = new EdgeNode[edges.length];
        boolean[] discovered = new boolean[edges.length];
        boolean[] processed = new boolean[edges.length];

        Arrays.fill(parent, -1);


        IntStack s = new IntStack();
        discovered[x] = true;
        search[x] = edges[x];
        s.push(x);

        while (!s.isEmpty()) {

            x = s.peek();

            EdgeNode edge = search[x];
            if (edge == null) {
                if (!processor.processVertexLate(x)) {
                    return processor;
                }
                processed[x] = true;
                s.pop();
                continue;
            }

            search[x] = edge.next;

            int y = edge.y;

            if ((directed || (parent[x] != y && !processed[y])) && !processor.processEdge(x, y)) {
                return processor;
            }

            if (!discovered[y]) {
                if (!processor.processVertexEarly(y)) {
                    return processor;
                }
                discovered[y] = true;
                search[y] = edges[y];
                parent[y] = x;
                s.push(y);
            }

        }
        return processor;

    }
}
