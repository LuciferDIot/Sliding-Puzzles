package components.molecules;

import components.atoms.Graph.Vertex;

/**
 * Represents an object in a priority queue.
 * Implements the Comparable interface to compare objects based on priority.
 */
public class QueueObject implements Comparable<QueueObject> {

    private final Vertex vertex; // Vertex associated with the QueueObject
    private int priority; // Priority of the QueueObject
    private Vertex prev; // Previous vertex in the path
    private int totalWeight; // Total weight of the vertex

    /**
     * Constructs a QueueObject with the specified vertex, previous vertex, and priority.
     *
     * @param vertex   The vertex associated with the QueueObject.
     * @param prev     The previous vertex in the path.
     * @param priority The priority of the QueueObject.
     */
    public QueueObject(Vertex vertex, Vertex prev, int priority) {
        this.vertex = vertex;
        this.priority = priority;
        this.prev = prev;
    }

    /**
     * Gets the total weight of the vertex.
     *
     * @return The total weight of the vertex.
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the total weight of the vertex.
     *
     * @param totalWeight The total weight of the vertex to set.
     */
    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    /**
     * Gets the previous vertex in the path.
     *
     * @return The previous vertex in the path.
     */
    public Vertex getPrev() {
        return prev;
    }

    /**
     * Sets the previous vertex in the path.
     *
     * @param prev The previous vertex in the path to set.
     */
    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    /**
     * Gets the vertex associated with the QueueObject.
     *
     * @return The vertex associated with the QueueObject.
     */
    public Vertex getVertex() {
        return vertex;
    }

    /**
     * Gets the priority of the QueueObject.
     *
     * @return The priority of the QueueObject.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the QueueObject.
     *
     * @param priority The priority of the QueueObject to set.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Compares this QueueObject with another based on priority.
     *
     * @param o The QueueObject to be compared.
     * @return A negative integer, zero, or a positive integer if this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(QueueObject o) {
        return Integer.compare(this.priority, o.priority);
    }

    /**
     * Prints the details of the QueueObject.
     */
    public void print() {
        if (prev != null) {
            System.out.println(this.priority + " :  (" + this.vertex.getxAxis() + ", " + this.vertex.getyAxis() + ") "
                    + "prev :  (" + this.prev.getxAxis() + ", " + this.prev.getyAxis() + ")");
        } else {
            System.out.println(this.priority + " :  (" + this.vertex.getxAxis() + ", " + this.vertex.getyAxis() + ") ");
        }
    }
}
