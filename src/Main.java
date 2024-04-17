import components.atoms.Graph;
import components.atoms.Vertex;
import components.molecules.GraphTraverser;
import components.molecules.QueuePriority;
import components.organisms.FileOperations;
import components.organisms.PathFinder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Graph graph = FileOperations.parser("assets/benchmark/puzzle_320.txt");
        Graph graph = FileOperations.parser("assets/example/maze15_1.txt");
        QueuePriority minHeap = GraphTraverser.aStarUnweightedGraph(graph.getStart(), graph.getEnd());

        minHeap.print();

        List<Vertex> array = PathFinder.findShortestPath(minHeap, graph.getStart(), graph.getEnd());
        if (array != null){
            for (Vertex v : array) {
                System.out.println("--> ("+ v.getX()+ ", " + v.getY() + ")");
            }
        }
    }
}