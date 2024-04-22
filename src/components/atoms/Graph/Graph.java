package components.atoms.Graph;

public class Graph {

    private Vertex startToFind; // this will store the vertex which we have to start to find the F. this is S
    private Vertex searchInGraph; // this will store the vertex which we have to find the shortest path.
    private int totalRowCount, totalColCount; // this will store the row counts and column counts
    private final boolean isWeighted; // these will store whether the graph is weighted or directed
    private final boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.isDirected = inputIsDirected;
        this.isWeighted = inputIsWeighted;
    }

    public void setAdjacent(Vertex[][] chars, int xTotal, int yTotal) {
        for (int xAxis = 0; xAxis < xTotal; xAxis++) {
            for (int yAxis = 0; yAxis < yTotal; yAxis++) {
                Vertex v = chars[xAxis][yAxis];
                System.out.print(v.getCoordinates()+" \r");

                if (v.getCharacter() == '0') {
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
                }else if ((yAxis==0 || yAxis==yTotal - 1 || xAxis==xTotal - 1 || xAxis==0) && v.getCharacter()!='0') {
                    v.addUpEdge(chars);
                    v.addRightEdge(xTotal, chars);
                    v.addUpEdge(chars);
                    v.addDownEdge(yTotal, chars);
                }
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

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        /* This method use to add edges for both vertex1 and vertex2 */

        // Checking the graph is weighted.
        // If it is not, it will set the weight value to null.
        // It won't consider which value is passed through the method it will reassign null if the graph is unweighted.
        if (!this.isWeighted) {
            weight=null;
        }

        // set vertex1 as start and vertex2 as an end
        vertex1.addEdge(vertex2, weight);

        // if this is an undirected graph
        // set vertex2 as start and vertex1 as an end
        if (!this.isDirected) {
            vertex2.addEdge(vertex1, weight);
        }
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2) {
        /* This will remove the connection(edge) between vertex1 and vertex2*/

        // remove edge between vertex1 and vertex2 by removing edge object from vertex1
        vertex1.removeEdge(vertex2);

        // if this is an undirected graph,
        // remove edge between vertex1 and vertex2 by removing an edge object from vertex2
        if (!this.isDirected) vertex2.removeEdge(vertex1);
    }

    public void findMaximumSlidingPath(Vertex[][] chars) {
        for (Vertex[] aChar : chars) {
            for (Vertex v : aChar) {
                v.removeLowDistanceEdges();
            }
        }
    }
}
