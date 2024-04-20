import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.molecules.HashPriority;
import components.organisms.AssetExplorer;
import components.organisms.GraphTraverser;
import components.organisms.FileOperations;
import components.organisms.PathHandler;

import java.util.HashSet;
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
                    System.out.print("\n\n\n1. Yes \n2. No \nDo you want to continue? : ");
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

                System.out.println("\n\n------------- Starting to find shortest path using " +
                        (choice==1?"Dijkstra":"A*") + " -------------\n");
                List<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStart(), graph.getEnd(), isAStar);

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
}
