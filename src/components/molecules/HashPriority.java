package components.molecules;

import components.atoms.Graph.Vertex;

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

    public void enqueue(Vertex vertex, Vertex prev, int priority) {
        if (vertex != null) {
            queue.put(vertex, new QueueObject(vertex, prev, priority));
        }
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
        return queue.isEmpty() ? null : queue.values().stream().min((q1, q2) -> Integer.compare(q1.getPriority(), q2.getPriority())).orElse(null);
    }

    public QueueObject contains(Vertex vertex) {
        return queue.get(vertex);
    }

    public Map<Vertex, QueueObject> getQueue() {
        return queue;
    }

    public static Map<Vertex, QueueObject> getCopy(Map<Vertex, QueueObject> queue) {
        return new HashMap<>(queue);
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
