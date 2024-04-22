package components.organisms;

import components.atoms.Graph.Edge;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.molecules.QueuePriority;

import java.util.HashSet;

public class ShortPath {


    public static Stack<Vertex> findShortPath(Vertex start, Vertex end) {
        Stack<Vertex> path = new Stack<>();

        if (start!=null && end!=null) {
            HashPriority currentQueue = GraphTraversal.searchInGraph(start, end);
            HashSet<QueueObject> closedObjects = new HashSet<>();

            QueueObject currentObj = currentQueue.getQueueObj(end), prev = null;
            while (!currentQueue.isEmpty()) {
                path.push(currentObj.getVertex());
                closedObjects.add(currentObj);
                currentObj.print();

                if (currentObj.getVertex().equals(start)) break;

                boolean isX = currentObj.getPrev().isSameRow(currentObj.getVertex());

                Vertex newVertex=null, currentVertex;
                boolean isVertexFound = false;
                while (!isVertexFound) {
                    currentVertex = currentObj.getVertex();
                    newVertex = slideThroughDirect(currentVertex, end, isX);
                    QueueObject newQObj = currentQueue.getQueueObj(newVertex);
                    if (closedObjects.contains(newQObj) && prev!=null) {
                        currentVertex = currentVertex.findAnotherAdjacent(newQObj.getVertex(), prev.getVertex());
                    } else isVertexFound = true;
                }

                prev=currentObj;
                currentObj = currentQueue.dequeue(newVertex);
                currentQueue = GraphTraversal.searchInGraph(currentObj.getVertex(), end);
            }
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






}
