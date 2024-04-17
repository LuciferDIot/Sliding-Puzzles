package components.atoms;

public class Node<T> {

    private final T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setNextNode(Node<T> node) {
        this.next = node;
    }

    public Node<T> getNextNode() {
        return this.next;
    }

}