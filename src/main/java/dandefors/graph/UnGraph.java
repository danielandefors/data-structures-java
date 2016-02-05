package dandefors.graph;

import dandefors.graph.processors.ArticulationVertexFinder;
import dandefors.graph.processors.WeakComponentFinder;

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
}
