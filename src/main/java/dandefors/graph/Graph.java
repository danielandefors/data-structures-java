package dandefors.graph;

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
     * Insert an edge from x to y.
     *
     * @param x The source vertex.
     * @param y The target vertex.
     */
    void insert(int x, int y);

    /**
     * Check if there's an edge from x to y.
     *
     * @param x The source vertex.
     * @param y The target vertex.
     * @return True if there's an edge between the two vertices.
     */
    boolean connected(int x, int y);

    /**
     * Breadth-first search.
     *
     * @param x The start vertex.
     * @param p Processes vertexes and edges found in the search.
     * @return The processor
     */
    <T extends GraphSearchProcessor> T bfs(int x, T p);

    /**
     * Depth-first search.
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
        return bfs(x, new PathFinder(y, vertices())).getPath();
    }

    /**
     * Checks if the graph is acyclic. I.e., if it doesn't have any cycles.
     *
     * @return True if the graph is acyclic.
     */
    default boolean acyclic() {

        int len = vertices();
        CycleDetector c = new CycleDetector(len);
        for (int i = 0; i < len; i++) {
            if (c.visited(i)) continue;
            dfs(i, c);
            if (c.isCycleDetected()) {
                return false;
            }
        }

        return true;
    }


}
