import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.organisms.GraphTraverser;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = FileOperations.parser("assets/benchmark/puzzle_2560.txt");
//        Graph graph = FileOperations.parser("assets/example/maze20_2.txt");
        HashPriority minHeap = GraphTraverser.aStarUnweightedGraph(graph.getStart(), graph.getEnd());

        List<Vertex> array = PathHandler.findShortestPath(minHeap, graph.getStart(), graph.getEnd());
//        if (array != null){
//            for (QueueObject v : array.getQueue()) {
//                Vertex cu = v.getVertex();
//                System.out.println("--> ("+ cu.getX()+ ", " + cu.getY() + ")");
//            }
//        }
        assert array != null;
        PathHandler.printPath(array);
    }
}