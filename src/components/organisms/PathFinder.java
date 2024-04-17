package components.organisms;

import components.atoms.QueueObject;
import components.atoms.Vertex;
import components.molecules.QueuePriority;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathFinder {

    public static List<Vertex> findShortestPath(QueuePriority priorityObj, Vertex start, Vertex end) {

        if (priorityObj.isEmpty()) return null;

        List<Vertex> path = new ArrayList<>();

        boolean found = false;
        while (!found && !priorityObj.isEmpty()){
            QueueObject headObj = priorityObj.dequeue();
            System.out.println("priority "+priorityObj.getSize());

            if (headObj.getVertex().isSame(start)) continue;

            if (headObj.getPrev().isSame(start)){

                PriorityQueue<QueueObject> priorityCopy = QueuePriority.getCopy(priorityObj.getQueue());
                path.add(headObj.getPrev());

                while (!priorityCopy.isEmpty()) {
                    QueueObject currObj = priorityCopy.poll();
                    System.out.println("priorityCopy "+priorityCopy.size());

                    if (currObj != null && currObj.getPrev().isSame(headObj.getVertex())) {
                        path.add(currObj.getVertex());

                        System.out.println("Current path");
                        currObj.print();

                        end.print(false);
                        if (currObj.getVertex().isSame(end)) {
                            System.out.println("Found");
                            found = true;
                            break;
                        }
                        headObj = currObj;
                    }

                }
            }

            if (!found) path.clear();;
        }

        return path;

    }
}
