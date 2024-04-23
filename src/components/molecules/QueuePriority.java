package components.molecules;

import components.atoms.Graph.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Manages a priority queue of vertices.
 */
public class QueuePriority {

    // Private member variable for storing queue elements
    private final PriorityQueue<Vertex> queue;

    /**
     * Constructs a QueuePriority object.
     * Initializes the priority queue with a comparator based on vertex total cost.
     */
    public QueuePriority() {
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getTotalCost));
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Enqueues a vertex with its priority.
     *
     * @param vertex   The vertex to enqueue.
     * @param priority The priority associated with the vertex.
     */
    public void enqueue(Vertex vertex, int priority) {
        if (vertex != null) {
            // Add the vertex to the queue with its associated priority
            vertex.setTotalCost(priority);
            queue.add(vertex);
        }
    }

    /**
     * Dequeues the vertex with the highest priority.
     *
     * @return The vertex with the highest priority, or null if the queue is empty.
     */
    public Vertex dequeue() {
        if (!isEmpty()) {
            // Remove and return the vertex with the highest priority from the queue
            return queue.poll();
        }
        return null;
    }

    /**
     * Peeks at the vertex with the highest priority without removing it.
     *
     * @return The vertex with the highest priority, or null if the queue is empty.
     */
    public Vertex peek() {
        // Returns the vertex with the minimum priority
        return queue.peek();
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
     * Prints the elements of the queue.
     */
    public void print() {
        System.out.println("Size of queue: " + queue.size());
        for (Vertex element : queue) {
            System.out.println(element.getCoordinates());
        }
    }
}
