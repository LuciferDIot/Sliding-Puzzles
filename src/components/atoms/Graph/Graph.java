package components.atoms.Graph;

/**
 * Represents a graph composed of vertices and edges.
 */
public class Graph {

    private Vertex startToFind; // The starting vertex from which to find the destination (S)
    private Vertex searchInGraph; // The vertex to find the shortest path to (F)
    private int totalRowCount, totalColCount; // Total number of rows and columns in the graph

    /**
     * Sets adjacent vertices for each vertex in the graph.
     * @param chars The 2D array of vertices representing the graph.
     * @param xTotal The total number of columns in the graph.
     * @param yTotal The total number of rows in the graph.
     */
    public void setAdjacent(Vertex[][] chars, int xTotal, int yTotal) {
        // Iterate over each vertex in the graph
        for (int xAxis = 0; xAxis < xTotal; xAxis++) {
            for (int yAxis = 0; yAxis < yTotal; yAxis++) {
                Vertex v = chars[xAxis][yAxis];

                // Check if the vertex is empty ('0')
                if (v.getCharacter() == '0') {
                    // Check neighboring vertices to add edges
                    if (yAxis > 0) {
                        Vertex up = chars[xAxis][yAxis - 1];
                        if (up.getCharacter() != '0') {
                            up.addUpEdge(chars);
                            up.addLeftEdge(chars);
                            up.addRightEdge(xTotal, chars);
                        }
                    }
                    if (yAxis < yTotal - 1) {
                        Vertex down = chars[xAxis][yAxis + 1];
                        if (down.getCharacter() != '0') {
                            down.addDownEdge(yTotal, chars);
                            down.addLeftEdge(chars);
                            down.addRightEdge(xTotal, chars);
                        }
                    }
                    if (xAxis > 0) {
                        Vertex left = chars[xAxis - 1][yAxis];
                        if (left.getCharacter() != '0') {
                            left.addLeftEdge(chars);
                            left.addUpEdge(chars);
                            left.addDownEdge(yTotal, chars);
                        }
                    }
                    if (xAxis < xTotal - 1) {
                        Vertex right = chars[xAxis + 1][yAxis];
                        if (right.getCharacter() != '0') {
                            right.addRightEdge(xTotal, chars);
                            right.addUpEdge(chars);
                            right.addDownEdge(yTotal, chars);
                        }
                    }
                }else if ((yAxis==0 || yAxis==yTotal - 1 ||
                        xAxis==xTotal - 1 || xAxis==0) && v.getCharacter()!='0') {
                    // Add edges for boundary vertices
                    v.addUpEdge(chars);
                    v.addRightEdge(xTotal, chars);
                    v.addUpEdge(chars);
                    v.addDownEdge(yTotal, chars);
                }
            }
        }
    }



    /**
     * Finds the maximum sliding path in the graph.
     * @param chars The 2D array of vertices representing the graph.
     */
    public void findMaximumSlidingPath(Vertex[][] chars) {
        for (Vertex[] aChar : chars) {
            for (Vertex v : aChar) {
                v.removeLowDistanceEdges();
            }
        }
    }


    public int getTotalColCount() {
        return totalColCount;
    }

    public void setTotalColCount(int totalColCount) {
        this.totalColCount = totalColCount;
    }

    public int getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public Vertex addVertex(int x, int y, char label) {
        return new Vertex(x, y, label);
    }

    public void setStartToFind(Vertex vertex) {
        this.startToFind =vertex;
    }

    public void setSearchInGraph(Vertex vertex) {
        this.searchInGraph =vertex;
    }

    public Vertex getStartToFind() {
        return startToFind;
    }

    public Vertex getSearchInGraph() {
        return searchInGraph;
    }
}
