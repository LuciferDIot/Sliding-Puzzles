package components.organisms;

import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This utility class provides methods for file operations related to parsing and extracting information from files.
 * It includes functionalities to parse a file and create a graph based on its contents.
 * <p>
 * Example usage:
 * <pre>{@code
 *     // Define the file name
 *     String fileName = "map.txt";
 *
 *     // Parse the file and create a graph
 *     Graph graph = FileOperations.parser(fileName);
 * }</pre>
 *
 * @author  KWJP Geevinda
 * @see     components.atoms.Graph.Graph
 * @see     components.atoms.Graph.Vertex
 * @since   1.0
 */

public class FileOperations {

    /**
     * Parses a file and creates a graph based on its contents.
     *
     * @param fileName The name of the file to parse.
     * @return The Graph object created from the file contents.
     */
    public static Graph parser(String fileName) {

        long startTime = System.nanoTime();

        // Initialize a graph object
        Graph graph = new Graph();
        // Get the total number of rows and columns from the file
        Vertex[][] chars = getCharFromFile(fileName, graph);
        graph.setAdjacent(chars, graph.getTotalColCount(), graph.getTotalRowCount());
        graph.findMaximumSlidingPath(chars);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time to create the graph time: " + duration + " milliseconds");


        return graph;
    }


    /**
     * Reads characters from a file and stores them in a 2D array of Vertex objects.
     *
     * @param fileName The name of the file to read.
     * @param graph    The Graph object to set the total row and column counts.
     * @return A 2D array of Vertex objects representing the characters read from the file.
     */
    public static Vertex[][] getCharFromFile(String fileName, Graph graph) {
        try {
            int[] totalRowCol = getNumOfRowCol(fileName);
            graph.setTotalColCount(totalRowCol[0]);
            graph.setTotalRowCount(totalRowCol[1]);
            Vertex[][] container = new Vertex[totalRowCol[0]][totalRowCol[1]];

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                y++;
                for (int x = 1; x < line.length() + 1; x++) {
                    char c = line.charAt(x - 1);
                    Vertex newVertex = new Vertex(x, y, c);
                    container[x - 1][y - 1] = newVertex;
                    if (c == 'S') graph.setStartToFind(newVertex);
                    if (c == 'F') graph.setSearchInGraph(newVertex);
                }
            }
            // Close the reader
            reader.close();

            return container;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the number of rows and columns from a file.
     *
     * @param fileName The name of the file to read.
     * @return An array containing the number of columns and rows.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static int[] getNumOfRowCol(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int xTotal = 0;
        int yTotal = 0;

        String line;

        // Iterate through each line to count rows and find the maximum column length
        while ((line = reader.readLine()) != null) {

            line = line.trim();
            if (line.isEmpty()) break;

            yTotal++;

            if (line.length() > xTotal) {
                xTotal = line.length();
            }
        }

        // Return the number of rows and columns as an array
        return new int[]{xTotal, yTotal};
    }

}
