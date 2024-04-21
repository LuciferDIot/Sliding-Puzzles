package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

public class GraphTraverser {

    public static Stack<Vertex> searchInGraph(Vertex startVertex, Vertex endVertex, boolean isAStar) {

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
        closedList.enqueue(startVertex, null, startVertex.getHeuristicCost());

        // Iterate until the visit queue is empty
        while (!openList.isEmpty()) {
            // Dequeue the currentVertex vertex from the visit queue
            Vertex currentVertex = openList.dequeue();

            // Retrieve the corresponding QueueObject from visited vertices
            QueueObject currentQueueObj = closedList.getQueueObj(currentVertex);
            // Update level (actual cost from start) using the priority of the currentVertex node
            int level = currentQueueObj.getLevel();
            int actualCost = level + 1;

            // Check if the currentVertex vertex is the end vertex
            if (currentVertex.isSame(endVertex)) {
                // If the end vertex is found, break out of the loop
                System.out.println("\nFound end vertex\n");
                break;
            }

            // Iterate through the edges of the currentVertex vertex
            for (Edge e : currentVertex.getEdges()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int heuristicCost = isAStar?estimateHeuristicCost(neighbor, endVertex): 0;

                // Check if the neighbor vertex is already visited
                QueueObject closedVertex = closedList.contains(neighbor);
                if (closedVertex == null) {
                    // If the neighbor is not visited, enqueue it and update its heuristic cost
                    neighbor.setHeuristicCost(actualCost);
                    QueueObject newQueueObj =closedList.enqueue(neighbor, currentVertex, actualCost + heuristicCost);
                    newQueueObj.setLevel(actualCost);

                    // Enqueue the neighbor if it's not the end vertex
                    if (!endVertex.isSame(neighbor)) {
                        openList.enqueue(neighbor, actualCost + heuristicCost);
                    } else {

                        if (isAStar) openList.enqueue(neighbor, actualCost + heuristicCost);
                        System.out.println("\nFound end vertex as a neighbor\n");
                    }
                } else if (actualCost + heuristicCost < closedVertex.getPriority()) {
                    // Update the priority if the new level is lower
                    closedVertex.setPriority(actualCost);
                    closedVertex.setPrev(currentVertex);
                }
            }
        }

        Stack<Vertex> returnList = new Stack<>();


        QueueObject prevQueueObj = closedList.getQueueObj(endVertex);
        Vertex prevVertex, currentVertex = prevQueueObj.getPrev(), nextVertex=prevQueueObj.getVertex();
        returnList.push(nextVertex);

        while (true) {
            QueueObject prevObj = closedList.getQueueObj(currentVertex);
            prevVertex = prevObj.getPrev();

            if (prevVertex.isSame(startVertex)) {
                returnList.push(prevVertex);
                break;
            }

            if( !(prevVertex.isSameRow(currentVertex) && currentVertex.isSameRow(nextVertex)) &&
                    !(prevVertex.isSameColumn(currentVertex) && currentVertex.isSameColumn(nextVertex))) {

                returnList.push(currentVertex);
            }

            nextVertex = currentVertex;
            currentVertex = prevVertex;
        }

//        returnList.push(endVertex);
//        while (true) {
//            returnList.push(prevQueueObj.getVertex());
//
//            if (prevQueueObj.getVertex().isSame(startVertex)) break;
//            prevQueueObj = closedList.getQueueObj(prevQueueObj.getPrev());
//
//        }

        return returnList;
    }

    // Method to estimate the heuristic cost (Manhattan distance) between two vertices
    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        int dx = Math.abs(vertex.getX() - goal.getX());
        int dy = Math.abs(vertex.getY() - goal.getY());
        return dx + dy;
    }
}
