package dandefors.graph;

import java.util.Arrays;

/**
 * A search processor that stops the search as soon as it has found a path to the target vertex. Use this in
 * combination with a breadth-first search to find the shortest unweighted path between two vertices.
 */
class PathFinder implements GraphSearchProcessor {

    private final int target;
    private final int[] parent;
    private final int[] depth;

    public PathFinder(int target, int vertices) {
        this.target = target;
        this.depth = new int[vertices];
        Arrays.fill(depth, -1);
        this.parent = new int[vertices];
        Arrays.fill(parent, -1);
    }

    @Override
    public boolean processVertexEarly(int x) {
        int p = parent[x];
        if (p != -1) {
            depth[x] = depth[p] + 1;
        } else {
            depth[x] = 0;
        }
        return x != target;
    }

    @Override
    public boolean processEdge(int x, int y) {
        if (parent[y] == -1) {
            parent[y] = x;
        }
        return true;
    }

    /**
     * Returns the distance from the start vertex to the target. If there is a direct edge between the start  and
     * target vertex it returns 1. If start and target are identical it returns 0. If no path was found it returns -1.
     *
     * @return The distance to the target vertex.
     */
    public int getDistance() {
        return depth[target];
    }

    /**
     * Returns the
     *
     * @return
     */
    public int[] getPath() {
        int d = getDistance();
        if (d < 0) {
            return new int[0];
        }
        int[] path = new int[d + 1];
        int v = target;
        for (int i = d; i >= 0; i--) {
            path[i] = v;
            v = parent[v];
        }
        return path;
    }

}
