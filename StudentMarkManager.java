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
                if(parts.length < 3) continue;
                
                String fullName = parts[1].trim() + " " + parts[0].trim();
                String id = parts[2].trim();
                
                Student student = new Student(fullName, id);
                students.add(student);
            }
            System.out.println("Students loaded:\n");
            for (Student s : students){
                s.printInfo();
            }
            
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}

class Student {
    private String name;
    private String id;
    
    public Student (String name, String id){
        this.name = name;
        this.id = id;
    }
    
    public void printInfo(){
        System.out.println(name + " (" + id + ") ");
    }
}
