/**
 * Represents an edge between two vertices in a graph.
 */
public class Edge {
    private final Vertex end;
    private final int weight;

    /**
     * Constructs a new edge with the given end vertex and weight.
     *
     * @param end    the vertex at the end of the edge
     * @param weight the weight of the edge
     */
    public Edge(Vertex end, Integer weight) {
        this.end = end;
        this.weight = weight;
    }

    /**
     * Returns the vertex at the end of the edge.
     *
     * @return the end vertex
     */
    public Vertex getEnd() {
        return end;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return the weight of the edge
     */
    public Integer getWeight() {
        return weight;
    }
}
