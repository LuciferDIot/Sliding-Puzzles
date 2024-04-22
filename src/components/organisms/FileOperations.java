package components.organisms;

import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Define a class for file operations related to parsing and extracting information from files
public class FileOperations {

    // Method to parse a file and create a graph based on its contents
    public static Graph parser(String fileName) {

        long startTime = System.nanoTime();

        // Initialize a graph object
        Graph graph = new Graph(true, false);
        // Get the total number of rows and columns from the file
        Vertex[][] chars = getCharFromFile(fileName, graph);
        graph.setAdjacent(chars, graph.getTotalColCount(), graph.getTotalRowCount());
        graph.findMaximumSlidingPath(chars);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Total execution time: " + duration + " milliseconds");


        return graph;
    }


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
                for (int x = 1; x < line.length()+1; x++) {
                    char c = line.charAt(x-1);
                    Vertex newVertex = new Vertex(x, y, c);
                    container[x-1][y-1] = newVertex;
                    if (c=='S') graph.setStartToFind(newVertex);
                    if (c=='F') graph.setSearchInGraph(newVertex);
                }
            }
            // Close the reader
            reader.close();

            return container;
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


        // Method to get the number of rows and columns from a file
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
