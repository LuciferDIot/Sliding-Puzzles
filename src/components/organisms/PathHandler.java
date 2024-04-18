package components.organisms;

import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.atoms.Graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class PathHandler {

    /**
     * Finds the shortest path from start to end vertex using a closed list (QueuePriority).
     *
     * @param closedList The closed list (QueuePriority) containing visited vertices.
     * @param start The start vertex of the path.
     * @param end The end vertex of the path.
     * @return The shortest path from start to end vertex, or null if closedList is empty or end vertex is not found.
     */
    public static List<Vertex> findShortestPath(HashPriority closedList, Vertex start, Vertex end) {
        // Check if the closedList is empty
        if (closedList.isEmpty()) return null; // Return null if the closedList is empty

        // Initialize a list to store the shortest path
        List<Vertex> path = new ArrayList<>();

        // Check if the end vertex exists in the closedList
        QueueObject endQueueObj = closedList.contains(end);
        if (endQueueObj == null)
            return null; // Return null if the end vertex is not found
        else {
            // Initialize a variable to store the previous QueueObject
            QueueObject prevQueueObj = endQueueObj;

            // Flag to track if the shortest path is found
            boolean found = false;
            while (!found) {
                // Add the current vertex to the path
                if (!path.contains(prevQueueObj.getVertex())) path.add(prevQueueObj.getVertex());
                // Retrieve the QueueObject for the previous vertex
                QueueObject currQueueObj = closedList.contains(prevQueueObj.getPrev());

                // Check if the previous vertex is the start vertex
                if (currQueueObj.getPrev().isSame(start)) {
                    // Add the current and previous vertices to the path and set found to true
                    if (!path.contains(currQueueObj.getVertex())) path.add(currQueueObj.getVertex());
                    if (!path.contains(currQueueObj.getPrev())) path.add(currQueueObj.getPrev());
                    found = true;
                }

                // Update the previous QueueObject
                prevQueueObj = currQueueObj;
            }
        }
        // Reverse the path and return it
        return path.reversed();
    }


    public static void printPath(List<Vertex> path) {

//        if (path != null){
//            for (Vertex v : path) {
//                System.out.println("--> ("+ v.getX()+ ", " + v.getY() + ")");
//            }
//        }

        Vertex firstVertex = path.getFirst();

        System.out.println("Start at "+ firstVertex.getCoordinates());

        Vertex prevVertex=firstVertex;
        String direction = "";
        for (int i = 1; i < path.size(); i++) {
            Vertex currVertex = path.get(i);

            String newDirection = calculateDirection(currVertex, prevVertex);
            System.out.println(newDirection+" :"+ " curr: "+currVertex.getCoordinates()+" Prev: "+prevVertex.getCoordinates());

            if (!newDirection.equals(direction)) {
                direction = newDirection;
                System.out.println("Move "+direction+" to "+currVertex.getCoordinates());
            }

            if (i == path.size() - 1) {
                System.out.println("End at "+ currVertex.getCoordinates());
            }

            prevVertex = currVertex;
        }

        System.out.println("Done!");
    }

//    private static List<Vertex> removeDuplicateAxis(List<Vertex> path) {
//
//        int prevX, prevY;
//        for (int i = 0; i < path.size() ; i++) {
//            Vertex currVertex = path.get(i);
//
//
//        }
//    }

    private static String calculateDirection(Vertex start, Vertex end) {
        if (start.isSameRow(end)) {
            if (start.isColumnHigher(end)) return "Right";
            else return "Left";
        }

        if (start.isSameColumn(end)) {
            if (start.isRowHigher(end)) return "Down";
            else return "Up";
        }

        return null;
    }


}
