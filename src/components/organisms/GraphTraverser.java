package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

public class GraphTraverser {

    public static HashPriority aStarUnweightedGraph(Vertex startVertex, Vertex endVertex) {
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
        closedList.enqueue(startVertex, null, startVertex.getHeuristicCost());

        // Iterate until the visit queue is empty
        while (!openList.isEmpty()) {
            // Dequeue the currentVertex vertex from the visit queue
            Vertex currentVertex = openList.dequeue();
            // Retrieve the corresponding QueueObject from visited vertices
            QueueObject currentQueueObj = closedList.getQueueObj(currentVertex);

            // Update level (actual cost from start) using the priority of the currentVertex node
            int level = currentQueueObj.getLevel();
            int newLevel = level + 1;

            // Check if the currentVertex vertex is the end vertex
            if (currentVertex.isSame(endVertex)) {
                // If the end vertex is found, break out of the loop
                System.out.println("Found end vertex");
                break;
            }

            // Iterate through the edges of the currentVertex vertex
            for (Edge e : currentVertex.getEdges()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int heuristicCost = estimateHeuristicCost(neighbor, endVertex);

                // Check if the neighbor vertex is already visited
                QueueObject closedVertex = closedList.contains(neighbor);
                if (closedVertex == null) {
                    // If the neighbor is not visited, enqueue it and update its heuristic cost
                    neighbor.setHeuristicCost(newLevel);
                    neighbor.setPrev(currentVertex);
                    closedList.enqueue(neighbor, currentVertex, newLevel + heuristicCost);

                    // Enqueue the neighbor if it's not the end vertex
                    if (!endVertex.isSame(neighbor)) {
                        openList.enqueue(neighbor, newLevel + heuristicCost);
                    } else {
                        System.out.println("Found end vertex as a neighbor");
                    }
                } else if (newLevel < closedVertex.getPriority()) {
                    // Update the priority if the new level is lower
                    closedVertex.setPriority(newLevel);
                    closedVertex.setPrev(currentVertex);
                }
            }
        }

        return closedList;
    }

    // Method to estimate the heuristic cost (Manhattan distance) between two vertices
    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        int dx = Math.abs(vertex.getX() - goal.getX());
        int dy = Math.abs(vertex.getY() - goal.getY());
        return dx + dy;
    }
}
