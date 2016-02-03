package dandefors.graph;

/**
 * A graph.
 */
public interface Graph {

    /**
     * Check if it's a directed graph.
     */
    boolean directed();

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
     * Get the number of connected components in an undirected graph.
     *
     * @return The number of connected components.
     */
    default int getConnectedComponents() {

        if (directed()) {
            throw new UnsupportedOperationException("Only supported for undirected graphs");
        }

        int cc = 0;
        int len = vertices();
        VertexVisitor v = new VertexVisitor(len);

        for (int i = 0; i < len; i++) {
            if (v.visited(i)) continue;
            bfs(i, v);
            cc++;
        }

        return cc;

    }


}
