package dandefors.graph.search;


import dandefors.stack.IntStack;

/**
 * Finds strongly connected components (DFS).
 */
public class StrongComponentFinder extends EdgeClassifier {

    private int components;
    private final int[] low;
    private final int[] scc;

    private final IntStack stack = new IntStack();

    public StrongComponentFinder(int vertices) {
        super(vertices);
        low = new int[vertices];
        scc = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            low[i] = i;
            scc[i] = -1;
        }
    }

    @Override
    public boolean processVertexEarly(int x) {
        stack.push(x);
        return super.processVertexEarly(x);
    }

    @Override
    public boolean processEdge(int x, int y, EdgeType type) {
        // If the edge is a back edge, or a cross edge to a vertex that haven't been assigned to a strongly connected
        // component, assign `y` as the lowest reachable vertex from `x` if the entry time of `y` is lower than the
        // entry time of the vertex that's currently assigned as the lowest reachable vertex from `x`. I.e., because
        // we've found a path to a vertex that was discovered earlier.
        if (type == EdgeType.BACK || (type == EdgeType.CROSS && scc[y] == -1)) {
            if (getEntryTime(y) < getEntryTime(low[x])) {
                low[x] = y;
            }
        }
        return true;
    }

    @Override
    public boolean processVertexLate(int x) {
        if (low[x] == x) {
            // This is the root of a strongly connected component:
            // Pop everything from the stack up until this vertex and assign it to the next SCC
            int t;
            scc[x] = components;
            while ((t = stack.pop()) != x) {
                scc[t] = components;
            }
            components++;
        }

        // Cascade the lowest reachable vertex to the parent if the entry time is less than the entry time of the
        // parent's lowest reachable vertex. Since it means that we've found a back or cross path to an vertex that
        // was discovered earlier.
        int p = getParent(x);
        if (p >= 0 && getEntryTime(low[x]) < getEntryTime(low[p])) {
            low[p] = low[x];
        }

        return super.processVertexLate(x);
    }

    /**
     * Get the number of strongly connected components that were found.
     *
     * @return The number of SCCs.
     */
    public int getComponents() {
        return components;
    }

}
