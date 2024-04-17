package components.atoms;

public class Queue<T> {

    private final LinkedList<T> queue;
    private int size;

    public Queue() {
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void enqueue(T data) {
        this.queue.addToTail(data);
        this.size++;
    }

    public T peek(){
        if (this.isEmpty()) return null;
        else return this.queue.getHead().getData();
    }

    public T dequeue(){
        if (!this.isEmpty()) {
            T dequeueVertex = this.queue.removeHead();
            this.size--;
            return dequeueVertex;
        }else return null;
    }
}
