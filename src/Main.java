import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.organisms.AssetExplorer;
import components.organisms.GraphTraverser;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String assetsFolderPath = "assets";
        Scanner scanner = new Scanner(System.in);

        int loop = 0;
        while (true) {
            try {
                if (loop > 0) {
                    System.out.print("\n\n\nDo you want to continue? Press 1 : ");
                    int choice = scanner.nextInt();
                    if (choice != 1) break;
                }

                String returnFile = AssetExplorer.ExploreAssets(assetsFolderPath, scanner);

                Graph graph = FileOperations.parser(returnFile);

                if (graph == null) {
                    System.out.println("Error: Unable to parse the file or invalid file format.");
                    continue;
                }


                boolean isAStar = true;
                System.out.print("\n\n\n1. Exact shortest path \n2. Quick way to end \nEnter the choice : ");
                int choice = scanner.nextInt();
                if (choice == 1) isAStar = false;

                HashPriority closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), isAStar);

                if (closedList == null) {
                    System.out.println("Error: Failed to find a path.");
                    continue;
                }

                List<Vertex> array = PathHandler.findShortestPath(closedList, graph.getStart(), graph.getEnd());

                if (array != null) {
                    PathHandler.printPath(array);
                } else {
                    System.out.println("No path found");
                }

                loop++;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }


    public static void mai(String[] args) {
//        Graph graph = FileOperations.parser("assets/benchmark/puzzle_2560.txt");
        Graph graph = FileOperations.parser("assets/example/maze10_2.txt");
        HashPriority closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), true);

        List<Vertex> array = PathHandler.findShortestPath(closedList, graph.getStart(), graph.getEnd());
        if (array!=null) PathHandler.printPath(array);
        else System.out.println("No path found");
        graph.getStart().print(false);
        graph.getEnd().print(false);
    }
}
