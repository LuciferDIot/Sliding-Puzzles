package components.molecules;

import components.atoms.Graph.Vertex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * A priority queue implementation using a HashMap to store elements with priorities.
 */
public class HashPriority {

    // Private member variable for storing elements and their priorities
    private final Map<Vertex, QueueObject> queue;

    /**
     * Constructs an empty HashPriority queue.
     */
    public HashPriority() {
        this.queue = new HashMap<>();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Enqueues a vertex with a given priority.
     *
     * @param vertex   The vertex to be enqueued.
     * @param prev     The previous vertex in the path.
     * @param priority The priority of the vertex.
     * @return The QueueObject associated with the enqueued vertex.
     */
    public QueueObject enqueue(Vertex vertex, Vertex prev, int priority) {
        QueueObject newQueueObj = null;
        if (vertex != null) {
            newQueueObj = new QueueObject(vertex, prev, priority);
            queue.put(vertex, newQueueObj);
        }
        return newQueueObj;
    }

    /**
     * Dequeues the vertex with the lowest priority.
     *
     * @return The QueueObject removed from the queue.
     */
    public QueueObject dequeue() {
        if (!isEmpty()) {
            QueueObject next = peek();
            queue.remove(next.getVertex());
            return next;
        }
        return null;
    }

    /**
     * Peeks at the vertex with the lowest priority without removing it from the queue.
     *
     * @return The QueueObject at the front of the queue.
     */
    public QueueObject peek() {
        return queue.isEmpty() ? null : queue.values().stream().min(Comparator.comparingInt(QueueObject::getPriority)).orElse(null);
    }

    /**
     * Checks if the queue contains a specific vertex.
     *
     * @param vertex The vertex to check for.
     * @return The QueueObject associated with the vertex if found, otherwise null.
     */
    public QueueObject contains(Vertex vertex) {
        return queue.get(vertex);
    }

    /**
     * Gets the QueueObject associated with a specific vertex.
     *
     * @param vertex The vertex to get the QueueObject for.
     * @return The QueueObject associated with the vertex.
     */
    public QueueObject getQueueObj(Vertex vertex) {
        return queue.get(vertex);
    }

    /**
     * Gets the size of the queue.
     *
     * @return The number of elements in the queue.
     */
    public int getSize() {
        return queue.size();
    }

    /**
     * Prints the contents of the queue.
     */
    public void print() {
        System.out.println("Size of queue: " + queue.size());
        for (QueueObject element : queue.values()) {
            element.print();
        }
    }
}
