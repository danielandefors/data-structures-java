package dandefors.graph;

import dandefors.stack.IntStack;

/**
 *
 */
class TopologicalSort extends EdgeClassificationProcessor {

    private boolean cycle;
    private IntStack order = new IntStack();

    public TopologicalSort(int vertices) {
        super(vertices);
    }

    @Override
    public boolean processEdge(int x, int y, EdgeType type) {
        if (type == EdgeType.BACK) {
            cycle = true;
            return false;
        }
        return true;
    }

    @Override
    public boolean processVertexLate(int x) {
        order.push(x);
        return super.processVertexLate(x);
    }

    public int[] getTopologicalOrder() {
        if (cycle) {
            throw new IllegalStateException("The graph is cyclic");
        }
        return order.toArray();
    }

}
