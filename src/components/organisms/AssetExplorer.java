package components.organisms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AssetExplorer {

    public static String exploreAssets(String path, Scanner scanner) {
        System.out.println("\n");

        File folder = new File(path);
        File[] files = folder.listFiles();
        String returnFile = null;

        if (files != null && files.length > 0) {
            int index = 0;
            System.out.println(index+ ". To Parent Folder");
            index++;
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(index + ". Folder: " + file.getName());
                } else {
                    System.out.println(index + ". File: " + file.getName());
                }
                index++;
            }

            System.out.println(index+ ". Run all at once");
            System.out.print("Enter the number corresponding to the file or folder you want to explore: ");
            int userInput = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (userInput > 0 && userInput <= files.length) {
                File selectedFile = files[userInput - 1];
                if (selectedFile.isDirectory()) {
                    returnFile= exploreAssets(selectedFile.getAbsolutePath(), scanner);
                } else {
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath() + "\n\n");

                    return selectedFile.getAbsolutePath();
                }
            } else if (userInput == 0) {
                String parentPath = folder.getParent();
                if (parentPath != null) {
                    returnFile= exploreAssets(parentPath, scanner);
                } else {
                    System.out.println("You are already at the root folder.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + files.length);
            }
        } else {
            System.out.println("No files or folders found in the specified directory.");
        }

        return returnFile==null? null :(path+returnFile.split(path)[1]);
    }

    public static ArrayList<String> exploreAssets() {

        String fileName = "assets\\example";

        ArrayList<String> list = new ArrayList<>();
        File[] files = new File(fileName).listFiles();

        assert files != null;
        for (File file : files) {
            list.add(file.getAbsolutePath());
        }

        fileName = "assets\\benchmark";
        files = new File(fileName).listFiles();
        assert files != null;
        for (File file : files) {
            list.add(file.getAbsolutePath());
        }
        return list;
    }
}
