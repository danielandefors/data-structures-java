package dandefors.set;

/**
 * Union-find data structure.
 */
public class UnionFind {

    private int sets;
    private final int[] parent;
    private final int[] size;

    public UnionFind(int length) {
        sets = length;
        parent = new int[length];
        size = new int[length];
        // Initialize each element as being in its own set of size 1
        for (int i = 0; i < length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Get the number of elements.
     * Serves as upper bound for integers passed to {@link #union(int, int)}, {@link #find(int)}, etc.
     *
     * @return The length.
     */
    public int length() {
        return parent.length;
    }

    /**
     * Get the number of sets.
     *
     * @return The number of sets.
     */
    public int sets() {
        return sets;
    }

    /**
     * Find the root of `x`.
     *
     * @param x An element.
     * @return The root of `x`.
     */
    public int find(int x) {
        int p = parent[x];
        while (x != p) {
            // Dynamic programming to make the path shorter each time
            int g = parent[p];
            parent[x] = g;
            x = p;
            p = g;
        }
        return x;
    }

    /**
     * Union two sets.
     *
     * @param x Element in first set
     * @param y Element in second set
     */
    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            // Join the sets:
            // The smaller set a subset of the larger
            if (size[rx] > size[ry]) {
                parent[ry] = rx;
                size[rx] += size[ry];
            } else {
                parent[rx] = ry;
                size[ry] += size[rx];
            }
            sets--;
        }
    }

    /**
     * Check if two elements are in the same set.
     *
     * @param x The first element.
     * @param y The second element.
     * @return True if they're in the same set.
     */
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }

}
