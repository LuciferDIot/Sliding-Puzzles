package components.atoms.Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private final char label;
    private final List<Edge> edges;
    private final int x, y;
    private int heuristicCost; // Heuristic cost from this vertex to the goal
    private Vertex prev; // Previous vertex in the shortest path

    public Vertex(int x, int y, char label){
        this.edges = new ArrayList<Edge>();
        this.x = x;
        this.y = y;
        this.heuristicCost = 0;
        this.prev = null;
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

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public String getData(){
        return "("+this.x+", "+this.y+")";
    }

    public void print(boolean showWeight) {
        String message = "";

        if (this.edges.isEmpty()) {
            System.out.println(this.getData() + " -->");
            return;
        }

        for(int i = 0; i < this.edges.size(); i++) {
            if (i == 0) {
                message += this.edges.get(i).getStart().getData() + " -->  ";
            }

            message += this.edges.get(i).getEnd().getData();

            if (showWeight) {
                message += " (" + this.edges.get(i).getWeight() + ")";
            }

            if (i != this.edges.size() - 1) {
                message += ", ";
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
