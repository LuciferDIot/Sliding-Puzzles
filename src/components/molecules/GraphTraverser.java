package components.molecules;

import components.atoms.*;

import java.util.ArrayList;
import java.util.Stack;

public class GraphTraverser {



    public static QueuePriority aStarUnweightedGraph(Vertex startVertex, Vertex endVertex) {
        QueuePriority visitedVertices = new QueuePriority();
        Queue<Vertex> visitQueue = new Queue<>();
        visitQueue.enqueue(startVertex);

        // Set heuristic cost of start vertex to 0
        startVertex.setHeuristicCost(0);
        visitedVertices.enqueue(startVertex, null, startVertex.getHeuristicCost());

        while (!visitQueue.isEmpty()) {
            Vertex current = visitQueue.dequeue();
            QueueObject currentQueueObj = visitedVertices.getQueueObj(current);

            // Update level (actual cost from start) using the priority of the current node
            int level = currentQueueObj.getPriority();
            int newLevel = level + 1;

            if (current.isSame(endVertex)) {
                // If we've reached the end vertex, break out of the loop
                System.out.println("Found end vertex");
                break;
            }

            for (Edge e : current.getEdges()) {
                Vertex neighbor = e.getEnd();

                // Update heuristic cost for the neighbor
                int heuristicCost = estimateHeuristicCost(neighbor, endVertex);

                QueueObject visitedQueueObj = visitedVertices.contains(neighbor);
                if (visitedQueueObj == null) {
                    neighbor.setHeuristicCost(newLevel);
                    neighbor.setPrev(current);
                    visitedVertices.enqueue(neighbor, current, newLevel + heuristicCost);

                    if (!endVertex.isSame(neighbor)) {
                        visitQueue.enqueue(neighbor);
                    }else System.out.println("Found end vertex as a neighbor");

                } else if (newLevel < visitedQueueObj.getPriority()) {
                    visitedQueueObj.setPriority(newLevel);
                    visitedQueueObj.setPrev(current);
                }
            }
        }

        return visitedVertices;
    }

    private static int estimateHeuristicCost(Vertex vertex, Vertex goal) {
        // For simplicity in an unweighted graph, can use the Manhattan distance
        // or simply return 0 as the heuristic cost, assuming all edges have equal cost.
        int dx = Math.abs(vertex.getX() - goal.getX());
        int dy = Math.abs(vertex.getY() - goal.getY());
        return dx + dy;
    }

//    public static void depthFirstTraversal(Vertex start, ArrayList<Vertex> visitedVertices) {
//        Stack<Vertex> stack = new Stack<>();
//        Stack<Vertex> pathFromRoot = new Stack<>();
//
//        pathFromRoot.push(start);
//        stack.push(start);
//
//        while (!stack.isEmpty()) {
//            Vertex current = stack.pop();
//            pathFromRoot.push(current);
//
//            if (!visitedVertices.contains(current)) {
//                System.out.println(current.getData());
//                visitedVertices.add(current);
//                for (Edge e : current.getEdges()) {
//                    Vertex neighbor = e.getEnd();
//                    if (!visitedVertices.contains(neighbor)) {
//                        stack.push(neighbor);
//                    }
//                }
//            }
//        }
//    }
//
//    public static void dijkstraUnweightedGraph(Vertex startVertex, Vertex endVertex) {
//        QueuePriority visitedVertices = new QueuePriority();
//        Queue<Vertex> visitQueue = new Queue<>();
//        visitQueue.enqueue(startVertex);
//        int level=0;
//        visitedVertices.enqueue(startVertex, null, level);
//
//        while (!visitQueue.isEmpty()) {
//            Vertex current = visitQueue.dequeue();
//            QueueObject currentQueueObj= visitedVertices.getQueueObj(current);
//            level = currentQueueObj.getPriority();
//
//            for (Edge e: current.getEdges()) {
//                Vertex neighbor = e.getEnd();
//                if (!visitedVertices.contains(neighbor)) {
//                    visitedVertices.enqueue(neighbor, current, level+1);
//
//                    if (!endVertex.equals(neighbor)) visitQueue.enqueue(neighbor);
//                } else {
//                    QueueObject neighborQueueObj= visitedVertices.getQueueObj(neighbor);
//                    if (neighborQueueObj.compareTo(currentQueueObj)>0) {
//                        neighborQueueObj.setPriority(level + 1);
//                        neighborQueueObj.setPrev(current);
//                    }
//                }
//            }
//        }
//    }

//    public static void aStarUnweightedGraph(Vertex startVertex, Vertex endVertex) {
//        QueuePriority visitedVertices = new QueuePriority();
//        Queue visitQueue = new Queue();
//        visitQueue.enqueue(startVertex);
//        int level=0;
//        visitedVertices.enqueue(startVertex, null, level);
//
//        while (!visitQueue.isEmpty()) {
//            Vertex current = visitQueue.dequeue();
//            QueueObject currentQueueObj= visitedVertices.getQueueObj(current);
//            level = currentQueueObj.getPriority();
//
//            for (Edge e: current.getEdges()) {
//                Vertex neighbor = e.getEnd();
//                if (!visitedVertices.contains(neighbor)) {
//                    visitedVertices.enqueue(neighbor, current, level+1);
//
//                    if (!endVertex.equals(neighbor)) visitQueue.enqueue(neighbor);
//                } else {
//                    QueueObject neighborQueueObj= visitedVertices.getQueueObj(neighbor);
//                    if (neighborQueueObj.compareTo(currentQueueObj)>0) {
//                        neighborQueueObj.setPriority(level + 1);
//                        neighborQueueObj.setPrev(current);
//                    }
//                }
//            }
//        }
//    }
}
