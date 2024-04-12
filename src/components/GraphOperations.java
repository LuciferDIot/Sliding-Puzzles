package components;

import services.Graph;
import services.Queue;
import services.Stack;

public class GraphOperations {

    Graph root;

    public Graph createGraph(char[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0)
            return null; // Return null for empty array

        int rows = array.length;
        int cols = array[0].length;

        int maxQueueSize = getMaxArraySize(array);
        Queue queue = new Queue(maxQueueSize+1);  // Create queue to hold up nodes

        // Create nodes and populate the graph
        for (int i = 0; i < rows; i++) {

            Graph prev=null;
            for (int j = 0; j < cols; j++) {

                //creating the currentNode
                Graph currentNode = new Graph(array[i][j], i, j);

                //check whether prev currentNode is null or not and assign left, right values of each other
                if (prev != null) {
                    currentNode.setLeft(prev);
                    prev.setRight(currentNode);
                }
                prev = currentNode;
                queue.enqueue(currentNode);

                if (queue.getSize()>array[i].length) {
                    Graph upNode=queue.dequeue();
                    currentNode.setUp(upNode);
                    upNode.setDown(currentNode);
                }

                if (array[i][j] == 'S') root=currentNode;
            }
        }

        return root;
    }

    public int getMaxArraySize(char[][] array) {
        if (array == null || array.length == 0) {
            return 0; // Return 0 for empty or null array
        }

        int maxSize = 0;

        for (char[] subArray : array) {
            int size = subArray.length;
            if (size > maxSize) {
                maxSize = size;
            }
        }

        return maxSize;
    }

    public void printRecursive(Graph root, Stack stack) {
        if (!stack.contains(root)){
            // Print current node's data
            System.out.println("Data: " + root.getData() + ", Row: " + root.getRowIndex() + ", Col: " + root.getColIndex());
            stack.push(root);

            Graph left = root.getLeft();
            Graph right = root.getRight();
            Graph up = root.getUp();
            Graph down = root.getDown();

            // Recursively print downward node if exists
            if (left != null) {
                printRecursive(left, stack);
            }

            // Recursively print downward node if exists
            if (down != null) {
                printRecursive(down, stack);
            }

            // Recursively print downward node if exists
            if (right != null) {
                printRecursive(right, stack);
            }

            // Recursively print rightward node if exists
            if (up != null) {
                printRecursive(up, stack);
            }
        }

    }
}
