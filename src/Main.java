import components.atoms.Graph;
import components.molecules.GraphTraverser;
import components.molecules.QueuePriority;
import components.organisms.FileOperations;

public class Main {
    public static void main(String[] args) {
        Graph graph = FileOperations.parser("assets/benchmark/puzzle_320.txt");
        QueuePriority array = GraphTraverser.aStarUnweightedGraph(graph.getStart(), graph.getEnd());
        array.print();
    }
}