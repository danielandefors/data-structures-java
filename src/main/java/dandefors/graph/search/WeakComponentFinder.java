package dandefors.graph.search;

import dandefors.graph.GraphSearchProcessor;

/**
 * Finds connected components in an undirected graph (BFS or DFS).
 */
public class WeakComponentFinder implements GraphSearchProcessor {

    private int components;

    @Override
    public boolean processStartVertex(int x) {
        components++;
        return true;
    }

    public int getComponents() {
        return components;
    }
}


