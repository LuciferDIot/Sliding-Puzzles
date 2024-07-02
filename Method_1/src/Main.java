import java.io.*;
import java.time.Duration;
import java.time.Instant;

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
 * GitHub: https://github.com/LuciferDIot/Sliding-puzzles.git
 */

/**
 * Represents the main class to run the shortest path finder program.
 */
public class Main {
    public static void main(String[] args) throws IOException {


        String filePath = getValidFilePath();

        // Time Starter
        Instant startTime = Instant.now();

        Graph graph = Graph.GraphParser(filePath);

        System.out.println("Start from " + graph.getStart().getData() + " to " + graph.getEnd().getData());

        // Find and print the shortest path
        ShortestPathFinder.printPath(graph.getStart(), graph.getEnd());

        // End the timer and Calculate and display the execution time
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Execution time in milliseconds: " + duration.toMillis());

    }

    public static String getValidFilePath() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = null;
        boolean isValid = false;

        while (!isValid) {
            System.out.println("\n\n\n-------------------------------------------------");
            System.out.println("--------------- SLIDING PUZZLES ---------------");
            System.out.println("-------------------------------------------------");
            System.out.print("Input file path (files/benchmark/puzzle_20.txt): ");
            filePath = reader.readLine();
            System.out.println("-------------------------------------------------");

            File file = new File(filePath);
            if (file.exists()) {
                isValid = true;
            } else {
                System.out.println("File not found. Please enter a valid file path.");
            }
        }

        return filePath;}

}

