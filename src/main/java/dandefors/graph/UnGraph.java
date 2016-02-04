package dandefors.graph;

/**
 * An undirected graph.
 */
public interface UnGraph extends Graph {

    /**
     * Get the number of connected components in an undirected graph.
     *
     * @return The number of connected components.
     */
    default int getConnectedComponents() {

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

    /**
     * Get all articulation vertices (a.k.a. cut nodes) in the graph, starting from the given vertex.
     * Only supported for undirected graphs.
     *
     * @param x The start vertex.
     * @return The articulation vertices.
     */
    default int[] getArticulationVertices(int x) {
        return dfs(x, new ArticulationVertexFinder(vertices())).getArticulationVertices();
    }
}
