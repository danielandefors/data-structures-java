package dandefors.graph;

/**
 * Graph search processor.
 * Can be used with graph DFS and BFS.
 */
public interface GraphSearchProcessor {

    /**
     * Called when the graph search algorithm starts a search from a given vertex.
     *
     * @param x The start vertex.
     * @return True to continue the search.
     */
    default boolean processStartVertex(int x) {
        return true;
    }

    /**
     * Called when the graph search algorithm enters a vertex.
     *
     * @param x The vertex entered.
     * @return True to continue the search.
     */
    default boolean processVertexEarly(int x) {
        return true;
    }


    /**
     * Called when the graph search algorithm encounters an edge.
     *
     * @param x The source vertex.
     * @param y The target vertex.
     * @return True to continue the search.
     */
    default boolean processEdge(int x, int y) {
        return true;
    }

    /**
     * Called when the graph search algorithm exits a vertex.
     *
     * @param x The vertex exited.
     * @return True to continue the search.
     */
    default boolean processVertexLate(int x) {
        return true;
    }

}
