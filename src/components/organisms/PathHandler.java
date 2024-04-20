package components.organisms;

import components.atoms.LinearStructure.Stack;
import components.atoms.Graph.Vertex;

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
}