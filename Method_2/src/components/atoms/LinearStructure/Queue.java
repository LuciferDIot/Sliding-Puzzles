package components.atoms.LinearStructure;

import components.atoms.LinearStructure.LinkedList.LinkedList;

/**
 * A generic Queue class representing a queue data structure.
 *
 * @param <T> The type of elements stored in the queue.
 */
public class Queue<T> {

    // Private member variables for the underlying linked list and the size of the queue
    private final LinkedList<T> queue; // LinkedList used to implement the queue
    private int size; // Size of the queue

    /**
     * Constructs an empty Queue.
     */
    public Queue() {
        // Initialize the linked list and size of the queue
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0; // Returns true if size is 0, indicating an empty queue
    }

    /**
     * Returns the size of the queue.
     *
     * @return The current size of the queue.
     */
    public int size() {
        return this.size; // Returns the current size of the queue
    }

    /**
     * Adds an element to the back of the queue.
     *
     * @param data The element to be added to the queue.
     */
    public void enqueue(T data) {
        this.queue.addToTail(data); // Adds the element to the tail of the linked list
        this.size++; // Increment the size of the queue
    }

    /**
     * Retrieves the element at the front of the queue without removing it.
     *
     * @return The element at the front of the queue, or null if the queue is empty.
     */
    public T peek(){
        if (this.isEmpty())
            return null; // If the queue is empty, return null
        else
            return this.queue.getHead().getData(); // Returns the data of the head node of the linked list
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return The element removed from the front of the queue, or null if the queue is empty.
     */
    public T dequeue(){
        if (!this.isEmpty()) { // If the queue is not empty,
            // Remove and store the data of the head node of the linked list
            T dequeueVertex = this.queue.removeHead();
            this.size--; // Decrement the size of the queue
            return dequeueVertex; // Return the removed data
        } else
            return null; // If the queue is empty, return null
    }

    /**
     * Gets the underlying linked list of the queue.
     *
     * @return The LinkedList used to implement the queue.
     */
    public LinkedList<T> getQueue() {
        return this.queue;
    }
}
