package services;

import java.util.Objects;

public class Graph {
    private char data;
    private final int rowIndex;
    private final int colIndex;
    private Graph down, up, left, right;


    public Graph(char data, int rowIndex, int colIndex) {
        this.data = data;
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Graph other = (Graph) obj;
        // Compare the content of nodes
        return Objects.equals(this.getData(), other.getData())
                && this.getRowIndex() == other.getRowIndex()
                && this.getColIndex() == other.getColIndex();
    }

    public void setDown(Graph down) {
        this.down = down;
    }

    public void setUp(Graph up) {
        this.up = up;
    }

    public void setLeft(Graph left) {
        this.left = left;
    }

    public void setRight(Graph right) {
        this.right = right;
    }

    public void setData(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    public Graph getUp() {
        return up;
    }

    public Graph getDown() {
        return down;
    }

    public Graph getLeft() {
        return left;
    }

    public Graph getRight() {
        return right;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }


}
