package components.atoms.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a vertex in a graph.
 */
public class Vertex {

    private final char character;
    private final List<Edge> edgeList; // List of edges connected to this vertex
    private final int xAxis, yAxis; // Coordinates of the vertex
    private final HashSet<String> visitedVertices; // Set to track visited vertices
    private int totalCost; // Heuristic cost from this vertex to the goal

    /**
     * Constructs a new vertex with the given coordinates and character.
     * Initializes the edge list, coordinates, total cost, character, and visited vertices set.
     * @param xAxis The x-coordinate of the vertex.
     * @param yAxis The y-coordinate of the vertex.
     * @param character The character associated with the vertex.
     */
    public Vertex(int xAxis, int yAxis, char character){
        this.edgeList = new ArrayList<>();
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.totalCost = 0;
        this.character = character;
        this.visitedVertices = new HashSet<>();
    }

    /**
     * Checks if the vertex at the specified coordinates has been visited.
     * @param x The x-coordinate of the vertex.
     * @param y The y-coordinate of the vertex.
     * @return True if the vertex has been visited, otherwise false.
     */
    private boolean hasVisited(int x, int y) {
        return visitedVertices.contains(x + "," + y);
    }

    /**
     * Adds an edge between this vertex and the specified end vertex with the given weight.
     * Does not add the edge if it's the same vertex or if it has been visited.
     * @param endVertex The end vertex of the edge.
     * @param weight The weight of the edge.
     */
    public void addEdge(Vertex endVertex, Integer weight){
        if (this.isSame(endVertex) || hasVisited(endVertex.getxAxis(), endVertex.getyAxis())) {
            // Skip adding edge if it's the same vertex or if it has been visited
            return;
        }

        /* store created an edge object for the connection between endVertex and current vertex inside this edge list */
        this.edgeList.add(new Edge(this, endVertex, weight));
        visitedVertices.add(endVertex.getxAxis() + "," + endVertex.getyAxis()); // Mark as visited
    }

    /**
     * Removes the edge between this vertex and the specified end vertex.
     * @param endVertex The end vertex of the edge to be removed.
     */
    public void removeEdge(Vertex endVertex){
        /* remove connection between this and endVertex vertexes by removing edge from this edge list */
        this.edgeList.removeIf(edge -> edge.getEnd().equals(endVertex));
    }


    /**
     * Gets the character associated with this vertex.
     * @return The character associated with the vertex.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Gets the list of edges connected to this vertex.
     * @return The list of edges connected to this vertex.
     */
    public List<Edge> getEdgeList() {
        return edgeList;
    }

    /**
     * Gets the x-coordinate of this vertex.
     * @return The x-coordinate of the vertex.
     */
    public int getxAxis() {
        return xAxis;
    }

    /**
     * Gets the y-coordinate of this vertex.
     * @return The y-coordinate of the vertex.
     */
    public int getyAxis() {
        return yAxis;
    }

    /**
     * Gets the x-index of this vertex (x-coordinate - 1).
     * @return The x-index of the vertex.
     */
    public int getxIndex() {
        return xAxis - 1;
    }

    /**
     * Gets the y-index of this vertex (y-coordinate - 1).
     * @return The y-index of the vertex.
     */
    public int getyIndex() {
        return yAxis - 1;
    }

    /**
     * Gets the total cost associated with this vertex.
     * @return The total cost associated with the vertex.
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the total cost associated with this vertex.
     * @param totalCost The total cost to be set.
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Gets a string representation of the vertex's coordinates.
     * @return A string representing the vertex's coordinates.
     */
    public String getData(){
        return "(" + this.xAxis + ", " + this.yAxis + ")";
    }

    /**
     * Gets a string representation of the vertex's coordinates.
     * @return A string representing the vertex's coordinates.
     */
    public String getCoordinates() {
        return "(" + this.xAxis + ", " + this.yAxis + ")";
    }

    /**
     * Checks if this vertex is the same as the given vertex.
     * @param vertex The vertex to compare with.
     * @return True if the vertices are the same, otherwise false.
     */
    public boolean isSame(Vertex vertex) {
        return this.getxAxis() == vertex.getxAxis() && this.getyAxis() == vertex.getyAxis();
    }

    /**
     * Checks if the column of this vertex is higher than the column of the given vertex.
     * @param end The vertex to compare with.
     * @return True if the column of this vertex is higher, otherwise false.
     */
    public boolean isColumnHigher(Vertex end) {
        return this.getxAxis() < end.getxAxis();
    }

    /**
     * Checks if the row of this vertex is higher than the row of the given vertex.
     * @param end The vertex to compare with.
     * @return True if the row of this vertex is higher, otherwise false.
     */
    public boolean isRowHigher(Vertex end) {
        return this.getyAxis() < end.getyAxis();
    }

    /**
     * Checks if this vertex is in the same row as the given vertex.
     * @param end The vertex to compare with.
     * @return True if this vertex is in the same row, otherwise false.
     */
    public boolean isSameRow(Vertex end) {
        return this.getyAxis() == end.getyAxis();
    }

    /**
     * Checks if this vertex is in the same column as the given vertex.
     * @param o The vertex to compare with.
     * @return True if this vertex is in the same column, otherwise false.
     */
    public boolean isSameColumn(Vertex o) {
        return this.xAxis == o.getxAxis();
    }

    /**
     * Prints the vertex's edge list, optionally showing edge weights.
     * @param showWeight True to show edge weights, false otherwise.
     */
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


    /**
     * Prints the vertex's edge list if the character is 'S', showing edge weights.
     */
    public void printS() {
        if (this.getCharacter() == 'S') print(true);
    }

    /**
     * Prints the vertex's edge list if the character is 'F', showing edge weights.
     */
    public void printF() {
        if (this.getCharacter() == 'F') print(true);
    }

    /**
     * Prints the vertex's edge list if the vertex coordinates match the specified coordinates, showing edge weights.
     * @param x The x-coordinate to match.
     * @param y The y-coordinate to match.
     */
    public void printCoordinate(int x, int y) {
        if (this.getxAxis() == x && this.getyAxis() == y) print(true);
    }

    /**
     * Prints the edge list of the specified vertex if the vertex coordinates match the specified coordinates, showing edge weights.
     * @param x The x-coordinate to match.
     * @param y The y-coordinate to match.
     * @param vertex The vertex whose edge list is to be printed.
     */
    public void printOther(int x, int y, Vertex vertex) {
        if (this.getxAxis() == x && this.getyAxis() == y) vertex.print(true);
    }

    /**
     * Determines the direction from the start vertex to the end vertex.
     * @param start The start vertex.
     * @param end The end vertex.
     * @return The direction from start to end ('Left', 'Right', 'Up', 'Down', or 'Wrong').
     */
    public static String getDirection(Vertex start, Vertex end) {
        if (start.isSameRow(end)) {
            return start.isColumnHigher(end) ? "Left" : "Right";
        }
        if (start.isSameColumn(end)) {
            return start.isRowHigher(end) ? "Up" : "Down";
        }
        return "Wrong";
    }

    /**
     * Adds an upward edge from this vertex to the vertices above it in the specified 2D array of vertices.
     * @param chars The 2D array of vertices representing the graph.
     */
    public void addUpEdge(Vertex[][] chars) {
        // Iterate through the vertices above this vertex
        for (int y = this.getyIndex()-1; y >= 0; y--) {
            // Get the vertex at the current position
            Vertex v = chars[this.getxIndex()][y];

            // Check for special cases where the vertex above is not a barrier or the start/end vertex
            boolean specialCase = (y == 0 && v.getCharacter() != '0') || v.getCharacter() == 'S' || v.getCharacter() == 'F';
            if (v.getCharacter() == '0' || specialCase) {
                // Calculate the index of the vertex above, considering the special case
                int yIndex = specialCase ? y : y + 1;

                // Get the vertex representing the edge above
                Vertex upEdge = chars[this.getxIndex()][yIndex];
                // Add an edge between this vertex and the vertex above
                if (upEdge.getCharacter() != '0') {
                    // Calculate the weight of the edge based on the difference in y-indices
                    upEdge.addEdge(this, Math.abs(this.getyIndex() - upEdge.getyIndex()));
                    this.addEdge(upEdge, Math.abs(this.getyIndex() - upEdge.getyIndex()));
                    // Break the loop after adding the edge
                    break;
                }
            }
        }
    }


    /**
     * Adds a downward edge from this vertex to the vertices below it in the specified 2D array of vertices.
     * @param yTotal The total number of rows in the 2D array.
     * @param chars The 2D array of vertices representing the graph.
     */
    public void addDownEdge(int yTotal, Vertex[][] chars) {
        // Iterate through the vertices below this vertex
        for (int y = this.getyIndex() + 1; y < yTotal; y++) {
            // Get the vertex at the current position
            Vertex v = chars[this.getxIndex()][y];

            // Check for special cases where the vertex below is not a barrier or the start/end vertex
            boolean specialCase = (y == yTotal - 1 && v.getCharacter() != '0') || v.getCharacter() == 'S' || v.getCharacter() == 'F';
            if (v.getCharacter() == '0' || specialCase) {
                // Calculate the index of the vertex below, considering the special case
                int yIndex = specialCase ? y : y - 1;

                // Get the vertex representing the edge below
                Vertex downEdge = chars[this.getxIndex()][yIndex];
                // Add an edge between this vertex and the vertex below
                if (downEdge.getCharacter() != '0') {
                    // Calculate the weight of the edge based on the difference in y-indices
                    downEdge.addEdge(this, Math.abs(this.getyIndex() - downEdge.getyIndex()));
                    this.addEdge(downEdge, Math.abs(this.getyIndex() - downEdge.getyIndex()));
                    // Break the loop after adding the edge
                    break;
                }
            }
        }
    }

    /**
     * Adds a leftward edge from this vertex to the vertices to its left in the specified 2D array of vertices.
     * @param chars The 2D array of vertices representing the graph.
     */
    public void addLeftEdge(Vertex[][] chars) {
        // Iterate through the vertices to the left of this vertex
        for (int x = this.getxIndex() - 1; x >= 0; x--) {
            // Get the vertex at the current position
            Vertex v = chars[x][this.getyIndex()];

            // Check for special cases where the vertex to the left is not a barrier or the start/end vertex
            boolean specialCase = (x == 0 && v.getCharacter() != '0') || v.getCharacter() == 'S' || v.getCharacter() == 'F';
            if (v.getCharacter() == '0' || specialCase) {
                // Calculate the index of the vertex to the left, considering the special case
                int xIndex = specialCase ? x : x + 1;

                // Get the vertex representing the edge to the left
                Vertex leftEdge = chars[xIndex][this.getyIndex()];
                // Add an edge between this vertex and the vertex to the left
                if (leftEdge.getCharacter() != '0') {
                    // Calculate the weight of the edge based on the difference in x-indices
                    leftEdge.addEdge(this, Math.abs(this.getxIndex() - leftEdge.getxIndex()));
                    this.addEdge(leftEdge, Math.abs(this.getxIndex() - leftEdge.getxIndex()));
                    // Break the loop after adding the edge
                    break;
                }
            }
        }
    }

    /**
     * Adds a rightward edge from this vertex to the vertices to its right in the specified 2D array of vertices.
     * @param xTotal The total number of columns in the 2D array.
     * @param chars The 2D array of vertices representing the graph.
     */
    public void addRightEdge(int xTotal, Vertex[][] chars) {
        // Iterate through the vertices to the right of this vertex
        for (int x = this.getxIndex() + 1; x < xTotal; x++) {
            // Get the vertex at the current position
            Vertex v = chars[x][this.getyIndex()];

            // Check for special cases where the vertex to the right is not a barrier or the start/end vertex
            boolean specialCase = (x == xTotal - 1 && v.getCharacter() != '0') || v.getCharacter() == 'S' || v.getCharacter() == 'F';
            if (v.getCharacter() == '0' || specialCase) {
                // Calculate the index of the vertex to the right, considering the special case
                int xIndex = specialCase ? x : x - 1;

                // Get the vertex representing the edge to the right
                Vertex rightEdge = chars[xIndex][this.getyIndex()];
                // Add an edge between this vertex and the vertex to the right
                if (rightEdge.getCharacter() != '0') {
                    // Calculate the weight of the edge based on the difference in x-indices
                    rightEdge.addEdge(this, Math.abs(this.getxIndex() - rightEdge.getxIndex()));
                    this.addEdge(rightEdge, Math.abs(this.getxIndex() - rightEdge.getxIndex()));
                    // Break the loop after adding the edge
                    break;
                }
            }
        }
    }

    /**
     * Removes low-distance edges from the edge list of this vertex, keeping only the ones that have the maximum or minimum distance.
     */
    public void removeLowDistanceEdges() {
        // Initialize variables to keep track of edges with maximum or minimum distance in each direction
        Edge xMax = null, xMin = null, yMax = null, yMin = null;
        Vertex xMaxV = this, xMinV = this, yMaxV = this, yMinV = this;

        // Iterate through the edges of this vertex
        for (Edge e : this.edgeList) {
            // Get the end vertex of the current edge
            Vertex v = e.getEnd();

            // Determine if the end vertex is in a position to be considered for maximum or minimum distance in each direction
            boolean isColumnHigher = xMaxV.isColumnHigher(v) && xMaxV.isSameRow(v) && !xMaxV.isSame(v);
            boolean isColumnLower = !xMinV.isColumnHigher(v) && xMinV.isSameRow(v) && !xMinV.isSame(v);
            boolean isRowHigher = yMaxV.isRowHigher(v) && yMaxV.isSameColumn(v) && !yMaxV.isSame(v);
            boolean isRowLower = !yMinV.isRowHigher(v) && yMinV.isSameColumn(v) && !yMinV.isSame(v);

            // Update the edges with maximum or minimum distance in each direction
            if (isColumnHigher) {
                xMax = e;
                xMaxV = e.getEnd();
            } else if (isColumnLower) {
                xMin = e;
                xMinV = e.getEnd();
            }
            if (isRowHigher) {
                yMax = e;
                yMaxV = e.getEnd();
            } else if (isRowLower) {
                yMin = e;
                yMinV = e.getEnd();
            }
        }

        // Clear the current edge list
        this.edgeList.clear();

        // Add edges with maximum or minimum distance in each direction to the edge list
        if (xMax != null) this.edgeList.add(xMax);
        if (xMin != null) this.edgeList.add(xMin);
        if (yMax != null) this.edgeList.add(yMax);
        if (yMin != null) this.edgeList.add(yMin);
    }

}
