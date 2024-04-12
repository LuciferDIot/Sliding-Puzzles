package services;

public class LinkedList2D {
    private char data;
    private int rowIndex=0, colIndex=0;
    private LinkedList2D down=null, up=null, left=null, right=null;


    public LinkedList2D(char data, int rowIndex, int colIndex) {
        this.data = data;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public void setDown(LinkedList2D down) {
        this.down = down;
    }

    public void setUp(LinkedList2D up) {
        this.up = up;
    }

    public void setLeft(LinkedList2D left) {
        this.left = left;
    }

    public void setRight(LinkedList2D right) {
        this.right = right;
    }

    public void setData(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    public LinkedList2D getUp() {
        return up;
    }

    public LinkedList2D getDown() {
        return down;
    }

    public LinkedList2D getLeft() {
        return left;
    }

    public LinkedList2D getRight() {
        return right;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }


}
