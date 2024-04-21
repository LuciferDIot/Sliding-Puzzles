package components.organisms;

import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;

public class PathHandler {

    public static void printPath(Stack<Vertex> path) {
        Vertex prevVertex, nextVertex, currentVertex;

        currentVertex = path.pop();
        System.out.println("Start at " + currentVertex.getCoordinates());
        prevVertex = currentVertex;

        while (true) {
            String direction = Vertex.getDirection(prevVertex, currentVertex);

            try {
                nextVertex = path.pop();
                if ( !(prevVertex.isSameRow(currentVertex) && currentVertex.isSameRow(nextVertex)) &&
                        !(prevVertex.isSameColumn(currentVertex) && currentVertex.isSameColumn(nextVertex))) {
                    System.out.println("Move " + direction + " to " + currentVertex.getCoordinates());
                }
            } catch (IllegalStateException e){
                System.out.println("Move " + direction + " to " + currentVertex.getCoordinates());
                System.out.println("Done!");
                break;
            }

            prevVertex = currentVertex;
            currentVertex = nextVertex;
        }
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