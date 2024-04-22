package components.organisms;

import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;

public class PathHandler {

    public static void printPathByStack(Stack<Vertex> path) {

        int step = 0;
        Vertex currentVertex = path.pop(), prevVertex;
        System.out.println((++step) + ". Start at " + currentVertex.getCoordinates());

        while (!path.isEmpty()) {
            prevVertex = path.pop();
            String direction = Vertex.getDirection(prevVertex, currentVertex);
            System.out.println((++step) + ". Move " + direction + " to " + prevVertex.getCoordinates());

            currentVertex = prevVertex;
        }

        System.out.println((++step) + ". End at " + currentVertex.getCoordinates());
    }

    public static void printLoadingBar(int rowId, int maxRow) {
        // Calculate the number of '#' characters to print based on the current row
        int numHashes = Math.round(rowId / (float) maxRow * 10); // Multiply by 10 for a finer display

        // Print "Loading |" followed by the '#' characters and then "|"
        System.out.print("Loading |");
        for (int i = 0; i < numHashes; i++) {
            System.out.print("#");
        }
        System.out.print("|\r");
    }
}