package services;

public class Queue {
    private final Graph[] queue;
    private int front=-1, rear=-1, size=0;

    public Queue(int n) {
        queue = new Graph[n];
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void enqueue(Graph x) {
        if (size == queue.length) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = x;
        size++;
    }

    public int getSize(){
        return size;
    }

    public Graph dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            front = (front + 1) % queue.length;
            Graph dequeuedElement = queue[front];
            queue[front] = null;
            size--;
            return dequeuedElement;
        }
    }
}
