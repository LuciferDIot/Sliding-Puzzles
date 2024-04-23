package components.atoms.LinearStructure.LinkedList;

/**
 * A generic Node class representing a node in a linked list.
 *
 * @param <T> The type of data stored in the node.
 */
public class Node<T> {

    // Private member variables for the data and the next node
    private final T data; // Data stored in the node
    private Node<T> next; // Reference to the next node in the list

    /**
     * Constructs a new Node with the given data.
     *
     * @param data The data to be stored in the node.
     */
    public Node(T data) {
        // Initialize the data of the node with the provided data
        this.data = data;
        // Set the reference to the next node to null
        this.next = null;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return The data stored in the node.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param node The node to be set as the next node.
     */
    public void setNextNode(Node<T> node) {
        this.next = node;
    }

    /**
     * Gets the reference to the next node.
     *
     * @return The next node in the list.
     */
    public Node<T> getNextNode() {
        return this.next;
    }
}
