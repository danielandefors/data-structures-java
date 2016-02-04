package dandefors.graph;

/**
 *
 */
public class AdjacencyListTest extends DiGraphTest {

    @Override
    UnGraph createGraph(int vertices) {
        return AdjacencyList.createUnGraph(vertices);
    }

    @Override
    DiGraph createDiGraph(int vertices) {
        return AdjacencyList.createDiGraph(vertices);
    }

}
