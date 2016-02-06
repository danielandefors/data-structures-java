package dandefors.graph.search;

import dandefors.graph.GraphSearchProcessor;

/**
 * Detects cycles in graphs.
 */
public class CycleDetector implements GraphSearchProcessor {

    public static final byte UNVISITED = 0;
    public static final byte VISITED = 1;
    public static final byte STACKED = 2;

    private boolean cycle;
    private final byte[] stack;

    public CycleDetector(int vertices) {
        this.stack = new byte[vertices];

    }

    @Override
    public boolean processVertexEarly(int x) {
        stack[x] = STACKED;
        return true;
    }

    @Override
    public boolean processVertexLate(int x) {
        stack[x] = VISITED;
        return true;
    }

    @Override
    public boolean processEdge(int x, int y) {
        if (stack[y] == STACKED) {
            cycle = true;
            return false;
        }
        return true;
    }

    public boolean acyclic() {
        return !cycle;
    }


}
