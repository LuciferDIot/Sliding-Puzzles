package components.molecules;

import components.atoms.Graph.Vertex;

public class QueueObject implements Comparable<QueueObject>{
    private final Vertex vertex;
    private int priority;
    private Vertex prev;

    public QueueObject(Vertex vertex, Vertex prev, int priority) {
        this.vertex = vertex;
        this.priority = priority;
        this.prev = prev;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(QueueObject o) {
        return Integer.compare(this.priority, o.priority);
    }

    public void print() {

        if (prev != null) {
            System.out.println(this.priority + " :  (" + this.vertex.getX()+ ", " + this.vertex.getY() + ") "
                    + "prev :  (" + this.prev.getX()+ ", " + this.prev.getY() + ")");
        }else
            System.out.println(this.priority + " :  (" + this.vertex.getX()+ ", " + this.vertex.getY() + ") ");
    }
}
