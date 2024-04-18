import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.molecules.QueueObject;
import components.organisms.GraphTraverser;
import components.molecules.QueuePriority;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Graph graph = FileOperations.parser("assets/benchmark/puzzle_2560.txt");
//        Graph graph = FileOperations.parser("assets/example/maze20_2.txt");
        HashPriority closedList = GraphTraverser.aStarUnweightedGraph(graph.getStart(), graph.getEnd());

//        closedList.print();

        List<Vertex> array = PathHandler.findShortestPath(closedList, graph.getStart(), graph.getEnd());
//        if (array != null){
//            for (Map.Entry<Vertex, QueueObject> v : closedList.getQueue().entrySet()) {
//                QueueObject cu = v.getValue();
//                cu.print();
////                System.out.println(cu.getPriority()+"--> ("+ cu.getVertex().getX()+ ", " + cu.getVertex().getY() + ")");
//            }
//        }
        PathHandler.printPath(array);
    }
}