
/*
 * Sliding Puzzle Game
 *
 * Author: KWJP Geevinda
 * UOW id: W1871471, IIT id: 20212016
 * Date: April 23, 2024
 *
 * Description:
 * This is a game that is used to find the shortest path by sliding in ice
 * (for more info plz look at readme.md).
 *
 * License: MIT License
 *
 * GitHub: https://github.com/LuciferDIot/Puzzle.git
 */


import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.organisms.AssetExplorer;
import components.organisms.FileOperations;
import components.organisms.GraphTraverser;
import components.organisms.PathHandler;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The main class responsible for running the application.
 */
public class Main {

    public static void main(String[] args) {
        // Define the folder path containing assets
        String assetsFolderPath = "assets";
        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Initialize loop counter
        int loop = 0;
        // Start the application loop
        while (true) {
            try {
                // Check if this is not the first iteration and prompt user for continuation
                if (loop > 0) {
                    System.out.print("\n\n\n1. Yes \n2. No \nDo you want to continue? : ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice != 1) break; // Exit the loop if user chooses to stop
                }

                // Explore assets and return the selected file path
                String returnFile = AssetExplorer.exploreAssets(assetsFolderPath, scanner);

                // If no file is selected, run all assets at once
                if (returnFile == null) {
                    runAllAtOnce();
                } else {
                    long totalTime = System.nanoTime();

                    System.out.println("\n-------          Creating the graph             -------");
                    // Parse the selected file and construct a graph
                    Graph graph = FileOperations.parser(returnFile);

                    long findPathTime = System.nanoTime();
                    // Perform A* search to find the shortest path
                    System.out.println("\n------ Starting to find shortest path using A* -------");
                    Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStartToFind(), graph.getSearchInGraph());

                    // Print the path found by A* search
                    if (closedList == null) {
                        System.out.println("Error: Failed to find a path.");
                        continue;
                    }
                    PathHandler.printPathByStack(closedList);

                    // Calculate and print execution time
                    long endTime = System.nanoTime();
                    long findPathDuration = (endTime - findPathTime) / 1000000; // Convert to milliseconds
                    long totalDuration = (endTime - totalTime) / 1000000; // Convert to milliseconds
                    System.out.println("Find path execution time: " + findPathDuration + " milliseconds");
                    System.out.println("Total process execution time: " + totalDuration + " milliseconds");
                }

                // Increment loop counter
                loop++;
            } catch (NumberFormatException | InputMismatchException e) {
                // Handle any exceptions gracefully
                System.out.println("\n\nPlease enter valid input.");
                System.out.println("Process terminated !!!");
                break;
            } catch (Exception e) {
                System.out.println("\n\nSomething went wrong.");
            }
        }
        // Close the scanner
        scanner.close();
    }

    /**
     * Explore all assets in the folder and process them sequentially.
     */
    public static void runAllAtOnce() {
        long allProcessTime = System.nanoTime();
        // Retrieve a list of all asset files
        List<String> files = AssetExplorer.exploreAssets();

        // Process each file one by one
        for (String file : files) {
            System.out.println("\n\n\n" + file);
            // Parse the file and construct a graph
            Graph graph = FileOperations.parser(file);
            // Print start and end vertices of the graph
            graph.getStartToFind().print(true);
            graph.getSearchInGraph().print(true);
            // Perform A* search and print the path
            Stack<Vertex> closedList = GraphTraverser.searchInGraph(graph.getStartToFind(), graph.getSearchInGraph());
            PathHandler.printPathByStack(closedList);
        }
        long endTime = System.nanoTime();
        long allProcessDuration = (endTime - allProcessTime) / 1000000; // Convert to milliseconds
        System.out.println("All process execution time: " + allProcessDuration + " milliseconds");
    }
}
