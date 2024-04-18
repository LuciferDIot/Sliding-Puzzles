package components.atoms.LinearStructure.LinkedList;

// Define a generic Node class
public class Node<T> {

    // Private member variables for the data and the next node
    private final T data; // Data stored in the node
    private Node<T> next; // Reference to the next node in the list

    // Constructor for Node
    public Node(T data) {
        // Initialize the data of the node with the provided data
        this.data = data;
        // Set the reference to the next node to null
        this.next = null;
    }

    // Method to get the data of the node
    public T getData() {
        return data;
    }

    // Method to set the reference to the next node
    public void setNextNode(Node<T> node) {
        this.next = node;
    }

    // Method to get the reference to the next node
    public Node<T> getNextNode() {
        return this.next;
    }
}
