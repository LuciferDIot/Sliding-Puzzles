package services;

public class Stack {
    private final LinkedList2D[] stack;
    private int top;
    private final int maxSize;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new LinkedList2D[maxSize];
        this.top = -1; // Initialize top pointer to -1 to indicate an empty stack
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(LinkedList2D item) {
        if (isFull()) {
            System.out.println("Stack overflow!");
            return;
        }
        top++;
        stack[top] = item;
    }

    public LinkedList2D pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow!");
            return null;
        }
        LinkedList2D poppedItem = stack[top];
        top--;
        return poppedItem;
    }

    public LinkedList2D peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top];
    }

    public boolean contains(LinkedList2D item) {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
        }else {
            for (LinkedList2D node : stack) {
                if (node == null) break;
                else if (node.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }
}

