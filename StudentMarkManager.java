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
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("Unit") || line.startsWith("Last Name") || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String fullName = parts[1].trim() + " " + parts[0].trim();
                String id = parts[2].trim();

                double mark1 = 0.0;
                double mark2 = 0.0;
                double mark3 = 0.0;
                
                if (parts.length > 3) { mark1 = parseMark(parts[3]); }
                if (parts.length > 4) { mark2 = parseMark(parts[4]); }
                if (parts.length > 5) { mark3 = parseMark(parts[5]); }

                Student student = new Student(fullName, id, mark1, mark2, mark3);
                students.add(student);
            }

            System.out.println("Students and total marks:\n");
            for (Student s : students) {
                s.printInfo();
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static double parseMark(String mark) {
        try {
            return Double.parseDouble(mark.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}

// Student class with marks
class Student {
    private String name;
    private String id;
    private double mark1;
    private double mark2;
    private double mark3;

    public Student(String name, String id, double mark1, double mark2, double mark3) {
        this.name = name;
        this.id = id;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public double getTotal() {
        return mark1 + mark2 + mark3;
    }

    public void printInfo() {
        System.out.println(name + " (" + id + ") - Total: " + getTotal());
    }
}
