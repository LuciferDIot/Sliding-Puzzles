package components.atoms.Graph;

public class Graph {

    private Vertex start; // this will store the vertex which we have to start to find the F. this is S
    private Vertex end; // this will store the vertex which we have to find the shortest path.
    private int maxRow, maxCol; // this will store the row counts and column counts
    private final boolean isWeighted; // these will store whether the graph is weighted or directed
    private final boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.isDirected = inputIsDirected;
        this.isWeighted = inputIsWeighted;
    }


    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public Vertex addVertex(int x, int y, char label) {
        return new Vertex(x, y, label);
    }

    public void setStart(Vertex vertex) {
        this.start=vertex;
    }

    public void setEnd(Vertex vertex) {
        this.end=vertex;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
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
}
