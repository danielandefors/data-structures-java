package dandefors.graph;

import java.util.Arrays;

/**
 * Graph search processor which classifies edges as either back, forward, tree, or cross edges.
 */
public abstract class EdgeClassificationProcessor implements GraphSearchProcessor {

    private int time;
    private int root;
    private final int[] parent;
    private final int[] entryTime;
    private final int[] exitTime;

    public EdgeClassificationProcessor(int vertices) {
        parent = new int[vertices];
        entryTime = new int[vertices];
        exitTime = new int[vertices];
        Arrays.fill(parent, -1);
        Arrays.fill(entryTime, -1);
        Arrays.fill(exitTime, -1);
    }

    @Override
    public boolean processStartVertex(int x) {
        root = x;
        return true;
    }

    @Override
    public boolean processVertexEarly(int x) {
        entryTime[x] = time++;
        return true;
    }

    @Override
    public boolean processVertexLate(int x) {
        exitTime[x] = time++;
        return true;
    }

    @Override
    public final boolean processEdge(int x, int y) {

        if (parent[y] == -1 && !isRoot(y)) {
            parent[y] = x;
        }

        EdgeType type = classifyEdge(x, y);
        return processEdge(x, y, type);

    }

    private EdgeType classifyEdge(int x, int y) {
        if (parent[y] == x) {
            return EdgeType.TREE;
        } else if (!isProcessed(y)) {
            return EdgeType.BACK;
        } else if (entryTime[y] > entryTime[x]) {
            return EdgeType.FORWARD;
        } else {
            return EdgeType.CROSS;
        }
    }

    public int getParent(int vertex) {
        return parent[vertex];
    }

    public int getEntryTime(int vertex) {
        return entryTime[vertex];
    }

    public int getExitTime(int vertex) {
        return exitTime[vertex];
    }

    public boolean isRoot(int vertex) {
        return root == vertex;
    }

    public boolean isDiscovered(int vertex) {
        return getEntryTime(vertex) != -1;
    }

    public boolean isProcessed(int vertex) {
        return getExitTime(vertex) != -1;
    }

    public abstract boolean processEdge(int x, int y, EdgeType type);

}
