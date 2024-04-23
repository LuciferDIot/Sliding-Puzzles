package components.organisms;

import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;

/**
 * This class provides utility methods for handling paths and printing loading bars.
 * It includes methods for printing paths represented by stacks of vertices and for printing loading bars.
 * <p>
 * Example usage:
 * <pre>{@code
 *     // Create a stack representing the path
 *     Stack<Vertex> path = new Stack<>();
 *     path.push(new Vertex(0, 0)); // Add vertices representing the path
 *     path.push(new Vertex(1, 0));
 *     path.push(new Vertex(2, 0));
 *     // Print the path
 *     PathHandler.printPathByStack(path);
 *
 *     // Print a loading bar
 *     PathHandler.printLoadingBar(50, 100); // Prints "Loading |#####     |"
 * }</pre>
 *
 * @author  KWJP Geevinda
 * @see     components.atoms.Graph.Vertex
 * @see     components.atoms.LinearStructure.Stack
 * @since   1.0
 */

public class PathHandler {

    /**
     * Prints the path represented by a stack of vertices.
     *
     * @param path The stack containing the vertices representing the path.
     */
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

    /**
     * Prints a loading bar based on the current progress.
     *
     * @param rowId  The current row or progress.
     * @param maxRow The maximum number of rows or the completion value.
     */
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
