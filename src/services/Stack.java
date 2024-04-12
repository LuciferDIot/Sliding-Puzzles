package services;

public class Stack {
    private final Graph[] stack;
    private int top;
    private final int maxSize;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Graph[maxSize];
        this.top = -1; // Initialize top pointer to -1 to indicate an empty stack
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(Graph item) {
        if (isFull()) {
            System.out.println("Stack overflow!");
            return;
        }
        top++;
        stack[top] = item;
    }

    public Graph pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow!");
            return null;
        }
        Graph poppedItem = stack[top];
        top--;
        return poppedItem;
    }

    public Graph peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top];
    }

    public boolean contains(Graph item) {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
        }else {
            for (Graph node : stack) {
                if (node == null) break;
                else if (node.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }
}

