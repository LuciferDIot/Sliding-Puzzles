package components.organisms;

import components.atoms.Graph.Graph;
import components.atoms.LinearStructure.Queue;
import components.atoms.Graph.Vertex;

import java.io.*;
import java.util.Objects;

public class FileOperations {

    public static Graph parser(String fileName) {

        Graph graph = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            graph = new Graph(false, false);
            int[] totalRowCol = getNumOfRowCol(fileName);

            System.out.println("Total number of rows: " + totalRowCol[0]);
            System.out.println("Total number of Cols: " + totalRowCol[1]);
            System.out.println("Total number of Nodes: " + (totalRowCol[1]*totalRowCol[0]));

            Queue<Vertex> prevRow = new Queue<>();

            String line;
            int rowId = 0;
            while ((line = reader.readLine()) != null){

                rowId++;

                if (rowId>graph.getMaxRow()) graph.setMaxRow(rowId);

                Vertex prevVertex = null;
                for (int colId = 1; colId < line.length()+1; colId++){
                    if (colId>graph.getMaxCol()) graph.setMaxCol(colId);

                    char label = line.charAt(colId-1);

                    if (label != '0') {
                        Vertex newVertex = graph.addVertex(colId, rowId, label);

                        if (prevVertex != null) graph.addEdge(newVertex, prevVertex, null);

                        if (!prevRow.isEmpty() && line.length() <= prevRow.size()){
                            Vertex headVertex = prevRow.peek();
                            if (headVertex.isColumnHigher(newVertex)) {
                                while (headVertex.isColumnHigher(newVertex)) {
                                    if (prevRow.peek().getY()+1 != headVertex.getY()) break;
                                    prevRow.dequeue();
                                }
                            }
                            else if(headVertex.isSameColumn(newVertex)) {
                                headVertex = prevRow.dequeue();
                                if (headVertex.getLabel()!='0') graph.addEdge(headVertex, newVertex, null);
                            }
                        }

                        if (Objects.equals(newVertex.getLabel(), 'S')) graph.setStart(newVertex);
                        if (Objects.equals(newVertex.getLabel(), 'F')) graph.setEnd(newVertex);

                        prevRow.enqueue(newVertex);
                        prevVertex = newVertex;
                    }
                    else {
                        prevVertex = null;
                        if (!prevRow.isEmpty()) prevRow.dequeue();
                        prevRow.enqueue(new Vertex(colId, rowId, '0'));
                    }
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return graph;
    }


    public static int[] getNumOfRowCol(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        int numOfCols = 0;
        int numOfRows = 0;

        String line;

        while ((line = reader.readLine()) != null) {
            numOfRows++;

            if (line.length() > numOfCols) {
                numOfCols = line.length();
            }
        }

        return new int[]{numOfRows, numOfCols};
    }

}
