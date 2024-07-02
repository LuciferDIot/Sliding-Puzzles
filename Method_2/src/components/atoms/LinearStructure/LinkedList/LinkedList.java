package components.atoms.LinearStructure.LinkedList;

/**
 * A generic LinkedList class representing a linear data structure.
 *
 * @param <T> The type of elements stored in the LinkedList.
 */
public class LinkedList<T> {

    // Private member variables for the head, tail, and size of the list
    private Node<T> head;
    private Node<T> tail;
    private static int size = 0; // Initialize the size of the list to 0

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        // Initialize both head and tail to null when the list is created
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new node to the head of the list.
     *
     * @param data The data to be stored in the new node.
     */
    public void addToHead(T data) {
        // Create a new node with the provided data
        Node<T> newHead = new Node<>(data);
        // Store the current head of the list
        Node<T> currentHead = this.head;
        // Set the newly created node as the head of the list
        this.head = newHead;

        // If the list was not empty, set the next node of the new head to the previous head
        if (currentHead != null)
            this.head.setNextNode(currentHead);
        else
            this.tail = newHead; // Update tail if the list was empty
        size++; // Increment the size of the list
    }

    /**
     * Adds a new node to the tail of the list.
     *
     * @param data The data to be stored in the new node.
     */
    public void addToTail(T data) {
        // Create a new node with the provided data
        Node<T> newNode = new Node<>(data);
        // If the list is empty, set both head and tail to the new node
        if (this.tail == null) {
            this.head = newNode;
        } else {
            // Otherwise, set the next node of the current tail to the new node and update the tail
            this.tail.setNextNode(newNode);
        }
        this.tail = newNode;
        size++; // Increment the size of the list
    }

    /**
     * Removes the head node from the list and returns its data.
     *
     * @return The data of the removed head node, or null if the list is empty.
     */
    public T removeHead() {
        // Store the current head node
        Node<T> removedHead = this.head;
        // If the head is null, the list is empty, so return null
        if (removedHead == null)
            return null;

        // Set the head of the list to the next node
        this.head = this.head.getNextNode();
        // If the new head is null, the list becomes empty, so update the tail
        if (this.head == null)
            this.tail = null;
        size--; // Decrement the size of the list
        // Return the data of the removed head node
        return removedHead.getData();
    }

    /**
     * Finds a specific node in the list.
     *
     * @param targetNode The node to find in the list.
     * @return The data of the found node, or null if the node is not found.
     */
    public T findNode(Node<T> targetNode) {
        // Start from the head of the list
        Node<T> current = head;
        // Traverse the list until the end is reached
        while (current != null) {
            // If the current node equals the target node, return its data
            if (current.equals(targetNode)) {
                return current.getData();
            }
            // Move to the next node
            current = current.getNextNode();
        }
        // If the target node is not found, return null
        return null;
    }

    /**
     * Gets the size of the list.
     *
     * @return The number of elements in the list.
     */
    public static int getSize() {
        return size;
    }

    /**
     * Gets the head of the list.
     *
     * @return The head node of the list.
     */
    public Node<T> getHead() {
        return head;
    }
}
