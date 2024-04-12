import components.FileHandling;
import services.Graph;
import services.LinkedList2D;
import services.Stack;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        FileHandling file_handling = new FileHandling();
        char[][] puzzle= file_handling.readFile("assets/maze10_1.txt");

        Graph graph = new Graph();
        LinkedList2D root = graph.createGraph(puzzle);
        Stack stack = new Stack(puzzle.length * puzzle[0].length);
        graph.printRecursive(root, stack);
    }
}