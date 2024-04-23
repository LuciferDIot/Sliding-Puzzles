package components.atoms.Graph;

/**
 * Represents an edge between two vertices in a graph.
 * An edge connects two vertices and may have an associated weight.
 * This class provides methods to retrieve the starting vertex,
 * the ending vertex, and the weight of the edge.
 * <p>
 * Example usage:
 * <pre>{@code
 *     // Create vertices
 *     Vertex startVertex = new Vertex(1, 1, 'S');
 *     Vertex endVertex = new Vertex(2, 2, 'F');
 *
 *     // Create an edge between the vertices
 *     Edge edge = new Edge(startVertex, endVertex, 5);
 *
 *     // Retrieve information about the edge
 *     Vertex start = edge.getStart();
 *     Vertex end = edge.getEnd();
 *     int weight = edge.getWeight();
 * }</pre>
 *
 * @author  KWJP Geevinda
 * @see     components.atoms.Graph.Vertex
 * @since   1.0
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

