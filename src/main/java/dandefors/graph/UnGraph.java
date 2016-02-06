package dandefors.graph;

import dandefors.graph.search.ArticulationVertexFinder;
import dandefors.graph.search.WeakComponentFinder;
import dandefors.tuple.Tuple;

/**
 * An undirected graph.
 */
public interface UnGraph extends Graph {

    @Override
    default double density() {
        double v = vertices(), e = edges();
        return (e * 2) / (v * (v - 1));
    }

    /**
     * Get the number of connected components in an undirected graph.
     *
     * @return The number of connected components.
     */
    default int getConnectedComponents() {
        return bfs(new WeakComponentFinder()).getComponents();
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

    /**
     * Compute the minimum spanning tree of the graph.
     * It creates a new graph that only contains the edges of the minimum spanning tree.
     * It also returns the total weight cost of the MST.
     *
     * @return The minimum spanning tree and the total weight cost.
     * @throws IllegalStateException If the graph is disconnected.
     */
    Tuple<UnGraph, Integer> getMinimumSpanningTree();
}
