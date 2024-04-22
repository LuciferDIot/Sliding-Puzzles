package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Queue;
import components.atoms.LinearStructure.Stack;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

import java.util.HashSet;

public class ShortPath {


    public static Stack<Vertex> findShortPath(Vertex start, Vertex end) {
        Stack<Vertex> path = new Stack<>();

        Queue<Vertex> lastQueue = null, currentQueue = null;
        HashSet<Vertex> visited = new HashSet<>();
        Vertex current = start;
        Vertex lastCameVertex = null;
        while (true) {

            visited.add(current);
            path.push(current);



            current.print(false);
            if (current.isSame(end)) break;
            currentQueue = searchInGraph(current, end);

            Vertex firstSlide = currentQueue.dequeue();
            lastCameVertex = current;

            current = slideThroughDirect(current, firstSlide, !firstSlide.isSameRow(current));
            lastQueue = currentQueue;

        }

        return path;
    }

    public static Vertex slideThroughDirect(Vertex current, Vertex end, boolean isX) {
        Vertex returnVertex = current;

        HashSet<Vertex> closedList = new HashSet<>();
        Vertex currentVertex = current, prevVertex = null;
        boolean endLoop = false;
        while (!endLoop) {
            closedList.add(currentVertex);
            prevVertex = currentVertex;
            for (Edge e : currentVertex.getEdgeList()) {
                Vertex neighbor = e.getEnd();

                if (closedList.contains(neighbor)) continue;

                if (neighbor.isSame(end)) {
                    endLoop = true;
                    break; // Exit the loop once the end vertex is found
                } else if (neighbor.isSameRow(currentVertex) && isX) {
                    currentVertex = neighbor;
                    break; // Exit the loop after updating currentVertex
                } else if (neighbor.isSameColumn(currentVertex) && !isX) {
                    currentVertex = neighbor;
                    break; // Exit the loop after updating currentVertex
                }
            }
            if (prevVertex.isSame(currentVertex)) endLoop = true; // Terminate the loop if the row remains the same
        }
        returnVertex = currentVertex;

        return returnVertex;
    }


    public static Queue<Vertex> searchInGraph(Vertex startVertex, Vertex endVertex) {

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

            // Check if the currentVertex vertex is the end vertex
            if (currentVertex.isSame(endVertex)) {
                break;
            }

            // Iterate through the edges of the currentVertex vertex
            for (Edge e : currentVertex.getEdgeList()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int heuristicCost = estimateHeuristicCost(neighbor, endVertex);
                int actualCost = 1; // weight is 1;
                int totalCost = actualCost + heuristicCost;

                // Check if the neighbor vertex is already visited
                QueueObject closedVertex = closedList.contains(neighbor);
                if (closedVertex == null) {
                    // If the neighbor is not visited, enqueue it and update its heuristic cost
                    neighbor.setTotalCost(actualCost);
                    QueueObject newQueueObj =closedList.enqueue(neighbor, currentVertex, totalCost);
                    newQueueObj.setLevel(actualCost);

                    // Enqueue the neighbor if it's not the end vertex
                    if (!endVertex.isSame(neighbor)) {
                        openList.enqueue(neighbor, totalCost);
                    } else openList.enqueue(neighbor, totalCost);

                } else if (actualCost + heuristicCost < closedVertex.getPriority()) {
                    // Update the priority if the new level is lower
                    closedVertex.setPriority(actualCost);
                    closedVertex.setPrev(currentVertex);
                }
            }
        }

        Queue<Vertex> returnList = new Queue<>();


        QueueObject prevQueueObj = closedList.getQueueObj(endVertex);
        Vertex prevVertex, currentVertex = prevQueueObj.getPrev(), nextVertex=prevQueueObj.getVertex();
        returnList.enqueue(nextVertex);

        while (true) {
            QueueObject prevObj = closedList.getQueueObj(currentVertex);
            prevVertex = prevObj.getPrev();

            if (prevVertex.isSame(startVertex)) {
                returnList.enqueue(prevVertex);
                break;
            }

            if( !(prevVertex.isSameRow(currentVertex) && currentVertex.isSameRow(nextVertex)) &&
                    !(prevVertex.isSameColumn(currentVertex) && currentVertex.isSameColumn(nextVertex))) {

                returnList.enqueue(currentVertex);
            }

            nextVertex = currentVertex;
            currentVertex = prevVertex;
        }

        return returnList;
    }

    // Method to estimate the heuristic cost (Manhattan distance) between two vertices
    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        int dx = Math.abs(vertex.getxAxis() - goal.getxAxis());
        int dy = Math.abs(vertex.getyAxis() - goal.getyAxis());
        return dx + dy;
    }



}
