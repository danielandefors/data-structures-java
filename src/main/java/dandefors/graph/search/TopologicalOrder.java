package dandefors.graph.search;

import dandefors.stack.IntStack;

/**
 * Creates a topological ordering of vertices in a graph (DFS).
 */
public class TopologicalOrder extends EdgeClassifier {

    private boolean cycle;
    private IntStack order = new IntStack();

    public TopologicalOrder(int vertices) {
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
            throw new IllegalStateException("Graph is cyclic");
        }
        return order.toArray();
    }

}
