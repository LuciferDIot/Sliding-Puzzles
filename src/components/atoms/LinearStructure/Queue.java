package components.atoms.LinearStructure;

import components.atoms.LinearStructure.LinkedList.LinkedList;

// Define a generic Queue class
public class Queue<T> {

    // Private member variables for the underlying linked list and the size of the queue
    private final LinkedList<T> queue; // LinkedList used to implement the queue
    private int size; // Size of the queue

    // Constructor for Queue
    public Queue() {
        // Initialize the linked list and size of the queue
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return this.size == 0; // Returns true if size is 0, indicating an empty queue
    }

    // Method to get the size of the queue
    public int size() {
        return this.size; // Returns the current size of the queue
    }

    // Method to add an element to the back of the queue
    public void enqueue(T data) {
        this.queue.addToTail(data); // Adds the element to the tail of the linked list
        this.size++; // Increment the size of the queue
    }

    // Method to get the element at the front of the queue without removing it
    public T peek(){
        if (this.isEmpty())
            return null; // If the queue is empty, return null
        else
            return this.queue.getHead().getData(); // Returns the data of the head node of the linked list
    }

    // Method to remove and return the element at the front of the queue
    public T dequeue(){
        if (!this.isEmpty()) { // If the queue is not empty
            // Remove and store the data of the head node of the linked list
            T dequeueVertex = this.queue.removeHead();
            this.size--; // Decrement the size of the queue
            return dequeueVertex; // Return the removed data
        } else
            return null; // If the queue is empty, return null
    }

    public LinkedList<T> getQueue() {return this.queue;}
}
