package components.organisms;

import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Queue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Define a class for file operations related to parsing and extracting information from files
public class FileOperations {

    // Method to parse a file and create a graph based on its contents
    public static Graph parser(String fileName) {

        long startTime = System.nanoTime();

        Graph graph = null;

        try {
            // Create a reader to read the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // Initialize a graph object
            graph = new Graph(false, false);
            // Get the total number of rows and columns from the file
            int[] totalRowCol = getNumOfRowCol(fileName);
            graph.setMaxRow(totalRowCol[0]);
            graph.setMaxCol(totalRowCol[1]);

            // Print the total number of rows, columns, and nodes
            System.out.println("Total number of rows: " + graph.getMaxRow());
            System.out.println("Total number of Cols: " + graph.getMaxCol());
            System.out.println("Total number of Nodes: " + (graph.getMaxCol() * graph.getMaxRow()));

            // Initialize a queue to store vertices from the previous row
            Queue<Vertex> prevRow = new Queue<>();

            String line;
            int rowId = 0;
            while ((line = reader.readLine()) != null) {
                rowId++;

                // Print the loading progress for each row
                PathHandler.printLoadingBar(rowId, graph.getMaxRow());


                // Update the maximum row of the graph
                if (rowId > graph.getMaxRow()) graph.setMaxRow(rowId);

                Vertex prevVertex = null;
                for (int colId = 1; colId < line.length() + 1; colId++) {
                    // Update the maximum column of the graph
                    if (colId > graph.getMaxCol()) graph.setMaxCol(colId);

                    char label = line.charAt(colId - 1);

                    if (label != '0') {
                        // Add a new vertex to the graph
                        Vertex newVertex = graph.addVertex(colId, rowId, label);

                        // Add an edge between the current vertex and the previous vertex
                        if (prevVertex != null) graph.addEdge(newVertex, prevVertex, null);

                        // Process the previous row to handle possible connections
                        if (!prevRow.isEmpty() && graph.getMaxCol() == prevRow.size()) {
                            Vertex headVertex = prevRow.peek();
                            if (headVertex.isColumnHigher(newVertex) && headVertex.getY()+1<newVertex.getY()) {
                                while (headVertex.isColumnHigher(newVertex) && headVertex.getY()+1<newVertex.getY()) {
                                    if (prevRow.peek().getY() + 1 != headVertex.getY()) break;
                                    prevRow.dequeue();
                                }
                            } else if (headVertex.isSameColumn(newVertex)) {
                                headVertex = prevRow.dequeue();
                                if (headVertex.getLabel() != '0') graph.addEdge(headVertex, newVertex, null);
                            }
                        }

                        // Set start and end vertices
                        if (label == 'S') graph.setStart(newVertex);
                        if (label == 'F') graph.setEnd(newVertex);

                        // Enqueue the current vertex to the previous row queue
                        prevRow.enqueue(newVertex);
                        prevVertex = newVertex;
                    } else {
                        // Reset the previous vertex and dequeue from the previous row queue
                        prevVertex = null;
                        if (!prevRow.isEmpty() && graph.getMaxCol() == prevRow.size()) prevRow.dequeue();
                        prevRow.enqueue(new Vertex(colId, rowId, '0'));
                    }
                }
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println("Total execution time: " + duration + " milliseconds");

            // Close the reader
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return graph;
    }

    // Method to get the number of rows and columns from a file
    public static int[] getNumOfRowCol(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int numOfCols = 0;
        int numOfRows = 0;

        String line;

        // Iterate through each line to count rows and find the maximum column length
        while ((line = reader.readLine()) != null) {
            numOfRows++;

            if (line.length() > numOfCols) {
                numOfCols = line.length();
            }
        }

        // Return the number of rows and columns as an array
        return new int[]{numOfRows, numOfCols};
    }

}
