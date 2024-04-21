package components.molecules;

import components.atoms.Graph.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;

// Define a class for managing a priority queue
public class QueuePriority {

    // Private member variable for storing queue elements
    private final PriorityQueue<Vertex> queue;

    // Constructor for QueuePriority
    public QueuePriority() {
        // Initialize the priority queue
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getTotalCost));
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Method to enqueue a vertex with its priority
    public void enqueue(Vertex vertex, int priority) {
        if (vertex != null) {
            // Add the vertex to the queue with its associated QueueObject
            vertex.setTotalCost(priority);
            queue.add(vertex);
        }
    }

    // Method to dequeue the vertex with the highest priority
    public Vertex dequeue() {
        if (!isEmpty()) {
            // Retrieve and remove the vertex with the highest priority from the queue
            return queue.poll();
        }
        return null;
    }

    // Method to peek at the vertex with the highest priority without removing it
    public Vertex peek() {
        // Returns the vertex with the minimum priority
        return queue.peek();
    }


    // Method to get the size of the queue
    public int getSize() {
        return queue.size();
    }

    // Method to print the elements of the queue
    public void print() {
        System.out.println("Size of queue: " + queue.size());
        for (Vertex element : queue) {
            System.out.println(element.getCoordinates());
        }
    }
}
