import components.FileHandling;
import components.GraphOperations;
import services.Graph;
import services.Stack;

public class Main {

    public static void main(String[] args) {

        FileHandling file_handling = new FileHandling();
        char[][] puzzle= file_handling.readFile("assets/benchmark/benchmark_series/puzzle_80.txt");

        GraphOperations graphOperations = new GraphOperations();
        Graph root = graphOperations.createGraph(puzzle);
        Stack stack = new Stack(puzzle.length * puzzle[0].length);
        graphOperations.printRecursive(root, stack);
    }
}