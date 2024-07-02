import java.util.*;

/**
 * Represents a class for finding the shortest path in a graph.
 */
public class ShortestPathFinder {

    /**
     * Searches for the shortest path in the graph from the start vertex to the end vertex.
     *
     * @param startVertex the start vertex
     * @param endVertex   the end vertex
     * @return a stack containing the vertices representing the shortest path from start to end
     */
    private static Stack<Vertex> searchInGraph(Vertex startVertex, Vertex endVertex) {

        // Openlist and closedlist for Dijkstra
        ArrayList<Vertex> closedList = new ArrayList<>();
        PriorityQueue<Vertex> openList = new PriorityQueue<>(Comparator.comparing(Vertex::getFCost));

        openList.add(startVertex);

        // Set fCost of start to 0 and adding to closedlist
        startVertex.setFCost(0);
        closedList.add(startVertex);

        while (!openList.isEmpty()) {
            Vertex currentVertex = openList.poll();  // Get vertex with lowest fCost

            // If current vertex is the end vertex, break the loop
            if (currentVertex.equals(endVertex)) break;

            // Get edges of the current vertex
            for (Edge e : currentVertex.getEdges()) {

                Vertex neighbour = e.getEnd(); // Get neighbour

                if (neighbour.equals(endVertex))
                    System.out.println("Found end vertex as a neighbour for " + currentVertex);

                // Calculate total cost
                int totalCost = currentVertex.getFCost() + e.getWeight();

                // If neighbour is not in closedlist, adding to openlist
                if (!closedList.contains(neighbour)) {
                    neighbour.setFCost(totalCost);
                    neighbour.setPrevious(currentVertex);
                    closedList.add(neighbour);
                    openList.add(neighbour);

                } else {
                    // If neighbour is in closedlist and new path is shorter
                    if (totalCost < neighbour.getFCost()) {
                        neighbour.setFCost(totalCost);
                        neighbour.setPrevious(currentVertex);
                    }
                }
            }
        }

        // shortest path as a stack of vertices
        Stack<Vertex> returnList = new Stack<>();
        Vertex currentVertex = endVertex;

        while (true) {
            returnList.push(currentVertex);

            // If current vertex is the start vertex, break the loop
            if (currentVertex.getX() == startVertex.getX() && currentVertex.getY() == startVertex.getY()) break;

            currentVertex = currentVertex.getPrevious(); // Moving to previous vertex
        }

        return returnList;
    }

    /**
     * Prints the shortest path from the start vertex to the end vertex.
     *
     * @param startVertex the start vertex
     * @param endVertex   the end vertex
     */
    public static void printPath(Vertex startVertex, Vertex endVertex) {
        Stack<Vertex> exactPath = searchInGraph(startVertex, endVertex);
        int stepCount = 0;
        Vertex previousVertex = startVertex;

        while (!exactPath.isEmpty()) {
            Vertex currentVertex = exactPath.pop();
            stepCount++;

            if (currentVertex == startVertex) System.out.println(stepCount + ". Start at " + currentVertex.getData());
            else if (currentVertex == endVertex) {
                String move = Graph.getDirection(currentVertex, previousVertex);
                System.out.println(stepCount + ". Move " + move + " to " + currentVertex.getData());
                System.out.println("Done !");
                break;
            } else {
                String move = Graph.getDirection(currentVertex, previousVertex);
                System.out.println(stepCount + ". Move " + move + " to " + currentVertex.getData());
            }
            previousVertex = currentVertex;
        }


    }
}
