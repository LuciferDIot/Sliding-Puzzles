import java.util.ArrayList;

/**
 * Represents a vertex in a graph.
 */
public class Vertex {

    //Store co-ordinates
    private final int x;
    private final int y;
    private final ArrayList<Edge> edges;
    private final char ice;

    //Declared to use in Shortest pathfinder
    private Vertex previous = null;
    private int fCost = 0;

    /**
     * Constructs a new vertex with the given coordinates and terrain type.
     *
     * @param x   the x-coordinate of the vertex
     * @param y   the y-coordinate of the vertex
     * @param ice the terrain type of the vertex
     */
    public Vertex(int x, int y, char ice) {
        this.x = x;
        this.y = y;
        this.ice = ice;
        this.edges = new ArrayList<>();
    }

    /**
     * Gets the terrain type of the vertex.
     *
     * @return the terrain type
     */
    public char getIce() {
        return ice;
    }


    /**
     * Gets the previous vertex in the shortest path.
     *
     * @return the previous vertex
     */
    public Vertex getPrevious() {
        return previous;
    }

    /**
     * Sets the previous vertex in the shortest path.
     *
     * @param previous the previous vertex
     */
    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    /**
     * Gets the fCost of the vertex.
     *
     * @return the fCost of the vertex
     */
    public int getFCost() {
        return fCost;
    }

    /**
     * Sets the fCost of the vertex.
     *
     * @param fCost of the vertex
     */
    public void setFCost(int fCost) {
        this.fCost = fCost;
    }

    /**
     * Gets the x-coordinate of the vertex.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the vertex.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the x-coordinate index of the vertex (adjusted by 1 for array index).
     *
     * @return the x-coordinate index
     */
    public int getIndex_X() {
        return this.getX() - 1;
    }

    /**
     * Gets the y-coordinate index of the vertex (adjusted by 1 for array index).
     *
     * @return the y-coordinate index
     */
    public int getIndex_Y() {
        return this.getY() - 1;
    }

    /**
     * Gets the list of edges connected to this vertex.
     *
     * @return the list of edges
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Adds an edge from this vertex to the specified end vertex with the given weight.
     * Edges are only added if the end vertex is not the same as this vertex and both vertices are not obstacles.
     *
     * @param endVertex The vertex where the edge ends.
     * @param weight    The weight of the edge.
     */
    public void addEdge(Vertex endVertex, Integer weight) {
        if ((endVertex.getX() != this.getX() || endVertex.getY() != this.getY()) &&
                endVertex.getIce() != '0' && this.getIce() != '0') {
            this.edges.add(new Edge(endVertex, weight));
        }
    }

    /**
     * Gets a string representation of the vertex's coordinates.
     *
     * @return a string representing the vertex's coordinates
     */
    public String getData() {
        return "(" + this.x + ", " + this.y + ")";
    }


    /**
     * Adds edges from this vertex to the surrounding vertices in the specified 2D array of vertices.
     *
     * @param vertices The 2D array of vertices representing the graph.
     * @param xOffset  The x-direction offset for checking surrounding vertices.
     * @param yOffset  The y-direction offset for checking surrounding vertices.
     */
    public void addEdges(Vertex[][] vertices, int xOffset, int yOffset) {

        //Fetching max and min co-ordinates
        int max_X = vertices.length;
        int max_Y = vertices[0].length;

        // Adding edges in four directions around this vertex
        // Each direction represents movement sides in the grid

        // Upwards Edge
        if (!(xOffset == 0 && yOffset == -1)) {
            for (int y = this.getIndex_Y() - 1; y >= 0; y--) {
                try {
                    Vertex upVertex = vertices[this.getIndex_X()][y];

                    // If there's no obstacle or it's the start or end point, add the edge
                    if (y == 0 && upVertex.getIce() != '0' || upVertex.getIce() == 'S' || upVertex.getIce() == 'F') {

                        upVertex.addEdge(this, Math.abs(this.getIndex_Y() - upVertex.getIndex_Y()));
                        this.addEdge(upVertex, Math.abs(this.getIndex_Y() - upVertex.getIndex_Y()));
                        break;

                    } else if (upVertex.getIce() == '0') {

                        upVertex = vertices[this.getIndex_X()][y + 1]; // Skip zero ice blocks
                        upVertex.addEdge(this, Math.abs(this.getIndex_Y() - upVertex.getIndex_Y()));
                        this.addEdge(upVertex, Math.abs(this.getIndex_Y() - upVertex.getIndex_Y()));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
        }

        // Rightwards edge
        if (!(xOffset == -1 && yOffset == 0)) {
            for (int x = this.getIndex_X() + 1; x < max_X; x++) {
                try {
                    Vertex rightVertex = vertices[x][this.getIndex_Y()];

                    // If there's no obstacle or it's the start or end point, add the edge
                    if (x == max_X - 1 && rightVertex.getIce() != '0' || rightVertex.getIce() == 'S' || rightVertex.getIce() == 'F') {

                        rightVertex.addEdge(this, Math.abs(this.getIndex_X() - rightVertex.getIndex_X()));
                        this.addEdge(rightVertex, Math.abs(this.getIndex_X() - rightVertex.getIndex_X()));
                        break;

                    } else if (rightVertex.getIce() == '0') {

                        rightVertex = vertices[x - 1][this.getIndex_Y()];// Skip zero ice blocks
                        rightVertex.addEdge(this, Math.abs(this.getIndex_X() - rightVertex.getIndex_X()));
                        this.addEdge(rightVertex, Math.abs(this.getIndex_X() - rightVertex.getIndex_X()));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
        }

        // Leftwards edge
        if (!(xOffset == 1 && yOffset == 0)) {
            for (int x = this.getIndex_X() - 1; x >= 0; x--) {
                try {
                    Vertex leftVertex = vertices[x][this.getIndex_Y()];

                    // If there's no obstacle or it's the start or end point, add the edge
                    if (x == 0 && leftVertex.getIce() != '0' || leftVertex.getIce() == 'S' || leftVertex.getIce() == 'F') {

                        leftVertex.addEdge(this, Math.abs(this.getIndex_X() - leftVertex.getIndex_X()));
                        this.addEdge(leftVertex, Math.abs(this.getIndex_X() - leftVertex.getIndex_X()));
                        break;

                    } else if (leftVertex.getIce() == '0') {

                        leftVertex = vertices[x + 1][this.getIndex_Y()];// Skip zero ice blocks
                        leftVertex.addEdge(this, Math.abs(this.getIndex_X() - leftVertex.getIndex_X()));
                        this.addEdge(leftVertex, Math.abs(this.getIndex_X() - leftVertex.getIndex_X()));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                }

            }
        }


        // Downwards edge
        if (!(xOffset == 0 && yOffset == 1)) {
            for (int y = this.getIndex_Y() + 1; y < max_Y; y++) {
                try {
                    Vertex downVertex = vertices[this.getIndex_X()][y];

                    // If there's no obstacle or it's the start or end point, add the edge
                    if (y == max_Y - 1 && downVertex.getIce() != '0' || downVertex.getIce() == 'S' || downVertex.getIce() == 'F') {

                        downVertex.addEdge(this, Math.abs(this.getIndex_Y() - downVertex.getIndex_Y()));
                        this.addEdge(downVertex, Math.abs(this.getIndex_Y() - downVertex.getIndex_Y()));
                        break;

                    } else if (downVertex.getIce() == '0') {

                        downVertex = vertices[this.getIndex_X()][y - 1];// Skip zero ice blocks

                        downVertex.addEdge(this, Math.abs(this.getIndex_Y() - downVertex.getIndex_Y()));
                        this.addEdge(downVertex, Math.abs(this.getIndex_Y() - downVertex.getIndex_Y()));
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                }

            }
        }

    }

    /**
     * Generates a string representation of the vertex including its coordinates and connected edges.
     *
     * @return a string representing the vertex
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getData()).append(" --> ");
        for (Edge edge : edges) {
            sb.append(edge.getEnd().getData()).append(" ,");
        }

        return sb.toString();
    }

}
