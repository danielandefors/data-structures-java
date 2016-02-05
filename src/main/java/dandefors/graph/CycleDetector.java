package dandefors.graph;

/**
 * Processor used to detect if a graph contains a cycle.
 */
class CycleDetector implements GraphSearchProcessor {

    private static final byte UNVISITED = 0;
    private static final byte VISITED = 1;
    private static final byte STACKED = 2;

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
