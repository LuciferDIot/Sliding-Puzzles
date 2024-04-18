package components.organisms;

import components.atoms.QueueObject;
import components.atoms.Vertex;
import components.molecules.QueuePriority;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {

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
}
