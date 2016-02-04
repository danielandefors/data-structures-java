package dandefors.graph;

import java.util.BitSet;

/**
 * Graph search processor which finds all articulation vertices when used with DFS.
 */
class ArticulationVertexFinder extends EdgeClassificationProcessor {

    private final int[] reachableAncestor;
    private final int[] treeOutDegree;
    private final BitSet set;

    public ArticulationVertexFinder(int vertices) {
        super(vertices);
        this.reachableAncestor = new int[vertices];
        this.treeOutDegree = new int[vertices];
        this.set = new BitSet(vertices);
    }

    @Override
    public boolean processVertexEarly(int x) {
        reachableAncestor[x] = x;
        return super.processVertexEarly(x);
    }

    @Override
    public boolean processEdge(int x, int y, EdgeType type) {
        if (type == EdgeType.TREE) {
            treeOutDegree[x]++;
        } else { // getEntryTime(y) < getEntryTime(reachableAncestor[x])
            reachableAncestor[x] = y;
        }
        return true;
    }

    @Override
    public boolean processVertexLate(int x) {

        if (isRoot(x)) {
            if (treeOutDegree[x] > 1) {
                // root articulation vertex
                set.set(x);
            }
        } else {

            int p = getParent(x);
            if (!isRoot(p)) {
                if (reachableAncestor[x] == getParent(x)) {
                    // parent articulation vertex
                    set.set(p);
                }
                if (reachableAncestor[x] == x) {
                    // bridge articulation vertex
                    set.set(p);
                    if (treeOutDegree[x] > 0) {
                        // bridge articulation vertex
                        set.set(x);
                    }
                }
            }

            int timeX = getEntryTime(reachableAncestor[x]);
            int timeP = getEntryTime(reachableAncestor[p]);
            if (timeX < timeP) {
                reachableAncestor[p] = reachableAncestor[x];
            }

        }

        return super.processVertexLate(x);
    }

    /**
     * Get the articulation vertices that were found, if any.
     * The vertices will be returned in ascending order.
     *
     * @return The articulation vertices that were found.
     */
    public int[] getArticulationVertices() {
        int[] xs = new int[set.cardinality()];
        for (int x = set.nextSetBit(0), i = 0;
             x >= 0;
             x = set.nextSetBit(x + 1), i++) {
            xs[i] = x;
        }
        return xs;
    }

}
