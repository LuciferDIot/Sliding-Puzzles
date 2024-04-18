package components.organisms;

import components.molecules.QueueObject;
import components.atoms.Graph.Vertex;
import components.molecules.QueuePriority;

import java.util.ArrayList;
import java.util.List;

public class PathHandler {

    public static List<Vertex> findShortestPath(QueuePriority minHeap, Vertex start, Vertex end) {

        if (minHeap.isEmpty()) return null;

        List<Vertex> path = new ArrayList<>();

        QueueObject endQueueObj = minHeap.contains(end);
        if (endQueueObj == null) return null;
        else {

            QueueObject prevQueueObj = endQueueObj;

            boolean found = false;
            while (!found) {

                path.add(prevQueueObj.getVertex());
                QueueObject currQueueObj = minHeap.contains(prevQueueObj.getPrev());

                if (currQueueObj.getPrev().isSame(start)) {
                    path.add(currQueueObj.getVertex());
                    path.add(currQueueObj.getPrev());
                    found = true;
                }

                prevQueueObj = currQueueObj;
            }
        }
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
