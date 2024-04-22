package components.atoms.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Vertex {

    private final char character;
    private final List<Edge> edgeList;
    private final int xAxis, yAxis;
    private final HashSet<String> visitedVertices;
    private int totalCost; // Heuristic cost from this vertex to the goal

    public Vertex(int xAxis, int yAxis, char character){
        this.edgeList = new ArrayList<>();
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.totalCost = 0;
        this.character = character;
        this.visitedVertices = new HashSet<>();
    }

    // Add method to check if a vertex has been visited
    private boolean hasVisited(int x, int y) {
        return visitedVertices.contains(x + "," + y);
    }

    public void addEdge(Vertex endVertex, Integer weight){
        if (this.isSame(endVertex) || hasVisited(endVertex.getxAxis(), endVertex.getyAxis())) {
            // Skip adding edge if it's the same vertex or if it has been visited
            return;
        }

        /* store created an edge object for the connection between endVertex and current vertex inside this edge list */
        this.edgeList.add(new Edge(this, endVertex, weight));
        visitedVertices.add(endVertex.getxAxis() + "," + endVertex.getyAxis()); // Mark as visited
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

    public int getxIndex() {
        return xAxis-1;
    }

    public int getyIndex() {
        return yAxis-1;
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

    public void printS() {
        if (this.getCharacter()=='S') print(true);
    }
    public void printF() {
        if (this.getCharacter()=='F') print(true);
    }
    public void printCoordinate(int x, int y) {
        if (this.getxAxis()==x && this.getyAxis()==y) print(true);
    }
    public void printOther(int x, int y, Vertex vertex) {
        if (this.getxAxis()==x && this.getyAxis()==y) vertex.print(true);
    }


    public static String getDirection(Vertex start, Vertex end) {
        if (start.isSameRow(end)) {
            return start.isColumnHigher(end) ? "Left" : "Right";
        }
        if (start.isSameColumn(end)) {
            return start.isRowHigher(end) ? "Up" : "Down";
        }
        return "Wrong";
    }

    public void addUpEdge(Vertex[][] chars) {
        for (int y = this.getyIndex()-1; y >= 0; y--) {
            Vertex v =chars[this.getxIndex()][y];

            boolean specialCase = (y == 0 && v.getCharacter()!='0') || v.getCharacter()=='S' || v.getCharacter()=='F';
            if (v.getCharacter()=='0' || specialCase) {
                int yIndex = specialCase? y: y+1;

                Vertex upEdge = chars[this.getxIndex()][yIndex];
                if (upEdge.getCharacter() != '0') {
                    upEdge.addEdge(this, this.getyIndex() - upEdge.getyIndex());
                    this.addEdge(upEdge, this.getxIndex() - y);
                    break;
                }
            }
        }
    }

    public void addDownEdge(int yTotal, Vertex[][] chars) {
        for (int y = this.getyIndex() + 1; y < yTotal; y++) {
            Vertex v = chars[this.getxIndex()][y];

            boolean specialCase = (y == yTotal - 1 && v.getCharacter()!='0')
                    || v.getCharacter()=='S' || v.getCharacter()=='F';
            if (v.getCharacter() == '0' || specialCase) {
                int yIndex = specialCase? y: y-1;

                Vertex downEdge = chars[this.getxIndex()][yIndex];
                if (downEdge.getCharacter() != '0') {
                    downEdge.addEdge(this, downEdge.getyIndex() - this.getyIndex());
                    this.addEdge(downEdge, y - this.getyIndex());
                    break;
                }
            }
        }
    }

    public void addLeftEdge(Vertex[][] chars) {
        for (int x = this.getxIndex() - 1; x >= 0; x--) {
            Vertex v = chars[x][this.getyIndex()];

            boolean specialCase = (x == 0 && v.getCharacter()!='0')
                    || v.getCharacter()=='S' || v.getCharacter()=='F';
            if (v.getCharacter() == '0' || specialCase) {
                int xIndex = specialCase ? x : x + 1;

                Vertex leftEdge = chars[xIndex][this.getyIndex()];
                if (leftEdge.getCharacter() != '0') {
                    leftEdge.addEdge(this, this.getxIndex() - leftEdge.getxIndex());
                    this.addEdge(leftEdge, this.getxIndex() - x);
                    break;
                }
            }
        }
    }

    public void addRightEdge(int xTotal, Vertex[][] chars) {
        for (int x = this.getxIndex() + 1; x < xTotal; x++) {
            Vertex v = chars[x][this.getyIndex()];

            boolean specialCase = (x == xTotal - 1 && v.getCharacter()!='0')
                    || v.getCharacter()=='S' || v.getCharacter()=='F';
            if (v.getCharacter() == '0' || specialCase) {
                int xIndex = specialCase ? x : x - 1;

                Vertex rightEdge = chars[xIndex][this.getyIndex()];
                if (rightEdge.getCharacter() != '0') {
                    rightEdge.addEdge(this, rightEdge.getxIndex() - this.getxIndex());
                    this.addEdge(rightEdge, x - this.getxIndex());
                    break;
                }
            }
        }
    }


    public void removeLowDistanceEdges() {
        Edge xMax=null, xMin=null, yMax=null, yMin=null;
        Vertex xMaxV=this, xMinV=this, yMaxV=this, yMinV=this;
        for (Edge e : this.edgeList) {
            Vertex v = e.getEnd();

            boolean isColumnHigher = xMaxV.isColumnHigher(v) && xMaxV.isSameRow(v) && !xMaxV.isSame(v);
            boolean isColumnLower = !xMinV.isColumnHigher(v) && xMinV.isSameRow(v) && !xMinV.isSame(v);
            boolean isRowHigher = yMaxV.isRowHigher(v) && yMaxV.isSameColumn(v) && !yMaxV.isSame(v);
            boolean isRowLower = !yMinV.isRowHigher(v) && yMinV.isSameColumn(v) && !yMinV.isSame(v);

            if (isColumnHigher) {
                xMax = e;
                xMaxV=e.getEnd();
            }
            else if (isColumnLower) {
                xMin = e;
                xMinV=e.getEnd();
            }
            if (isRowHigher) {
                yMax = e;
                yMaxV=e.getEnd();
            }
            else if (isRowLower) {
                yMin = e;
                yMinV=e.getEnd();
            }
        }

        this.edgeList.clear();

        if (xMax != null) this.edgeList.add(xMax);
        if (xMin != null) this.edgeList.add(xMin);
        if (yMax != null) this.edgeList.add(yMax);
        if (yMin != null) this.edgeList.add(yMin);
    }
}
