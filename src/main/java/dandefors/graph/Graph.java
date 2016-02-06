package dandefors.graph;

import dandefors.graph.search.CycleDetector;
import dandefors.graph.search.ShortestPathFinder;
import dandefors.graph.search.TwoColorer;
import dandefors.heap.ArrayHeap;
import dandefors.heap.Heap;

/**
 * A graph.
 */
public interface Graph {

    /**
     * Get the number of vertices.
     */
    int vertices();

    /**
     * Get the number of edges.
     */
    int edges();

    /**
     * Get the graph's density as the ratio between the number of edges in the graph and the maximum number of edges.
     * If the graph is complex, e.g., it contains self-loops or multi-edges, the density could be > 1.
     *
     * @return The graph's density.
     */
    double density();

    /**
     * Insert an edge with zero weight from x to y.
     *
     * @param x The source vertex.
     * @param y The target vertex.
     */
    default void insert(int x, int y) {
        insert(x, y, 0);
    }

    /**
     * Insert an edge from x to y.
     *
     * @param x      The source vertex.
     * @param y      The target vertex.
     * @param weight The edge weight.
     */
    void insert(int x, int y, int weight);

    /**
     * Get all edges from the vertex `x`.
     *
     * @param x A vertex.
     * @return All edges from this vertex.
     */
    Iterable<Edge> edges(int x);

    /**
     * Check if there's an edge from x to y.
     *
     * @param x The source vertex.
     * @param y The target vertex.
     * @return True if there's an edge between the two vertices.
     */
    boolean connected(int x, int y);

    /**
     * Run a breadth-first search over all vertices in the graph.
     *
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T bfs(T p);

    /**
     * Run a breadth-first search from the given vertex.
     *
     * @param s The start vertex.
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T bfs(int s, T p);

    /**
     * Run a depth-first search over all vertices in the graph.
     *
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T dfs(T p);

    /**
     * Run a depth-first search from the given vertex.
     *
     * @param s The start vertex.
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T dfs(int s, T p);

    /**
     * Get the shortest unweighted path between x and y. A.k.a., the minimum-link path.
     * Returns an empty array if no path exists. If x and y are identical it returns an array with a single element.
     *
     * @param s The start vertex.
     * @param t The target vertex.
     * @return The shortest unweighted path from x to y.
     */
    default int[] getShortestUnweightedPath(int s, int t) {
        return bfs(s, new ShortestPathFinder(t, vertices())).getPath();
    }

    /**
     * Checks if the graph is acyclic. I.e., if it doesn't have any cycles.
     *
     * @return True if the graph is acyclic.
     */
    default boolean acyclic() {
        return dfs(new CycleDetector(vertices())).acyclic();
    }

    /**
     * Checks if the graph is bipartite. I.e., the vertices can be divided into two disjoint independent sets, such
     * that every edge in the graph has one endpoint in each set. A bipartite graph can also be colored with only two
     * colors.
     * <p>
     * If the graph is disconnected, it returns true if each component of the graph is a bipartition.
     *
     * @return True if the graph is bipartite.
     */
    default boolean bipartite() {
        return bfs(new TwoColorer(vertices())).bipartite();
    }


    /**
     * Get the shortest weighted path from `s` to `t`.
     * The algorithm only works with non-negative edge weights.
     *
     * @param s The start vertex.
     * @param t The target vertex.
     * @return The shortest weighted path.
     * @throws IllegalStateException If no path exists between `s` and `t`.
     */
    default Path getShortestWeightedPath(int s, int t) {

        // Represents the shortest path from `s` to `y`
        class SPNode implements Comparable<SPNode> {

            private int x;
            private int y;
            private int weight;
            private int length;

            public SPNode(int x, int y, int weight, int length) {
                this.x = x;
                this.y = y;
                this.weight = weight;
                this.length = length;
            }

            @Override
            public int compareTo(SPNode o) {
                return weight - o.weight;
            }
        }

        // Dijkstra

        int vertices = vertices();
        Heap<SPNode> h = ArrayHeap.createMinHeap();
        int[] parent = new int[vertices];
        boolean[] seen = new boolean[vertices];

        int last = s;
        int weight = 0;
        int length = 1;
        parent[last] = last;

        while (last != t) {

            seen[last] = true;
            for (Edge edge : edges(last)) {
                if (seen[edge.getY()]) continue;
                h.insert(new SPNode(last, edge.getY(), edge.getWeight() + weight, length + 1));
            }

            SPNode node;
            do {
                if (h.isEmpty()) {
                    throw new IllegalStateException(String.format("There's no path from %d to %d", s, t));
                }
                node = h.extract();
            } while (seen[node.y]);

            last = node.y;
            weight = node.weight;
            length = node.length;
            parent[last] = node.x;

        }

        int[] path = new int[length];
        int w = t;
        for (int i = length - 1; i >= 0; i--) {
            path[i] = w;
            w = parent[w];
        }


        return new Path(weight, path);

    }

}
