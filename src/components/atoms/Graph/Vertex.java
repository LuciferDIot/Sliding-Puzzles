package components.atoms.Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private final char character;
    private final List<Edge> edgeList;
    private final int xAxis, yAxis;
    private int totalCost; // Heuristic cost from this vertex to the goal

    public Vertex(int xAxis, int yAxis, char character){
        this.edgeList = new ArrayList<>();
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.totalCost = 0;
        this.character = character;
    }

    public void addEdge(Vertex endVertex, Integer weight){
        /* store created an edge object for the connection between endVertex and current vertex inside this edge list */
        this.edgeList.add(new Edge(this, endVertex, weight));
    }

    public void removeEdge(Vertex endVertex){
        /* remove connection between this and endVertex vertexes by removing edge from this edge list */
        this.edgeList.removeIf(edge -> edge.getEnd().equals(endVertex));
    }

    public char getCharacter() {
        return character;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getData(){
        return "("+this.xAxis +", "+this.yAxis +")";
    }

    public String getCoordinates() {
        return "("+this.xAxis +", "+this.yAxis +")";
    }


    public boolean isSame(Vertex vertex) {
        return this.getxAxis()==vertex.getxAxis() && this.getyAxis()==vertex.getyAxis();
    }

    public boolean isColumnHigher(Vertex end) {
        return this.getxAxis()<end.getxAxis();
    }

    public boolean isRowHigher(Vertex end) {
        return this.getyAxis()<end.getyAxis();
    }

    public boolean isSameRow(Vertex end) {
        return this.getyAxis() == end.getyAxis();
    }

    public boolean isSameColumn(Vertex o) {
        return this.xAxis == o.getxAxis();
    }

    public void print(boolean showWeight) {
        StringBuilder message = new StringBuilder();

        if (this.edgeList.isEmpty()) {
            System.out.println(this.getData() + " -->");
            return;
        }

        for(int i = 0; i < this.edgeList.size(); i++) {
            if (i == 0) {
                message.append(this.edgeList.get(i).getStart().getData()).append(" -->  ");
            }

            message.append(this.edgeList.get(i).getEnd().getData());

            if (showWeight) {
                message.append(" (").append(this.edgeList.get(i).getWeight()).append(")");
            }

            if (i != this.edgeList.size() - 1) {
                message.append(", ");
            }
        }
        System.out.println(message);
    }

    public static String getDirection(Vertex start, Vertex end) {
        if (start.isSameRow(end)) {
            return start.isColumnHigher(end) ? "Right" : "Left";
        }
        if (start.isSameColumn(end)) {
            return start.isRowHigher(end) ? "Down" : "Up";
        }
        return "Wrong";
    }
}
