import components.atoms.Graph.Graph;
import components.atoms.Graph.Vertex;
import components.atoms.LinearStructure.Stack;
import components.organisms.FileOperations;
import components.organisms.PathHandler;
import components.organisms.ShortPath;

public class Main {
//    public static void main(String[] args) {
//        String assetsFolderPath = "assets";
//        Scanner scanner = new Scanner(System.in);
//
//        int loop = 0;
//        while (true) {
//            try {
//                if (loop > 0) {
//                    System.out.print("\n\n\n1. Yes \n2. No \nDo you want to continue? : ");
//                    int choice = scanner.nextInt();
//                    if (choice != 1) break;
//                }
//
//                String returnFile = AssetExplorer.exploreAssets(assetsFolderPath, scanner);
//
//                if (returnFile==null) break;
//
//                Graph graph = FileOperations.parser(returnFile);
//
//                if (graph == null) {
//                    System.out.println("Error: Unable to parse the file or invalid file format.");
//                    continue;
//                }
//
//
//                long startTime = System.nanoTime();
//                Stack<Vertex> closedList = ShortPath.findShortPath(graph.getStartToFind(), graph.getSearchInGraph());
//
//                PathHandler.printPathByStack(closedList);
//
//                long endTime = System.nanoTime();
//                long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
//
//                System.out.println("Print path execution time: " + duration + " milliseconds");
//
//                loop++;
//            } catch (Exception e) {
//                System.out.println("An error occurred: " + e.getMessage());
//            }
//        }
//        scanner.close();
//    }

    public static void main(String[] args) {
        Graph graph = FileOperations.parser("assets/example/maze15_2.txt");
        Stack<Vertex> v =ShortPath.findShortPath(graph.getStartToFind(), graph.getSearchInGraph());
        PathHandler.printPathByStack(v);
    }

}
