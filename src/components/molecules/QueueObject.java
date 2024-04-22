package components.molecules;

import components.atoms.Graph.Vertex;

// Define a QueueObject class that implements Comparable interface
public class QueueObject implements Comparable<QueueObject> {

    // Private member variables for vertex, priority, and previous vertex
    private final Vertex vertex; // Vertex associated with the QueueObject
    private int priority; // Priority of the QueueObject
    private Vertex prev; // Previous vertex in the path
    private int totalWeight;

    // Constructor for QueueObject
    public QueueObject(Vertex vertex, Vertex prev, int priority) {
        // Initialize the vertex, previous vertex, and priority
        this.vertex = vertex;
        this.priority = priority;
        this.prev = prev;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    // Method to get the previous vertex
    public Vertex getPrev() {
        return prev;
    }

    // Method to set the previous vertex
    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    // Method to get the vertex associated with the QueueObject
    public Vertex getVertex() {
        return vertex;
    }

    // Method to get the priority of the QueueObject
    public int getPriority() {
        return priority;
    }

    // Method to set the priority of the QueueObject
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Method required by Comparable interface to compare QueueObjects based on priority
    @Override
    public int compareTo(QueueObject o) {
        return Integer.compare(this.priority, o.priority);
    }

    // Method to print the QueueObject details
    public void print() {
        // Print the priority and coordinates of the vertex
        if (prev != null) {
            System.out.println(this.priority + " :  (" + this.vertex.getxAxis()+ ", " + this.vertex.getyAxis() + ") "
                    + "prev :  (" + this.prev.getxAxis()+ ", " + this.prev.getyAxis() + ")");
        } else {
            System.out.println(this.priority + " :  (" + this.vertex.getxAxis()+ ", " + this.vertex.getyAxis() + ") ");
        }
    }
}
