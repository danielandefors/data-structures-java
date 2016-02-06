package dandefors.graph;

import dandefors.graph.search.StrongComponentFinder;
import dandefors.graph.search.TopologicalOrder;

/**
 * A directed graph.
 */
public interface DiGraph extends Graph {

    @Override
    default double density() {
        double v = vertices(), e = edges();
        return e / (v * (v - 1));
    }

    /**
     * Get the out degree of the vertex.
     *
     * @param x A vertex.
     * @return The out degree of the vertex.
     */
    int degree(int x);

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
        return dfs(x, new TopologicalOrder(vertices())).getTopologicalOrder();
    }

    /**
     * Get a topological order of the graph.
     *
     * @return A topological order of the graph.
     * @throws IllegalStateException If the graph has a cycle.
     */
    default int[] getTopologicalOrder() {
        return dfs(new TopologicalOrder(vertices())).getTopologicalOrder();
    }

    /**
     * Compute the strongly connected components of the graph.
     *
     * @return The number of strongly connected components.
     */
    default int getStronglyConnectedComponents() {
        return dfs(new StrongComponentFinder(vertices())).getComponents();
    }

}
