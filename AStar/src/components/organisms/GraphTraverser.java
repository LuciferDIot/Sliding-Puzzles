package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

/**
 * This class provides utility methods for traversing graphs and finding paths between vertices.
 * It implements an algorithm to search for the shortest path between two vertices in a graph.
 * <p>
 * Example usage:
 * <pre>{@code
 *     // Define the graph
 *     Graph graph = new Graph();
 *     // Add vertices and edges to the graph
 *     ...
 *     // Define the start and end vertices
 *     Vertex startVertex = ...
 *     Vertex endVertex = ...
 *     // Find the shortest path between the start and end vertices
 *     Stack<Vertex> path = GraphTraverser.searchInGraph(startVertex, endVertex);
 *     // Print the path
 *     PathHandler.printPathByStack(path);
 * }</pre>
 *
 * @author  KWJP Geevinda
 * @see     components.organisms.PathHandler
 * @since   1.0
 */

public class GraphTraverser {

    /**
     * Searches for a path between two vertices in a graph.
     *
     * @param startVertex The starting vertex of the search.
     * @param endVertex   The ending vertex of the search.
     * @return A stack containing the path from the startVertex to the endVertex, or null if no path is found.
     */
    public static Stack<Vertex> searchInGraph(Vertex startVertex, Vertex endVertex) {

        // Check if startVertex or endVertex is null
        if (startVertex == null || endVertex == null)
            return null; // Return null if either start or end vertex is null

        System.out.println("Searching for path from " + startVertex.getCoordinates() +
                " to " + endVertex.getCoordinates());
        // Initialize a queue to store visited vertices
        HashPriority closedList = new HashPriority();
        // Initialize a queue to store vertices to visit
        QueuePriority openList = new QueuePriority();
        // Enqueue the start vertex
        openList.enqueue(startVertex, 0);

        // Enqueue the start vertex into visited vertices with its heuristic cost
        closedList.enqueue(startVertex, null, startVertex.getTotalCost());

        boolean foundEndVertexAsNeighbour = false;
        // Iterate until the visit queue is empty
        while (!openList.isEmpty()) {
            // Dequeue the currentVertex vertex from the visit queue
            Vertex currentVertex = openList.dequeue();

            // Check if the currentVertex vertex is the end vertex
            if (currentVertex.isSame(endVertex) || foundEndVertexAsNeighbour) {
                // If the end vertex is found, break out of the loop
                if (!foundEndVertexAsNeighbour) System.out.println("\nFound end vertex\n");
                break;
            }

            QueueObject currentQueueObj = closedList.getQueueObj(currentVertex);

            // Iterate through the edges of the currentVertex vertex
            for (Edge e : currentVertex.getEdgeList()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int totalCost = (e.getWeight() + currentQueueObj.getTotalWeight()) + estimateHeuristicCost(neighbor, endVertex);

                // Check if the neighbor vertex is already visited
                QueueObject closedQueueObj = closedList.contains(neighbor);
                if (closedQueueObj == null) {
                    QueueObject newQueueObj = closedList.enqueue(neighbor, currentVertex, totalCost);
                    newQueueObj.setTotalWeight(e.getWeight() + currentQueueObj.getTotalWeight());
                    openList.enqueue(neighbor, totalCost);


                    // Check if the currentVertex vertex is the end vertex
                    if (neighbor.isSame(endVertex)) {
                        // If the end vertex is found, break out of the loop
                        System.out.println("\nFound end vertex as a neighbour\n");
                        foundEndVertexAsNeighbour=true;
                        break;
                    }

                } else if (totalCost < closedQueueObj.getPriority()) {
                    // Update the priority if the new level is lower
                    closedQueueObj.setPriority(totalCost);
                    closedQueueObj.setPrev(currentVertex);
                    closedQueueObj.setTotalWeight(e.getWeight() + currentQueueObj.getTotalWeight());
                }
            }
        }

        Stack<Vertex> returnList = new Stack<>();
        QueueObject currentQueueObj = closedList.getQueueObj(endVertex);

        while (true) {
            returnList.push(currentQueueObj.getVertex());
            if (currentQueueObj.getVertex().isSame(startVertex)) break;

            currentQueueObj = closedList.getQueueObj(currentQueueObj.getPrev());
        }

        return returnList;
    }

    /**
     * Estimates the heuristic cost (Manhattan distance) between two vertices.
     *
     * @param vertex The starting vertex.
     * @param goal   The ending vertex.
     * @return The estimated heuristic cost between the vertices.
     */
    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        int dx = Math.abs(goal.getxAxis() - vertex.getxAxis());
        int dy = Math.abs(goal.getyAxis() - vertex.getyAxis());
        return dx + dy;
    }
}
