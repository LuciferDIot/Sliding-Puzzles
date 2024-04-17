package components.organisms;

import components.atoms.QueueObject;
import components.atoms.Vertex;
import components.molecules.QueuePriority;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {

    public static List<Vertex> findShortestPath(QueuePriority priorityObj, Vertex start, Vertex end) {

        List<Vertex> path = new ArrayList<>();
        boolean found = false;
        while (!found){
            PriorityQueue<QueueObject> priority = priorityObj.getQueue();
            QueueObject headObj = priority.poll();

            if (headObj != null && headObj.getPrev().isSame(start)){

                PriorityQueue<QueueObject> priorityCopy = QueuePriority.getCopy(priority);
                path.add(headObj.getPrev());

                while (!priorityCopy.isEmpty()) {
                    QueueObject currObj = priorityCopy.poll();

                    if (currObj != null && currObj.getPrev().isSame(headObj.getVertex())) path.add(currObj.getVertex());

                }
            }

            path.clear();
        }

        return path;

    }
}
