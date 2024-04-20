package components.organisms;

import components.atoms.LinearStructure.Stack;
import components.atoms.Graph.Vertex;

import java.util.Objects;

public class PathHandler {

    public static void printPath(Stack<Vertex> path) {
        Vertex prevVertex, nextVertex, currentVertex;

        currentVertex = path.pop();
        System.out.println("Start at " + currentVertex.getCoordinates());
        prevVertex = currentVertex;

        while (true) {

            nextVertex = path.pop();

            if (path.isEmpty()) {
                String direction = calculateDirection(prevVertex, currentVertex);
                assert currentVertex != null;
                System.out.println("Move " + direction + " to " + currentVertex.getCoordinates());
                break;
            } else if (path.size()==1){
                String direction = calculateDirection(prevVertex, currentVertex);
                assert currentVertex != null;
                System.out.println("Move " + direction + " to " + currentVertex.getCoordinates());
            } else {
                if ( !(prevVertex.isSameRow(currentVertex) && currentVertex.isSameRow(Objects.requireNonNull(nextVertex))) &&
                        !(prevVertex.isSameColumn(currentVertex) && currentVertex.isSameColumn(Objects.requireNonNull(nextVertex)))) {
                    String direction = calculateDirection(prevVertex, currentVertex);
                    System.out.println("Move " + direction + " to " + currentVertex.getCoordinates());
                }
            }

            prevVertex = currentVertex;
            currentVertex = nextVertex;
        }

        System.out.println("Done!");
    }

    private static String calculateDirection(Vertex start, Vertex end) {
        if (start.isSameRow(end)) {
            return start.isColumnHigher(end) ? "Right" : "Left";
        }
        if (start.isSameColumn(end)) {
            return start.isRowHigher(end) ? "Down" : "Up";
        }
        return "Wrong";
    }
}