package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

public class GraphTraversal {

    public static HashPriority searchInGraph(Vertex startVertex, Vertex endVertex) {

        // Check if startVertex or endVertex is null
        if (startVertex == null || endVertex == null)
            return null; // Return null if either start or end vertex is null

        // Initialize a queue to store visited vertices
        HashPriority closedList = new HashPriority();
        // Initialize a queue to store vertices to visit
        QueuePriority openList = new QueuePriority();
        // Enqueue the start vertex
        openList.enqueue(startVertex, 0);

        // Enqueue the start vertex into visited vertices with its heuristic cost
        closedList.enqueue(startVertex, null, startVertex.getTotalCost());

        // Iterate until the visit queue is empty
        while (!openList.isEmpty()) {
            // Dequeue the currentVertex vertex from the visit queue
            Vertex currentVertex = openList.dequeue();

//            // Check if the currentVertex vertex is the end vertex
//            if (currentVertex.isSame(endVertex)) {
//                break;
//            }

            // Iterate through the edges of the currentVertex vertex
            for (Edge e : currentVertex.getEdgeList()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int heuristicCost = estimateHeuristicCost(neighbor, endVertex);
                int actualCost = 1; // weight is 1;
                int totalCost = actualCost + heuristicCost;

                QueueObject closedVertex = closedList.contains(neighbor);
                if (closedVertex == null) {
                    // If the neighbor is not visited, enqueue it and update its heuristic cost
                    neighbor.setTotalCost(actualCost);
                    QueueObject newQueueObj =closedList.enqueue(neighbor, currentVertex, totalCost);
                    newQueueObj.setLevel(actualCost);

                    // Enqueue the neighbor if it's not the end vertex
                    openList.enqueue(neighbor, totalCost);

                } else if (totalCost < closedVertex.getPriority()) {
                    // Update the priority if the new level is lower
                    closedVertex.setPriority(actualCost);
                    closedVertex.setPrev(currentVertex);
                }
            }
        }
        return closedList;
    }

    // Method to estimate the heuristic cost (Manhattan distance) between two vertices
    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        int dx = Math.abs(vertex.getxAxis() - goal.getxAxis());
        int dy = Math.abs(vertex.getyAxis() - goal.getyAxis());
        return dx + dy;
    }
}
