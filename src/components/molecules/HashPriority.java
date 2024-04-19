package components.molecules;

import components.atoms.Graph.Vertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HashPriority {

    private final Map<Vertex, QueueObject> queue;

    public HashPriority() {
        this.queue = new HashMap<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public QueueObject enqueue(Vertex vertex, Vertex prev, int priority) {
        QueueObject newQueueObj = null;
        if (vertex != null) {
            newQueueObj = new QueueObject(vertex, prev, priority);
            queue.put(vertex, newQueueObj);
        }
        return newQueueObj;
    }

    public QueueObject dequeue() {
        if (!isEmpty()) {
            QueueObject next = peek();
            queue.remove(next.getVertex());
            return next;
        }
        return null;
    }

    public QueueObject peek() {
        return queue.isEmpty() ? null : queue.values().stream().min(Comparator.comparingInt(QueueObject::getPriority)).orElse(null);
    }

    public QueueObject contains(Vertex vertex) {
        return queue.get(vertex);
    }

    public QueueObject getQueueObj(Vertex vertex) {
        return queue.get(vertex);
    }

    public int getSize() {
        return queue.size();
    }

    public void print() {
        System.out.println("Size of queue: " + queue.size());
        for (QueueObject element : queue.values()) {
            element.print();
        }
    }
}
