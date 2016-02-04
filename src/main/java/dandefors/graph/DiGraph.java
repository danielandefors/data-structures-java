package dandefors.graph;

/**
 * A directed graph.
 */
public interface DiGraph extends Graph {

    /**
     * Create a new graph that's identical to the current graph, except that all edges have been reversed.
     *
     * @return A new graph with reversed edges.
     */
    DiGraph reversed();

    /**
     * Get a topological order of the vertices that are reachable from `x`.
     *
     * @param x The root vertex of the topological sort.
     * @return A topological order of the vertices reachable from `x`.
     * @throws IllegalStateException If the graph has a cycle.
     */
    default int[] getTopologicalOrder(int x) {
        return dfs(x, new TopologicalSort(vertices())).getTopologicalOrder();
    }

    /**
     * Get a topological order of the graph.
     *
     * @return A topological order of the graph.
     * @throws IllegalStateException If the graph has a cycle.
     */
    default int[] getTopologicalOrder() {
        int len = vertices();
        TopologicalSort s = new TopologicalSort(len);
        for (int i = 0; i < len; i++) {
            if (s.isDiscovered(i)) continue;
            dfs(i, s);
            if (s.isCycleDetected()) break;
        }
        return s.getTopologicalOrder();
    }

}
