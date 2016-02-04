package dandefors.graph;

import java.util.BitSet;

/**
 * A processor that remembers each vertex visited during a search.
 */
class VertexVisitor implements GraphSearchProcessor {

    private final BitSet s;

    public VertexVisitor(int vertices) {
        this.s = new BitSet(vertices);
    }

    @Override
    public boolean processVertexEarly(int x) {
        s.set(x);
        return true;
    }

    public boolean visited(int x) {
        return s.get(x);
    }


}
