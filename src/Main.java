import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.organisms.GraphTraverser;
import components.molecules.QueuePriority;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = FileOperations.parser("assets/benchmark/puzzle_2560.txt");
//        Graph graph = FileOperations.parser("assets/example/maze20_2.txt");
        QueuePriority minHeap = GraphTraverser.aStarUnweightedGraph(graph.getStart(), graph.getEnd());

        List<Vertex> array = PathHandler.findShortestPath(minHeap, graph.getStart(), graph.getEnd());
//        if (array != null){
//            for (Vertex v : array) {
//                System.out.println("--> ("+ v.getX()+ ", " + v.getY() + ")");
//            }
//        }
        PathHandler.printPath(array);
    }
}