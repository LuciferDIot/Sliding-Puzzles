package components.atoms.LinearStructure;

import java.util.ArrayList;

/**
 * A generic Stack class representing a stack data structure.
 *
 * @param <T> The type of elements stored in the stack.
 */
public class Stack<T> {

    // Private member variable for storing elements in the stack
    private final ArrayList<T> elements;

    /**
     * Constructs an empty Stack.
     */
    public Stack() {
        // Initialize the ArrayList to store elements
        elements = new ArrayList<T>();
    }

    /**
     * Pushes an item onto the stack.
     *
     * @param item The item to be pushed onto the stack.
     */
    public void push(T item) {
        elements.add(item); // Add the item to the end of the ArrayList
    }

    /**
     * Pops an item from the stack.
     *
     * @return The item removed from the top of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            // If the stack is empty, throw an IllegalStateException
            throw new IllegalStateException("Stack is empty");
        }
        // Remove and return the last item from the ArrayList
        return elements.remove(elements.size() - 1);
    }

    /**
     * Peeks at the item on the top of the stack without removing it.
     *
     * @return The item at the top of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            // If the stack is empty, throw an IllegalStateException
            throw new IllegalStateException("Stack is empty");
        }
        // Return the last item from the ArrayList without removing it
        return elements.get(elements.size() - 1);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return elements.isEmpty(); // Returns true if the stack is empty
    }

    /**
     * Returns the size of the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return elements.size(); // Returns the number of elements in the stack
    }
}
