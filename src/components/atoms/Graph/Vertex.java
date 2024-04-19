package components.atoms.Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private final char label;
    private final List<Edge> edges;
    private final int x, y;
    private int heuristicCost; // Heuristic cost from this vertex to the goal

    public Vertex(int x, int y, char label){
        this.edges = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.heuristicCost = 0;
        this.label = label;
    }

    public void addEdge(Vertex endVertex, Integer weight){
        /* store created an edge object for the connection between endVertex and current vertex inside this edge list */
        this.edges.add(new Edge(this, endVertex, weight));
    }

    public void removeEdge(Vertex endVertex){
        /* remove connection between this and endVertex vertexes by removing edge from this edge list */
        this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
    }

    public char getLabel() {
        return label;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public String getData(){
        return "("+this.x+", "+this.y+")";
    }

    public void print(boolean showWeight) {
        StringBuilder message = new StringBuilder();

        if (this.edges.isEmpty()) {
            System.out.println(this.getData() + " -->");
            return;
        }

        for(int i = 0; i < this.edges.size(); i++) {
            if (i == 0) {
                message.append(this.edges.get(i).getStart().getData()).append(" -->  ");
            }

            message.append(this.edges.get(i).getEnd().getData());

            if (showWeight) {
                message.append(" (").append(this.edges.get(i).getWeight()).append(")");
            }

            if (i != this.edges.size() - 1) {
                message.append(", ");
            }
        }
        System.out.println(message);
    }

    public String getCoordinates() {
        return "("+this.x+", "+this.y+")";
    }


    public boolean isSame(Vertex vertex) {
        return this.getX()==vertex.getX() && this.getY()==vertex.getY();
    }

    public boolean isColumnHigher(Vertex end) {
        return this.getX()<end.getX();
    }

    public boolean isRowHigher(Vertex end) {
        return this.getY()<end.getY();
    }

    public boolean isSameRow(Vertex end) {
        return this.getY() == end.getY();
    }

    public boolean isSameColumn(Vertex o) {
        return this.x == o.getX();
    }
}
