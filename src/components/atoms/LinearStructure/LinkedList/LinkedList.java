package components.atoms.LinearStructure.LinkedList;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private static int size = 0;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addToHead(T data) {
        Node<T> newHead = new Node<>(data);
        Node<T> currentHead = this.head;
        this.head = newHead;

        if (currentHead != null) this.head.setNextNode(currentHead);
        else this.tail = newHead; // Update tail if list was empty
        size++;
    }

    public void addToTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        size++;
    }

    public T removeHead() {
        Node<T> removedHead = this.head;
        if (removedHead == null) return null;

        this.head = this.head.getNextNode();
        if (this.head == null) this.tail = null; // Update tail if list becomes empty
        size--;
        return removedHead.getData();
    }

    public T findNode(Node<T> targetNode) {
        Node<T> current = head;
        while (current != null) {
            if (current.equals(targetNode)) {
                return current.getData();
            }
            current = current.getNextNode();
        }
        return null;
    }

    public static int getSize() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }
}
