package components.atoms;

public class LinkedList<T> {

    private Node<T> head;
    private static int size=0;

    public LinkedList(){
        this.head = null;
    }

    public void addToHead(T data){
        Node<T> newHead = new Node<>(data);
        Node<T> currentHead = this.head;
        this.head = newHead;

        if(currentHead != null) this.head.setNextNode(currentHead);
        size++;
    }

    public void addToTail(T data){
        Node<T> tail = this.head;
        if(tail == null) this.head = new Node<>(data);

        else {
            while (tail.getNextNode() !=null){
                tail = tail.getNextNode();
            }
            tail.setNextNode(new Node<>(data));
        }
        size++;
    }

    public void addToMiddle(Node<T> prev, Node<T> newest){

    }

    public T removeHead(){
        Node<T> removedHead = this.head;
        if (removedHead==null) return null;

        this.head = this.head.getNextNode();
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
