package components.atoms.LinearStructure;

import java.util.ArrayList;

// Define a generic Stack class
public class Stack<T> {

    // Private member variable for storing elements in the stack
    private final ArrayList<T> elements;

    // Constructor for Stack
    public Stack() {
        // Initialize the ArrayList to store elements
        elements = new ArrayList<T>();
    }

    // Method to push an item onto the stack
    public void push(T item) {
        elements.add(item); // Add the item to the end of the ArrayList
    }

    // Method to pop an item from the stack
    public T pop() {
        if (isEmpty()) {
            // If the stack is empty, throw an IllegalStateException
            throw new IllegalStateException("Stack is empty");
        }
        // Remove and return the last item from the ArrayList
        return elements.remove(elements.size() - 1);
    }

    // Method to peek at the item on the top of the stack without removing it
    public T peek() {
        if (isEmpty()) {
            // If the stack is empty, throw an IllegalStateException
            throw new IllegalStateException("Stack is empty");
        }
        // Return the last item from the ArrayList without removing it
        return elements.get(elements.size() - 1);
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return elements.isEmpty(); // Returns true if the stack is empty
    }

    // Method to get the size of the stack
    public int size() {
        return elements.size(); // Returns the number of elements in the stack
    }
}
