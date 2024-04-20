import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.organisms.AssetExplorer;
import components.organisms.GraphTraverser;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String assetsFolderPath = "assets";
        Scanner scanner = new Scanner(System.in);

        int loop = 0;
        while (true) {
            try {
                if (loop > 0) {
                    System.out.print("\n\n\n1. Yes \n2. No \nDo you want to continue? : ");
                    int choice = scanner.nextInt();
                    if (choice != 1) break;
                }

                String returnFile = AssetExplorer.exploreAssets(assetsFolderPath, scanner);

                Graph graph = FileOperations.parser(returnFile);

                if (graph == null) {
                    System.out.println("Error: Unable to parse the file or invalid file format.");
                    continue;
                }


                boolean isAStar = true;
                System.out.print("\n\n\n1. Exact shortest path \n2. Quick way to end \nEnter the choice : ");
                int choice = scanner.nextInt();
                if (choice == 1) isAStar = false;

                System.out.println("\n\n------------- Starting to find shortest path using " +
                        (choice==1?"Dijkstra":"A*") + " -------------\n");
                Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), isAStar);

                if (closedList == null) {
                    System.out.println("Error: Failed to find a path.");
                    continue;
                }

                PathHandler.printPath(closedList);

                loop++;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

//    public static void main(String[] args) {
//        List<String> files = AssetExplorer.exploreAssets();
//
//        for (String file : files) {
//            System.out.println("\n\n\n"+file);
//            Graph graph = FileOperations.parser(file);
//            System.out.println("\n\n Dijkstra");
//            Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), false);
//            PathHandler.printPath(closedList);
//
//            System.out.println("\n\n A*");
//            closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), true);
//            PathHandler.printPath(closedList);
//        }
//    }

}
