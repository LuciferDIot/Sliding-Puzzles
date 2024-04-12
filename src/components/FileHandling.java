package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandling {
    public char[][] readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            // Create the array with the correct dimensions
            int rows = 0;
            int columns = 0;
            while ((line = reader.readLine()) != null) {
                rows++;
                if (line.length() > columns) {
                    columns = line.length();
                }
            }
            char[][] array = new char[rows][columns];

            // Fill the array with the file contents
            reader = new BufferedReader(new FileReader(fileName)); // Reopen reader
            int rowNumber = 0;
            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                System.arraycopy(chars, 0, array[rowNumber], 0, chars.length);
                rowNumber++;
            }

            reader.close();
            return array;
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
