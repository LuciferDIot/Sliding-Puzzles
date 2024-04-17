package components.atoms;

public class Edge {

    private final Vertex start, end;
    private final Integer weight;

    public Edge(Vertex startV, Vertex endV, Integer inputWeight) {
        this.start = startV;
        this.end = endV;
        this.weight = inputWeight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public Integer getWeight() {
        return weight;
    }
}
