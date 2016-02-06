package dandefors.graph;

/**
 *
 */
public class Path {

    private final int[] vertices;
    private final int weight;

    public Path(int weight, int[] vertices) {
        this.weight = weight;
        this.vertices = vertices;
    }

    public int[] getVertices() {
        return vertices;
    }

    public int getWeight() {
        return weight;
    }

}
