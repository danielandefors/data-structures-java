package dandefors.graph;

/**
 *
 */
public interface GraphSearchProcessor {

    default boolean processVertexEarly(int x) {
        return true;
    }

    default boolean processEdge(int x, int y) {
        return true;
    }

    default boolean processVertexLate(int x) {
        return true;
    }

}
