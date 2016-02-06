package dandefors.graph.search;

import dandefors.graph.GraphSearchProcessor;

/**
 * Attempts to color the graph using only two colors (black and white).
 * If the coloring was successful the {@link #bipartite()} method returns true.
 */
public class TwoColorer implements GraphSearchProcessor {

    public static final byte UNCOLORED = 0;
    public static final byte WHITE = 1;
    public static final byte BLACK = 2;

    private final byte[] color;

    private boolean bipartite;

    /**
     * @param vertices The number of vertices in the graph.
     */
    public TwoColorer(int vertices) {
        this.color = new byte[vertices];
        bipartite = true;
    }

    @Override
    public boolean processStartVertex(int x) {
        color[x] = WHITE;
        return true;
    }

    @Override
    public boolean processEdge(int x, int y) {
        if (color[x] == color[y]) {
            // We've encountered a cycle with an odd number of edges -- not bipartite
            bipartite = false;
            return false;
        }
        color[y] = complement(color[x]);
        return true;
    }

    /**
     * Get the color of the vertex.
     * {@link #WHITE}, {@link #BLACK}, or {@link #UNCOLORED}.
     *
     * @param vertex A vertex.
     * @return The color.
     */
    public byte getColor(int vertex) {
        return color[vertex];
    }

    /**
     * Get if the graph is a bipartite graph.
     * I.e., if we were able to color the vertices using only two colors.
     *
     * @return True if bipartite.
     */
    public boolean bipartite() {
        return bipartite;
    }

    /**
     * Get the complement of a given color.
     * E.g., if color is BLACK the method returns WHITE.
     *
     * @param color A color.
     * @return The complement of the given color.
     */
    public static byte complement(byte color) {
        if (color == WHITE) return BLACK;
        if (color == BLACK) return WHITE;
        return UNCOLORED;
    }


}
