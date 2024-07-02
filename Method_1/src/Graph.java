import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Represents a graph created with vertices and edges.
 */
public class Graph {
    private final Vertex start, end;

    /**
     * Constructs a graph with the given start and end vertices.
     *
     * @param start the start vertex
     * @param end   the end vertex
     */
    public Graph(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Parses a graph from a file.
     *
     * @param filePath the path to the file containing the graph data
     * @return the parsed graph
     * @throws IOException if an I/O error occurs
     */
    public static Graph GraphParser(String filePath) throws IOException {
        char[][] chars = readFile(filePath);
        Vertex[][] vertices = createVertexMatrix(chars);
        Graph graph = graphOperation(vertices);

        removeLowest(vertices);

        return graph;
    }


    /**
     * Creates a 2D matrix of vertices from a 2D matrix of characters.
     *
     * @param chars the 2D matrix of characters
     * @return the 2D matrix of vertices
     */
    private static Vertex[][] createVertexMatrix(char[][] chars) {
        Vertex[][] vertices = new Vertex[chars.length][chars[0].length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                Vertex v = new Vertex(i + 1, j + 1, chars[i][j]);
                vertices[i][j] = v;
            }
        }
        return vertices;
    }

    /**
     * Creates a graph from a matrix of vertices.
     *
     * @param vertices the matrix of vertices
     * @return the created graph
     */
    private static Graph graphOperation(Vertex[][] vertices) {
        Vertex start = null, end = null;

        for (int x = 0; x < vertices.length; x++) {
            for (int y = 0; y < vertices[x].length; y++) {
                Vertex currentVertex = vertices[x][y];

                if (currentVertex.getIce() == '0') {

                    try {
                        Vertex upVertex = vertices[x][y - 1];
                        if (upVertex.getIce() != '0') upVertex.addEdges(vertices, 0, 1);
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }

                    try {
                        Vertex rightVertex = vertices[x + 1][y];
                        if (rightVertex.getIce() != '0') rightVertex.addEdges(vertices, 1, 0);
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }

                    try {
                        Vertex leftVertex = vertices[x - 1][y];
                        if (leftVertex.getIce() != '0') leftVertex.addEdges(vertices, -1, 0);
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }

                    try {
                        Vertex downVertex = vertices[x][y + 1];
                        if (downVertex.getIce() != '0') downVertex.addEdges(vertices, 0, -1);
                    } catch (ArrayIndexOutOfBoundsException ignore) {
                    }
                } else {
                    if (
                            currentVertex.getIce() != '0' &&
                                    (y == vertices[x].length - 1 || x == vertices.length - 1 || x == 0 || y == 0) ||
                                    currentVertex.getIce() == 'S' || currentVertex.getIce() == 'F'
                    ) {

                        currentVertex.addEdges(vertices, 0, 0);
                        if (currentVertex.getIce() == 'S') start = currentVertex;
                        if (currentVertex.getIce() == 'F') end = currentVertex;
                    }
                }
            }
        }
        return start != null && end != null ? new Graph(start, end) : null;
    }


    /**
     * Reads a graph from a file.
     *
     * @param filePath the path to the file containing the graph data
     * @return the graph represented as a 2D array of characters
     * @throws IOException if an I/O error occurs
     */
    public static char[][] readFile(String filePath) throws IOException {
        int[] total = getCounts(filePath);
        char[][] matrix = new char[total[0]][total[1]];

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        for (int y = 0; y < matrix[0].length; y++) {
            String line = br.readLine();
            for (int x = 0; x < matrix.length; x++) {
                matrix[x][y] = line.charAt(x);
            }
        }

        return matrix;
    }

    /**
     * Gets the size of the graph from a file.
     *
     * @param filePath the path to the file containing the graph data
     * @return the size of the graph as an array of row and column counts
     * @throws IOException if an I/O error occurs
     */
    public static int[] getCounts(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;
        int row = 0, col = 0;
        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) break;

            if (line.trim().length() > col) col = line.trim().length();
            row++;
        }

        return new int[]{col, row};
    }

    /**
     * Removes vertices that have only one edge, keeping only the vertices at the corners.
     *
     * @param vertices the matrix of vertices
     */
    public static void removeLowest(Vertex[][] vertices) {

        for (Vertex[] value : vertices) {
            for (Vertex vertex : value) {
                Vertex lowestX = vertex, lowestY = vertex, maximumX = vertex, maximumY = vertex;
                Edge lowestXEdge = null, lowestYEdge = null, maximumXEdge = null, maximumYEdge = null;
                List<Edge> edges = vertex.getEdges();

                for (Edge edge : edges) {
                    Vertex end = edge.getEnd();
                    if (end.getX() == maximumY.getX() && end.getY() > maximumY.getY()) {
                        maximumY = end;
                        maximumYEdge = edge;
                    }
                    if (end.getX() == lowestY.getX() && end.getY() < lowestY.getY()) {
                        lowestY = end;
                        lowestYEdge = edge;
                    }
                    if (end.getY() == maximumX.getY() && end.getX() > maximumX.getX()) {
                        maximumX = end;
                        maximumXEdge = edge;
                    }
                    if (end.getY() == lowestX.getY() && end.getX() < lowestX.getX()) {
                        lowestX = end;
                        lowestXEdge = edge;
                    }
                }

                edges.clear();
                if (lowestYEdge != null) edges.add(lowestYEdge);
                if (lowestXEdge != null) edges.add(lowestXEdge);
                if (maximumYEdge != null) edges.add(maximumYEdge);
                if (maximumXEdge != null) edges.add(maximumXEdge);
            }
        }
    }

    /**
     * Gets the direction from the start vertex to the end vertex.
     *
     * @param start the start vertex
     * @param end   the end vertex
     * @return the direction from start to end
     */
    public static String getDirection(Vertex start, Vertex end) {
        if (start.getY() == end.getY()) {
            return start.getX() < end.getX() ? "Left" : "Right";
        }
        if (start.getX() == end.getX()) {
            return start.getY() < end.getY() ? "Up" : "Down";
        }
        return "Wrong";
    }

    /**
     * Gets the start vertex of the graph.
     *
     * @return the start vertex
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Gets the end vertex of the graph.
     *
     * @return the end vertex
     */
    public Vertex getEnd() {
        return end;
    }
}
