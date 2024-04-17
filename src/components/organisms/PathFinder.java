package components.organisms;

import components.atoms.QueueObject;
import components.atoms.Vertex;
import components.molecules.QueuePriority;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {

    public static List<Vertex> findShortestPath(QueuePriority priorityObj, Vertex start, Vertex end) {

        List<QueueObject> visited = new ArrayList<QueueObject>();

        List<Vertex> path = new ArrayList<>();
        boolean found = false;
        while (!found){
            PriorityQueue<QueueObject> priority = QueuePriority.getCopy(priorityObj.getQueue());
            QueueObject headObj = priority.poll();
            visited.add(headObj);

            if (headObj != null && !visited.contains(headObj) && headObj.getPrev().isSame(start)){
                path.add(headObj.getPrev());
                while (!priority.isEmpty()) {
                    QueueObject currObj = priority.poll();

                    if (currObj != null && currObj.getPrev().isSame(headObj.getVertex())) path.add(currObj.getVertex());
                }
            }

            path.clear();
        }

        return path;

    }
}
