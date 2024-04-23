package components.atoms.Graph;

/**
 * Represents an edge between two vertices in a graph.
 */
public class Edge {

    private final Vertex start; // The starting vertex of the edge
    private final Vertex end; // The ending vertex of the edge
    private final Integer weight; // The weight of the edge (can be null)

    /**
     * Constructs an edge between two vertices with an optional weight.
     * @param startV The starting vertex of the edge.
     * @param endV The ending vertex of the edge.
     * @param inputWeight The weight of the edge (can be null).
     */
    public Edge(Vertex startV, Vertex endV, Integer inputWeight) {
        this.start = startV;
        this.end = endV;
        this.weight = inputWeight;
    }

    /**
     * Retrieves the starting vertex of the edge.
     * @return The starting vertex of the edge.
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Retrieves the ending vertex of the edge.
     * @return The ending vertex of the edge.
     */
    public Vertex getEnd() {
        return end;
    }

    /**
     * Retrieves the weight of the edge.
     * @return The weight of the edge.
     */
    public Integer getWeight() {
        return weight;
    }
}

