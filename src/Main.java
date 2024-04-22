import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.organisms.AssetExplorer;
import components.organisms.FileOperations;
import components.organisms.GraphTraverser;
import components.organisms.PathHandler;

import java.util.List;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        String filename = "assets/benchmark/puzzle_20.txt";
//        Graph graph = FileOperations.parser(filename);
//        System.out.println("Total Col: "+graph.getTotalColCount()+ " Row: "+graph.getTotalRowCount());
//
//        graph.getStartToFind().print(true);
//        graph.getSearchInGraph().print(true);
//        Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStartToFind(), graph.getSearchInGraph());
//        PathHandler.printPathByStack(closedList);
//    }


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

                if (returnFile==null) runAllAtOnce();
                else {
                    Graph graph = FileOperations.parser(returnFile);

                    System.out.println("\n\n------ Starting to find shortest path using A* -------");

                    long startTime = System.nanoTime();
                    Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStartToFind(), graph.getSearchInGraph());

                    if (closedList == null) {
                        System.out.println("Error: Failed to find a path.");
                        continue;
                    }

                    PathHandler.printPathByStack(closedList);

                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

                    System.out.println("Print path execution time: " + duration + " milliseconds");
                }

                loop++;
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void runAllAtOnce() {
        List<String> files = AssetExplorer.exploreAssets();

        for (String file : files) {
            System.out.println("\n\n\n"+file);
            Graph graph = FileOperations.parser(file);
            graph.getStartToFind().print(true);
            graph.getSearchInGraph().print(true);
            Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStartToFind(), graph.getSearchInGraph());
            PathHandler.printPathByStack(closedList);
        }
    }

}
