package components.molecules;

import components.atoms.QueueObject;
import components.atoms.Vertex;

import java.util.Comparator;
import java.util.PriorityQueue;

public class QueuePriority {

    private final PriorityQueue<QueueObject> queue;

    public QueuePriority() {
        // Initialize the priority queue with a comparator based on priority
        this.queue = new PriorityQueue<>(Comparator.comparingInt(QueueObject::getPriority));
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(Vertex vertex, Vertex prev, int priority) {
        if (vertex != null) {
            queue.offer(new QueueObject(vertex, prev, priority));
        }
    }

    public QueueObject dequeue() {
        return queue.poll();
    }

    public QueueObject peek() {
        return queue.peek();
    }

    public QueueObject contains(Vertex vertex) {
        for (QueueObject element : queue) {
            if (element.getVertex().isSame(vertex)) {
                return element;
            }
        }
        return null;
    }

    public PriorityQueue<QueueObject> getQueue() {
        return queue;
    }

    public static PriorityQueue<QueueObject> getCopy(PriorityQueue<QueueObject> queue) {
        return new PriorityQueue<>(queue);
    }

    public QueueObject getQueueObj(Vertex vertex) {
        for (QueueObject element : queue) {
            if (element.getVertex().equals(vertex)) {
                return element;
            }
        }
        return null;
    }

    public void print(){
        System.out.println("Size of queue: " + queue.size());
        for (QueueObject element : this.queue) {
            element.print();
        }
    }
}
