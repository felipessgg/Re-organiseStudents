/**
 * Write a description of class StudentMarkManager here.
 *
 * @author Felipe Sepulveda
 * @version v1
 */
import java.io.*;
import java.util.*;

public class StudentMarkManager {
    public static void main(String[] args) {
        
        String fileName = "students.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("Unit") || line.startsWith("Last Name") || line.startsWith("#")) {
                    continue;
                }
                lines.add(line);
            }
            System.out.println("File read successfully wujuuuu. Total students: " + lines.size());
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
