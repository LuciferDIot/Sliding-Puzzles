package components.molecules;

import components.atoms.LinkedList;
import components.atoms.Node;
import components.atoms.QueueObject;
import components.atoms.Vertex;

public class PriorityList extends LinkedList<QueueObject> {

    public QueueObject findQueueObject(int x, int y){
        Node<QueueObject> currentNode = this.getHead();
        QueueObject exactMatch = null;
        while (exactMatch == null && this.getHead() != null){
            Vertex currentVertex = currentNode.getData().getVertex();
            if (currentVertex.getX()==x && currentVertex.getY()==y) exactMatch = currentNode.getData();

            currentNode = currentNode.getNextNode();
        }

        return exactMatch;
    }
}
