package dandefors.graph;

import dandefors.graph.processors.CycleDetector;
import dandefors.graph.processors.ShortestPathFinder;
import dandefors.graph.processors.TwoColorer;

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
     * @param x The start vertex.
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T bfs(int x, T p);

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
     * @param x The start vertex.
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T dfs(int x, T p);

    /**
     * Get the shortest unweighted path between x and y. Returns an empty array if no path exists. If x and y are
     * identical it returns an array with a single element.
     *
     * @param x The start vertex.
     * @param y The end vertex.
     * @return The shortest unweighted path from x to y.
     */
    default int[] getShortestUnweightedPath(int x, int y) {
        return bfs(x, new ShortestPathFinder(y, vertices())).getPath();
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

}
