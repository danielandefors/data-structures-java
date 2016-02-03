package dandefors.graph;

/**
 * A processor that remembers each vertex visited during a search.
 */
public class VertexVisitor implements GraphSearchProcessor {

    private final boolean[] s;

    public VertexVisitor(int vertices) {
        this.s = new boolean[vertices];
    }

    @Override
    public boolean processVertexEarly(int x) {
        s[x] = true;
        return true;
    }

    public boolean visited(int x) {
        return s[x];
    }


}
